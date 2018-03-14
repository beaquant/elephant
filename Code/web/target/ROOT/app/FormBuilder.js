/**
 * 构建界面元素的工具类。
 * 使用方法：
 * 
 * @author 刘志斌
 * @date 2012-10-25
 */
Ext.define('ECF.app.FormBuilder', {
	constructor: function(metaModel){
		//参数合法性校验
		if(!metaModel) throw 'Parameter "metaModel" is null, creating FormBuilder failure!';
		//初始化
		this.$metaModel = metaModel;
		this.callParent(arguments);
	},
	/**
	 * 系统默认提供的按钮。
	 */
	registeredCommands: {
		prev:{
            cfg:{text:'上一条', icon:'/resources/images/cmd-prev.png',iconCls: 'button-cmd'},
            buildHandler: function(form, ev){
            	return function() {
	                
	            };
            }
		},
		next:{
			cfg:{text:'下一条', icon:'/resources/images/cmd-next.png',iconCls: 'button-cmd'},
            buildHandler: function(form, ev){
            	return function() {
	                
	            };
            }
		},
		ok:{
            cfg:{text:'保存', icon:'/resources/images/cmd-save.png',iconCls: 'button-cmd'},
            buildHandler: function(form, ev){
            	return function() {
	                if(form.app && typeof form.app.fireEvent=='function'){
	                	form.app.fireEvent(ev ? ev : 'form-cmd-save', form);
		        	}
	            };
            }
        },
        close:{
            cfg:{text:'关闭', icon:'/resources/images/cmd-close.png', iconCls:'button-cmd'},
            buildHandler: function(form, ev){
            	return function() {
                	if(form.app && typeof form.app.fireEvent=='function'){
                		form.app.fireEvent('form-cmd-close', form);
                		form.close();
		        	}
	            };
            }
        }
	},
	/**
	 * 系统默认提供的校验方式。
	 */
	validators:{
		phone:{regex:/^[0-9\- ]+$/, msg:'请填写正确的电话号码'},
		mobile:{regex:/^[0-9]{11,11}$/, msg:'请填写正确的手机号码'},
		email:{regex:/^([\w\.\-\+])+\@(([\w\-])+\.)+([a-zA-Z0-9]{2,6})$/, msg:'请填写正确的邮箱地址'}
	},
	/**
	 * 构造一个form field配置。
	 * @param option 
	 *   为object类型，option上的相关配置属性会拷贝到返回结果的field配置中，用于完全根据元模型自动生成field配置无法
	 *   满足要求的情况下，可以由开发者加入所需配置项；
	 * @return 单个field的配置对象。
	 */
	field: function(option, fieldsDefault){
		var name = null;
		if(typeof option==='string') name = option;
		else name = option.name;
		if(!name) {
			if(option.$name) option.name = option.$name;
			return option;
		}
		var meta = this.$metaModel;
		var prop = meta.getProp(name);
		if(!prop) throw 'Property "'+name+'" not found in meta model:'+meta.modelName();
		
		var cfg = {name:prop.name, itemId:prop.name, fieldLabel:prop.label};
		if(option.origin) Ext.apply(cfg, option.origin);
		//隐藏字段
		if(option.hidden){
			cfg.xtype='hiddenfield';
			Ext.apply(cfg, option);
			return cfg;
		}
		//field的name、label、宽度、label宽度
		if(prop.nullable===false) {
			cfg.fieldLabel = '<span class="required-field-flag">*</span>' + cfg.fieldLabel;
			cfg.allowBlank = false;
		}
		if(fieldsDefault && fieldsDefault.labelWidth>0)
			cfg.labelWidth = fieldsDefault.labelWidth;
		if(option.labelWidth>=0) cfg.labelWidth = option.labelWidth;
		if(option.width>=0) cfg.width = option.width;
		//日期时间类型格式化
		if(prop.datatype=='date'){
			if(prop.domaintype=='time') cfg.format = 'H:i:s';
			if(prop.domaintype=='date') cfg.format = 'Y-m-d';
			if(prop.domaintype=='datetime') cfg.format = 'Y-m-d H:i:s';
		}
		//只读字段
		if(option.readonly){
			if(prop.datatype=='date') cfg.xtype = 'datetimedisplayfield';
			else cfg.xtype='displayfield';
			if(prop.domaintype=='enum'){
				cfg.renderer=ECF.app.enums.getRender(prop.ref);
			}
			Ext.apply(cfg, option);
			return cfg;
		}
		if(prop.maxlen>0) {
			cfg.maxLength = prop.maxlen;
			cfg.enforceMaxLength = true;
		}
		//默认值
		if(prop.defaultValue!=null){
			cfg.value = prop.defaultValue;
		}
		//password类型
		if(prop.domaintype=='password'){
			cfg.xtype = 'textfield';
			cfg.inputType = 'password';
			Ext.apply(cfg, option);
			return cfg;
		}
		//枚举类型
		if(prop.domaintype=='enum'){
			cfg.xtype = 'combobox';
			cfg.store = ECF.app.enums.getStore(prop.ref, option.emptyItem, option.includes, option.excludes);
			cfg.displayField = 'text';
			cfg.valueField = 'value';
			cfg.autoSelect = true;
			cfg.editable = false;
			Ext.apply(cfg, option);
			return cfg;
		}
		//数据校验
		if(prop.regex){
			if(prop.regex.constructor!=RegExp) throw 'Regex for property "'+prop.name+'" must be RegExp object!';
			cfg.validator = function(value){
				if(!value || value.length<=0) {
					if(prop.nullable===false) return prop.name + '不能为空';
					return true;
				}
				if(prop.regex.test(value)) return true;
				return prop.msg ? prop.msg : '请按照正确的格式填写';
			};
			cfg.validateOnChange = false;
		}else{
			var validateCfg = this.validators[prop.domaintype];
			if(this.validators[prop.domaintype]) 
				cfg.validator=function(value){
					if(Ext.isEmpty(value)) return true;
					if(validateCfg.regex.test(value)) return true;
					return validateCfg.msg ? validateCfg.msg : '请按照正确的格式填写';
				};
			cfg.validateOnChange = false;
		}
		//日期时间类型
		if(prop.datatype=='date'){
			if(prop.domaintype=='date'){
				cfg.xtype = 'datefield';
			}else if(prop.domaintype=='datetime'){
				cfg.xtype = 'datetimefield';
			}else if(prop.domaintype=='time'){
				cfg.xtype = 'timefield';
			}else{
				throw 'Invalidate domaintype for date field: '+prop.name;
			}
			return cfg;
		}
		//其他类型，包括纯字符串
		else if(prop.domaintype==='bigstring') {
			cfg.xtype = 'textareafield';
			cfg.grow = false;
			if(prop.rows) cfg.rows = prop.rows;
			else cfg.rows = 2;
		} else cfg.xtype = 'textfield';
		Ext.apply(cfg, option);
		return cfg;
	},
	/**
	 * 构造多个form field配置。
	 * @param options 数组对象，每个元素为元模型名称，或者包含元模型属性名称以及手工指定的其他配置项的JSON对象。
	 * @return 数组对象，每个元素为一个form field配置对象。
	 */
	fields: function(options, fieldsDefault){
		var meta = this.$metaModel;
		var result = [];
		if(!options || options.length<=0) return result;
		for(var i=0; i<options.length; i++)
			result.push(this.field(options[i], fieldsDefault));
		return result;
	},
	/**
	 * 
	 */
	hboxFieldPanel: function(panelOptions, rows){
		var cfg = Ext.apply({ xtype:'fieldset', layout:'anchor', collapsible:true, defaults:{anchor:'100%'}, items:[] }, panelOptions);
		if(!rows || rows.length<=0) return cfg;
		for(var i=0; i<rows.length; i++){
			cfg.items.push({xtype:'container',layout:'hbox', cls:'form-row-container', itemId:'fieldset-ct-' + i
				, items:this.fields(rows[i], panelOptions.fieldsDefault)});
		}
		return cfg;
	},
	cmd: function(name, form, ev){
		var cfg = name;
		var n = null;
		if(typeof name == 'string')	{ n = name; cfg = {}; }
		else if(cfg.name) n = cfg.name;
		if(n){
			var btn = this.registeredCommands[n];
			if(!btn) throw 'Button "'+n+'" not defined, FormBuilder.buildButton() failure!';
			Ext.applyIf(cfg, btn.cfg);
			cfg.handler = btn.buildHandler(form, ev);
		}
		return cfg;
	},
	getField: function(container, panelId, fieldId){
		if(!container) {
			Ext.log('getField(): container is null');
			return null;
		}
		var panel = container.getComponent(panelId);
		if(!panel) {
			Ext.log('getField(): '+panelId+' not found');
			return null;
		}
		for(var i=0; i<10; i++){
			var row = panel.getComponent('fieldset-ct-' + i);
			if(row==null) {
				Ext.log('getField(): all panels have been searched, field not found');
				return null;
			}
			var field = row.getComponent(fieldId);
			if(field) {
				Ext.log('getField(): field "'+fieldId+'" found');
				return field;
			}
		}
		Ext.log('getField(): all panels have been searched, field not found');
		return null;
	}
});