Ext.define('ECF.app.system.view.ActionForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.Action', 'ECF.app.system.metamodel.Menu',
	    'Ext.ux.form.field.TreePickerField'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'系统操作', closeAction:'destroy', 
    width:500, minWidth:500, y:40, x:200, resizable:true, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.Action, fb = meta.getFormBuilder();
    	var menuMeta = ECF.app.system.metamodel.Menu, menuGb = menuMeta.getGridBuilder();

    	this.items = [{
    		xtype:'form', id:'sys-action-form', border:false, bodyPadding:10, 
    		fieldDefaults:{enforceMaxLength:true, labelAlign:'right', msgTarget:'qtip'},
            
            items: [
                fb.hboxFieldPanel({id:'panel-action-info',title:'系统操作资料', fieldsDefault:{labelWidth:70}}, [
                	[{name:'actId', hidden:true}, {name:'actName', width:430}],
                    [
                     	//用一个隐藏的field存放菜单项ID值
                     	{name:'fnId', hidden:true, id:me.id+'-fnId'},
                     	//使用自定义的，支持弹出树状grid选择框的field，来显示菜单项名称，以及弹出菜单项选择框
	                     {
	                    	xtype:'treepickerfield', $name:'fnName', id:me.id+'-fnName', 
	                    	fieldLabel:'<span class="required-field-flag">*</span>菜单项', 
	                    	labelWidth:70, width:250,
	                    	//matchFieldWidth:弹出框的宽度不受picker field宽度限制
	                    	matchFieldWidth:false,
	                    	//弹出框（Ext.tree.Panel）的配置选项
	                    	pickerConfig:{
	                    		width:450, height:350, columns:[
	                    		    //第一列显示菜单名称（treecolumn支持树状结构）
	                    		    {
									    xtype:'treecolumn', 
									    dataIndex:'name', text:'菜单名称',
									    width:160, sortable:false, menuDisabled:true
									},
									//第二列显示菜单项类型
									menuGb.col({name:'mtype', width:80}),
									//第三列显示菜单项地址
									menuGb.col({name:'mdata'})
	                    		],
	                    		//在tree panel中单击选择一条记录后的判断函数，
	                    		//返回true表示选择的记录符合条件，选择的记录值会填入fnId和fnName中，弹出框关闭；
	                    		//返回false表示选择的记录不符合条件，弹出框保持弹出状态，picker field的选择值不发生变化；
	                    		selectHandler: function(record){
	                    			//这里只允许选择菜单项（mtype为app或者url），不允许选择菜单模块
	                    			return record.get('mtype')!='mdl';
	                    		}
	                    	},
	                    	//弹出框的data store
	                    	pickerStore:menuMeta.getStore('menutree', 'Ext.data.TreeStore', true),
	                    	//form表单上存放菜单项ID值的field id，弹出框选择了记录后，会将被选记录的ID值设置到这个form表单field中；
	                    	ctValueFieldId:me.id+'-fnId', 
	                    	//form表单上存放菜单项名称的field id，弹出框选择了记录后，会将被选记录的名称值设置到这个form表单field中；
	                    	ctDisplayFieldId:me.id+'-fnName',
	                    	//弹出框的data store中，被选择记录的ID属性名称；
	                    	pkValueField:'mid', 
	                    	//弹出框的data store中，被选择记录的显示字段名称；
	                    	pkDisplayField:'name'
	                    },
	                    {name:'status', width:180}
	                ],
	                [{name:'actKey', width:250}],
                    [{name:'url', width:430}],
                    [{name:'remark', width:430}]
                ])
            ],
            
            buttons: [
	            fb.cmd('ok', me), 
	            fb.cmd('close', me)
            ], 
            buttonAlign:'center'
    	}];
        
        this.callParent();
    }
});