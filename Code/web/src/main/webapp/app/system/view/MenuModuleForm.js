Ext.define('ECF.app.system.view.MenuModuleForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.Menu'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'编辑模块', closeAction:'destroy', 
    width:500, y:40, x:200, resizable:false, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.Menu, fb = meta.getFormBuilder();
    	
    	this.items = [{
    		xtype:'form', itemId:'sys-menu-form', border:false, bodyPadding:10, 
    		fieldDefaults:{enforceMaxLength:true, labelAlign:'right', msgTarget:'qtip'},
            
            items: [
                fb.hboxFieldPanel({id:'panel-menu-module-info',title:'模块资料', fieldsDefault:{labelWidth:70}}, [
                	[{name:'mid', hidden:true}, {name:'name', width:250}, {name:'status', width:150}],
                    [{name:'orderBy', width:250}, {name:'mtype', hidden:true},{name:'pid', hidden:true}]
                ]), 
                fb.hboxFieldPanel({id:'panel-log-info', title:'日志信息'}, [
                    [{name:'updateUser', readonly:true, width:200, labelWidth:90}
                    	, {name:'updateTime', readonly:true, width:220, labelWidth:90}]
                ])
            ],
            
            buttons: [
	            fb.cmd('ok', me, 'save-menu'), 
	            fb.cmd('close', me)
            ], 
            buttonAlign:'center'
    	}];
        
        this.callParent();
    }
});