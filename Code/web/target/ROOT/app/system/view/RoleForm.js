Ext.define('ECF.app.system.view.RoleForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.Role'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'角色资料', closeAction:'destroy', 
    width:500, minWidth:500, y:40, x:200, resizable:true, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.Role, fb = meta.getFormBuilder();
    	
    	this.items = [{
    		xtype:'form', border:false, bodyPadding:10, id:'sys-role-form',
    		fieldDefaults:{enforceMaxLength:true, labelAlign:'right', msgTarget:'qtip'},
            
            items: [
                fb.hboxFieldPanel({id:'panel-role-info',title:'角色资料', fieldsDefault:{labelWidth:70}}, [
                	[{name:'roleId', hidden:true}, {name:'roleName', width:250}, {name:'status', width:180}],
                    [{name:'remark', width:430}]
                ]), 
                fb.hboxFieldPanel({id:'panel-log-info', title:'日志信息'}, [
                	[{name:'createUser', readonly:true, width:200, labelWidth:90}
                		, {name:'createTime', readonly:true, width:220, labelWidth:90}],
                    [{name:'updateUser', readonly:true, width:200, labelWidth:90}
                    	, {name:'updateTime', readonly:true, width:220, labelWidth:90}]
                ])
            ],
            
            buttons: [
	            fb.cmd('ok', me), 
	            fb.cmd('close', me)
            ], 
            buttonAlign: 'center'
    	}];
        
        this.callParent();
    }
});