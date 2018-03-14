Ext.define('ECF.app.system.view.MenuItemForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.Menu'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'编辑菜单项', closeAction:'destroy', 
    width:600, y:40, x:200, resizable:false, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.Menu, fb = meta.getFormBuilder();
    	
    	this.items = [{
    		xtype:'form', itemId:'sys-menu-form', border:false, bodyPadding:10, 
    		fieldDefaults:{enforceMaxLength:true, labelAlign:'right', msgTarget:'qtip'},
            
            items: [{
                	 xtype:'fieldset', layout:'anchor', title:'使用说明', collapsible:true, cls:'tips', defaults:{anchor:'100%'}, items:[
						{html:'<div class="tips-text" style="width:100%;">'
							+'排序：决定菜单的显示排列顺序，必须为大于0的整数，菜单显示时将按照数字倒排序<br />'
							+ '菜单类型：为ExtJS菜单时，菜单地址必须设置为一个ExtJS app类名；为普通菜单时，菜单地址必须设置为一个页面的url地址'
							+'</div>'}
                	 ] 
                },
                fb.hboxFieldPanel({id:'panel-menu-item-info',title:'菜单项资料', fieldsDefault:{labelWidth:70}}, [
                	[{name:'mid', hidden:true}, {name:'name', width:300}, {name:'status', width:250}],
                    [{name:'mtype', width:300, includes:['app','url']}, {name:'orderBy', width:250}],
                    [{name:'mdata', width:550}, {name:'pid', hidden:true}]
                ]), 
                fb.hboxFieldPanel({id:'panel-log-info', title:'日志信息'}, [
                    [{name:'updateUser', readonly:true, width:250, labelWidth:90}
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