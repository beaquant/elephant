/**
 * 构建界面元素的工具类。
 * 使用方法：
 * 
 * @author 刘志斌
 * @date 2012-10-25
 */
Ext.define('ECF.app.GridBuilder', {
	constructor: function(metaModel){
		//参数合法性校验
		if(!metaModel) throw 'Parameter "metaModel" is null, creating GridBuilder failed!';
		//初始化
		this.$metaModel = metaModel;
		this.callParent(arguments);
	},
	
	/**
	 * 为Grid生成一个列。
	 * 
	 * 使用示例：
	 * var gb = metamodel.getGridBuilder();
	 * gb.col('updateUser');
	 * 说明：updateUser为元模型metamodel中定义的属性名称，Grid的列显示这一属性的值。
	 * 
	 * gb.col({name:'status', editable:true, width:60});
	 * 
	 * @param options 列的配置选项。
	 * 列的配置选项可以是字符串，其值必须是元模型中的属性名称；也可以是一个配置对象，配置对象支持的属性如下：
	 * name: string，用来指定元模型中定义的属性名称；
	 * editable: boolean，该列的值是否可以编辑修改；
	 * width: number，指定列的宽度，如果不指定宽度，列采用自适应宽度；
	 * origin: object，ExtJS Grid支持的任何配置属性都可以通过origin对象传给col方法；
	 */
	col: function(options){
		if(!options) throw 'GridBuilder.col(): 参数options不能为null';
		var name = '';
		if(typeof options==='string') name = options; else name = options.name;
		if(!name) throw 'GridBuilder.col(): 未指定属性名称';
		var meta = this.$metaModel, prop = meta.getProp(name);
		if(!prop) throw 'GridBuilder.col(): 元模型'+meta.modelName()+'中未定义属性'+name;

		var cfg = { text:prop.label, dataIndex:prop.name, menuDisabled:true, sortable:false };
		cfg = Ext.apply(cfg, options.origin);
		
		if(options.width>0) cfg.width = options.width; else cfg.flex = 1;
		
		if(prop.domaintype=='enum') cfg.renderer = ECF.app.enums.getRender(prop.ref);
		//if(prop.domaintype=='bool') cfg.renderer = ECF.app.enums.getRender('Status');
		if(prop.datatype=='date'){
			if(prop.domaintype=='date') cfg.renderer = Ext.util.Format.dateRenderer('Y-m-d');
			if(prop.domaintype=='time') cfg.renderer = Ext.util.Format.dateRenderer('H:i:s');
			if(prop.domaintype=='datetime') {
				if(options.format) cfg.renderer = Ext.util.Format.dateRenderer(options.format);
				else cfg.renderer = Ext.util.Format.dateRenderer('Y-m-d H:i:s');
			}
		}
		
		if(options.editable) {
			cfg.cls = 'col-editable';
			cfg.editor = {};
			if(prop.nullable === false) cfg.editor.allowBlank = false;
			//枚举类型，编辑时需要显示下拉选择框
			if(prop.domaintype=='enum'){
				cfg.editor.xtype = 'combobox';
				cfg.editor.displayField = 'text';
				cfg.editor.valueField = 'value';
				cfg.editor.editable = false;
				cfg.editor.listClass = 'x-combo-list-small';
				cfg.editor.store = ECF.app.enums.getStore('Status');
			}else if(prop.domaintype=='bool'){
                cfg.editor.xtype = 'checkbox';
                //cfg.editor.inputValue = '1';
			}
		}
		
		return cfg;
	},
	
	actionCol: function(app, options){
		var me = this;
		var cfg = { header:'操作', menuDisabled:true, xtype:'actioncolumn' };
		if(!options) return cfg;
		if(options.width) cfg.width = options.width;
		if(options.origin) Ext.apply(cfg, options.origin);
		cfg.items = [];
		Ext.each(options.items, function(item){
			var cmd = item;
			if(typeof item==='string'){
				if(!me.registeredCommands[item]) throw 'GridBuilder.actionCol(): 无法识别的按钮名称'+item;
				cmd = Ext.apply({}, me.registeredCommands[item]);
				cmd.iconCls = 'grid-cmd';
				cmd.event = 'grid-' + cmd.event;
			}
			if(cmd.event && app) {
				cmd.handler = function(grid, rowIndex, colIndex){
					app.fireEvent(cmd.event, grid, rowIndex, colIndex);
				};
			}
			cfg.items.push(cmd);
		});
		return cfg;
	},
	
	/**
	 * 
	 */
	tbarCmd: function(app, options){
		if(!options) throw 'GridBuilder.tbarCmd(): 必须指定系统中已经注册的工具栏按钮名称或自定义按钮的配置项';
		var cfg = options;
		if(typeof options === 'string'){
			if(!this.registeredCommands[options]) throw 'GridBuilder.tbarCmd(): 无法识别的工具栏按钮名称'+options;
			cfg = Ext.apply({}, this.registeredCommands[options]);
			cfg.iconCls = 'tbar-' + cfg.iconCls;
			cfg.event = 'tbar-' + cfg.event;
		}
		if(cfg.event && app)
			cfg.handler = function(){ app.fireEvent(cfg.event); };
		return cfg;
	},
	
	buildQFormID: function(grid){ return grid.getId()+'-qform-ct'; },
	/**
	 * 为Grid构造一个查询框。
	 * @param grid Grid对象。
	 * @param items 需要在查询框中放置的查询条件控件。
	 *    items[][]是一个2维数组，第一维代表查询框中显示多少行，第二维代表每一行上显示的多个字段
	 */
	qform: function(grid, items){
		var cfg = {xtype:'fieldset', layout:'anchor', collapsible:true, defaults:{anchor:'100%'}, width:'100%'
			, items:[]
		};
		var meta = this.$metaModel, index=0, me=this;
		Ext.each(items, function(row){
			cfg.items.push({xtype:'container', layout:'hbox',id:me.buildQFormID(grid)+'-'+index,items:[]});
			Ext.each(row, function(col){
				if(!col) return true;
				if(!col.id) throw 'GridBuilder.qform(): 每个字段必须配置id属性';
				var itemCfg = {itemId:col.id, cls:'query-form-item', align:'right'};
				var prop = {};
				if(col.name && meta.getProp(col.name)) prop = Ext.apply(prop, meta.getProp(col.name));
				else Ext.apply(itemCfg, col);
				Ext.apply(prop, col);
				
				itemCfg.fieldLabel = prop.label;
				if(prop.labelWidth>0) itemCfg.labelWidth=prop.labelWidth;
				if(prop.labelPad>=0) itemCfg.labelPad = prop.labelPad;
				if(typeof prop.labelSeparator==='string') itemCfg.labelSeparator = prop.labelSeparator;
				if(prop.hideLabel) itemCfg.hideLabel = prop.hideLabel;
				if(prop.width>0) itemCfg.width=prop.width;
				if(prop.cls) itemCfg.cls = itemCfg.cls + ' ' + prop.cls;
				if(prop.emptyText) itemCfg.emptyText = prop.emptyText;
				
				if(prop.domaintype=='enum'){
					itemCfg.xtype='combobox';
					itemCfg.editable=false;
					itemCfg.store=ECF.app.enums.getStore(prop.ref, (prop.label==null)?(col.noAll ? null : {value:'', text:' - 全部 - '}):(col.noAll ? null : {value:'', text:prop.label}))
					itemCfg.displayField='text';
					itemCfg.valueField='value';
					if(col.value) itemCfg.value=col.value;
					else itemCfg.value='';
				}else if(prop.datatype=='date'){
					if(prop.domaintype=='datetime') {
						itemCfg.xtype = 'datetimefield';
						itemCfg.dateCfg = {};
						itemCfg.timeCfg = {};
						if(prop.maxValue) {
							itemCfg.dateCfg.maxValue = prop.maxValue;
							itemCfg.timeCfg.maxValue = prop.maxValue;
						}
						if(prop.value) {
							itemCfg.dateCfg.value = prop.value;
							itemCfg.timeCfg.value = prop.value;
						}
					} else {
						if(prop.domaintype=='date') itemCfg.xtype = 'datefield';
						else if(prop.domaintype=='time') itemCfg.xtype = 'timefield';
						else throw 'Invalidate domaintype for date field: ' + prop.name;
						itemCfg.format = 'Y-m-d';
						if(prop.maxValue) itemCfg.maxValue = prop.maxValue;
						if(prop.value) itemCfg.value = prop.value;	
					}
				}else if(prop.datatype=='string' || prop.datatype=='number' || prop.datatype=='float'){
					itemCfg.xtype='textfield';
					if(prop.maxlen>0) {
						itemCfg.maxLength=prop.maxlen;
						itemCfg.enforceMaxLength = true;
					}
				}
				
				cfg.items[index].items.push(itemCfg);
				return true;
			});
			index++;
			return true;
		});
		
		return cfg;
	},
	
	getQFormValue: function(grid, id){
		for(var i=0; i<20; i++){
			var ct = Ext.getCmp(this.buildQFormID(grid)+'-'+i);
			if(ct==null) return null;
			var value = this.internalGetQFormValue(ct, id);
			if(value!=null) {
				if(value.constructor != Date)
					return value;
				return Ext.Date.format(value, 'Y-m-d H:i:s');
			}
		}
		return null;
    },
    internalGetQFormValue: function(ct, id){
    	if(ct==null || (typeof ct.getComponent)!='function') return null;
    	var cmp = ct.getComponent(id);
    	if(cmp==null) return null;
    	return cmp.getValue();
    },
	
	pagingBar: function(store){
		return Ext.create('Ext.PagingToolbar', {
	        store: store,
	        displayInfo: true,
	        displayMsg: '当前显示第 {0} 到 {1} 条记录，总共 {2} 条记录',
	        emptyMsg: "没有数据"
	    });
	},
	
	registeredCommands:{
		add: { text:'创建', tooltip:'创建', iconCls:'cmd-add', icon:'/resources/images/cmd-add.png', event:'cmd-create' },
		save: { text:'保存', tooltip:'保存', iconCls:'cmd-save', icon:'/resources/images/cmd-save.png', event:'cmd-save' },
		close: { text:'关闭', tooltip:'关闭', iconCls:'cmd-close', icon:'/resources/images/cmd-close.png', event:'cmd-close' },
		edit: { text:'编辑', tooltip:'编辑', iconCls:'cmd-edit', icon:'/resources/images/cmd-edit.png', event:'cmd-edit' },
		query: { text:'查询', tooltip:'查询', iconCls:'cmd-query', icon:'/resources/images/cmd-query.png', event:'cmd-query' },
		role: { text:'角色', tooltip:'分配角色', iconCls:'cmd-role', icon:'/resources/images/cmd-role.png', event:'cmd-role' }
	}
});