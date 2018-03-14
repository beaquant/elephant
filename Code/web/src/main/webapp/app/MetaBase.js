/**
 * 元数据基类。
 * 使用规范: 
 *     Ext.define('ECF.app.system.metamodel.User',{
 *         extend: 'ECF.app.MetaBase',
 *         //......
 *     });
 * @author: 刘志斌
 * @date 2012-10-20
 */
Ext.define('ECF.app.MetaBase', {
	requires: ['Ext.form.field.DateTimeDisplay'],
	/**
	 * 使用了单例模式，define子类之后会立即调用constructor。
	 */
	constructor: function(){
		this.defineModel();
		this.callParent([]);
		Ext.log('[meta] meta model "' + this.metaModelName() + '" has been defined.');
	},
	/**
	 * 根据元数据信息定义模型对象。
	 * 备注：该方法使用方式等同于类的静态方法，所有子类的实例变量无法访问。
	 * 命名约定: 
	 * 1. 在元数据模型中可以通过$modelName指定模型对象的类名；
	 * 2. 如果元数据模型中没有指定模型对象类名，则使用默认类名，
	 *    规则为：例如ECF.app.system.metamodel.User，则对应的模型对象类名为ECF.metadata.model.system.User，
	 *    即将metamodel替换为model；
	 */
	defineModel: function(){
		var name = this.$modelName;
		if(!name){
			name = this.metaModelName().replace('\.metamodel\.', '.model.');
			if(name==this.metaModelName())
				throw 'Property "modelName" is required for meta model!';
			this.$modelName = name;
		}
		if(Ext.ClassManager.isCreated(name)) {
			Ext.log('[meta] model "' + name + '" has already been defined!');
			return;
		}
		var data = { extend: 'Ext.data.Model', fields:[] };
		if(this.properties!=null && this.properties.length>0){
			for(var i=0; i<this.properties.length; i++){
				var prop = this.properties[i];
				var field = { name:prop.name, type:prop.datatype };
				if(prop.domaintype=='date' || prop.domaintype=='datetime' || prop.domaintype=='time')
					field.dateFormat = 'time';
				data.fields.push(field);
				if(prop.domaintype=='id')
					data.idProperty = field.name;
			}
		}else{
			Ext.log('[meta] meta model "' + this.metaModelName() + '" has no properties!');
		}
		Ext.define(name, data, function(){ Ext.log('[meta] model "' + name + '" has been defined.'); });
	},
	modelName: function(){ return this.$modelName; },
	metaModelName: function(){
		//注意：这里this.getName()无法使用，直接使用非公开的私有属性
		return this.$className; 
	},
    
	/**
	 * 获取元模型属性。
	 * @param name 属性名称。
	 */
	getProp: function(name){
		var me = this;
		if(!me.properties || me.properties.length<=0){
			Ext.log('[meta] ' + me.metaModelName() + ' has no properties!');
			return null;
		}
		for(var i=0; i<me.properties.length; i++){
			if(!me.properties[i]) continue;
			if(me.properties[i].name == name) return me.properties[i];
		}
		Ext.log('[meta] ' + me.metaModelName() + ' has no property "' + name + '"!');
		return null;
	},
	
	/**
	 * 获取指定名称的api。
	 */
	getApi: function(name){
		var me = this;
		if(!me.api || me.api.length<=0){
			ECF.msg.error('系统错误', me.metaModelName() + '没有设置任何api');
			return null;
		}
		for(var i=0; i<me.api.length; i++){
			if(me.api[i].name==name) return me.api[i];
		}
		ECF.msg.error('系统错误', me.metaModelName() + '没有设置api: ' + name);
		return null;
	},
	
	//===============================================================================
	// 页面展示相关的方法
	//===============================================================================
	/**
	 * 
	 */
	getFormBuilder: function(){
		return Ext.create('ECF.app.FormBuilder', this);
	},
	getGridBuilder: function(){
		return Ext.create('ECF.app.GridBuilder', this);
	},
	
	//===============================================================================
	// 数据操作相关的方法
	//===============================================================================
	/**
	 * 获取查询列表数据的Store。
	 * @param name 查询接口api名称，如果不指定则使用默认名称：query。
	 */
	getStoreConfig: function(name, storeType, noReader){
		if(!name || name.length<=0) name = 'find';
		//尝试查找之前是否已经创建过store
		var storeId = this.modelName() + '-' + name;
		/*var store = Ext.data.StoreManager.lookup(storeId);
		if(store) {
			Ext.log('[store] ' + storeId + ' was found in cache.');
			return store;
		}*/
		var api = this.getApi(name);
		if(api==null) return null;
		var options = {
            autoDestroy:true, autoLoad:false
            , storeId:storeId
            , model:this.modelName()
            , proxy:{ type: 'ajax', url: api.url
            	, pageParam:'pi', limitParam:'ps', noCache:false
            	, afterRequest: ECF.storeExceptionHandler
            }
        };
		//如果要创建Ext.data.TreeStore，服务器端返回的数据必须是:
		//{text:'root', expanded:true, leaf:false, children:[
		//	{..., children: [ ... ] },
		//	{..., children: [ ... ] }
		//]}
		//即第一个节点必须为root节点，下面跟子节点(children)，每个节点通过leaf、expanded表明是否是叶子节点、子节点是否已经展开。
		//root节点外面不能再有HttpJsonResult包装返回结果
		if(!noReader)
			options.proxy.reader = { 
				type: 'json', root: 'data'
        		, successProperty: 'success', messageProperty: 'message', totalProperty: 'totalCount' 
        	};
		var cfgPageSize = Ext.get('txtCbsCfgPageSize'), ps = 0;
		if(cfgPageSize) ps = parseInt(cfgPageSize.getValue());
		if(isNaN(ps) || ps<10) ps = 25;
		if(api.pageSize) ps = ps + api.pageSize;
		options.pageSize = ps;
		if(storeType=='Ext.data.TreeStore'){
			options.folderSort = false;
		}
		
		return options;
	},
	
	getStore: function(name, storeType, noReader){
		var storeCfg = this.getStoreConfig(name, storeType, noReader);
		Ext.log('[store] ' + storeCfg.storeId + ' was created.');
		return Ext.create(storeType ? storeType : 'Ext.data.JsonStore', storeCfg);
	},
	
	/**
	 * 通过服务器端提供的api接口，根据id加载一条数据。
	 * @param id 被加载对象的ID值；
	 * @param callback 成功加载数据后的回调函数；
	 *    回调函数有一个参数：callback(data)，参数data为加载的数据对象。
	 * @param apiName 元模型中定义的api接口名称。
	 * 注意：
	 * 1. 目前加载单条数据的服务器端接口必须采用如下形式：/api/xxx/yyy/get/{id}，即将id值拼在url中；
	 * 2. 服务器端接口必须采用GET方式处理请求；
	 */
	load: function(id, callback, apiName){
		if(!apiName) apiName = 'get';
		var api = this.getApi(apiName);
		if(api==null) return null;
		var url = api.url ? '' : api.url;
		if(url.substring(url.length-1)!='/') url = url + '/';
		url = url + (id ? '' : id);
		var con = new Ext.data.Connection({ method:'GET', url:url });
		ECF.request(con, {
			scope:this,
			success: function(result, options){
				if(typeof callback=='function') callback(result && result.data ? result.data : null, options);
			} 
		});
	},
	/**
	 * 更新一条记录。
	 * @param data JSON对象，可以是从store中得到的record对象。
	 * @param callback 执行成功后的回调函数。
	 *   回调函数有两个参数：callback(result, options)
	 *   1. result: 执行请求后返回的JSON结果对象，必须为以下格式：{ success:true/false, message:'如果出错，返回错误消息', data:'任意服务器端结果，数据类型不限' }；
	 *   2. extjs的ajax请求对象；
	 * 注意：
	 * 1. 服务器端接口必须采用POST方式处理请求；
	 * 2. 服务器端接口必须返回标准的result JSON对象（即上面callback函数中result参数形式）；
	 */
	update: function(data, callback){
		this.doPost(data, callback, 'update');
	},
	/**
	 * 将grid中快速编辑的内容更新到服务器端。
	 * 每执行一次更新一条数据，直到dirty records变为空。
	 */
	updateGrid: function(grid, callback){
		//校验
		if(!grid) return;
		if(typeof grid.getStore!='function') 
			throw this.metaModelName() + '.updateGrid(): the parameter "grid" is not an Ext Grid object!';
		if(grid.getStore().getProxy().getModel().modelName!=this.modelName()){
			ECF.msg.error('系统错误', this.metaModelName() + '.updateGrid(): \n' +
					'Grid使用的模型名称与元模型不一致：\n' +
					'Grid使用的模型名称：' + grid.getStore().getProxy().getModel().modelName + ',\n' +
					'元模型的模型名称：' + this.modelName());
			return;
		}
		var dirtyRecords = grid.getStore().getUpdatedRecords(), me = this;
		if(!dirtyRecords || dirtyRecords.length<=0) return;
		//开始更新
		me.update(dirtyRecords[0].data, function(data){
			dirtyRecords[0].commit(); //clear the dirty flag
			if(dirtyRecords.length>1) {
				me.updateGrid(grid, callback);
				return;
			}
			//全部更新完毕，重新加载grid数据
			grid.getStore().reload();
			if(typeof callback=='function') callback();
		});
	},
	/**
	 * 创建一条记录。
	 * @param data JSON对象，可以是从store中得到的record对象。
	 * @param callback 执行成功后的回调函数。
	 *   回调函数有两个参数：callback(result, options)
	 *   1. result: 执行请求后返回的JSON结果对象，必须为以下格式：{ success:true/false, message:'如果出错，返回错误消息', data:'任意服务器端结果，数据类型不限' }；
	 *   2. extjs的ajax请求对象；
	 * @param apiName 元模型中定义的api接口名称。
	 * 注意：
	 * 1. 服务器端接口必须采用POST方式处理请求；
	 * 2. 服务器端接口必须返回标准的result JSON对象（即上面callback函数中result参数形式）；
	 */
	create: function(data, callback){
		this.doPost(data, callback, 'create');
	},
	doPost: function(data, callback, apiName, onFailure){
		var api = this.getApi(apiName);
		if(api==null) return null;
		if(!data) data = {};
		data.winHeight = Ext.getBody().getViewSize().height;
		this.beforePost(data);
		var con = new Ext.data.Connection({ method: 'POST', url: api.url });
		ECF.request(con, {
			params:data, scope:this,
			success: function(result, options){
				if(typeof callback=='function') callback(result, options);
			},
			failure: onFailure
		});
	},
	/**
	 * POST提交之前对数据进行处理：
	 * 1. 将日期时间类型格式化为yyyy-MM-dd HH:mm:ss；
	 */
	beforePost: function(data){
		if(!this.properties) return;
		for(var i=0; i<this.properties.length; i++){
			var prop = this.properties[i];
			if(!data.hasOwnProperty(prop.name) || data[prop.name]==null) continue;
			if(prop.datatype=='date' && data[prop.name].constructor == Date){
				data[prop.name] = Ext.Date.format(data[prop.name], 'Y-m-d H:i:s');
			}
		}
	}

});