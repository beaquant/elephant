Ext.define('ECF.app.system.view.UserRoleAssignForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.Role'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'分配用户角色', closeAction:'destroy', autoShow:true, resizable:false,
    width:500, height:350, y:40, x:200,
    layout: 'hbox',
    buttonAlign:'center',
    
	initComponent: function() {
		var meta = ECF.app.system.metamodel.Role, me = this;
		var assignedStore = meta.getStore('user-role-assigned'), unassignedStore = meta.getStore('user-role-unassigned');
		me.app.setQueryParam(assignedStore, {userId:me.userId});
		assignedStore.load();
		me.app.setQueryParam(unassignedStore, {userId:me.userId});
		unassignedStore.load();
		this.items = [{
			xtype:'panel', layout:'fit', title:me.userName + '(' + me.loginId + ') 的角色', itemId:'panel-assigned',
			width:232, height:'100%', border:false, bodyPadding:3,
			items:[{
				xtype:'dataview', autoScroll:true, emptyText:'未分配任何角色', itemId:'dv-assigned',
				store:assignedStore,
				tpl:'<tpl for="."><div class="user-role-item"><span>{roleName}</span></div></tpl>',
				itemSelector:'div.user-role-item', 
				multiSelect:true, selectedItemCls:'user-role-item-selected', 
				trackOver:true, overItemCls:'user-role-item-over'
			}]
		},{
			xtype:'panel',
			width:35, height:'100%',
			layout:{ type:'vbox', align:'center', pack:'center' }, 
			bodyPadding:5, border:'0 1 0 1', bodyStyle:'border-top:none;border-bottom:none;',
			defaults:{ xtype:'button', width:22 },
			items: [
				{ iconCls:'cmd-move-right', 
					handler: function(){
						if(me.app) me.app.fireEvent('user-role-unassign', me);
					} 
				},
				{ iconCls:'cmd-move-left', margin:'15 0 0 0',
					handler: function(){
						if(me.app) me.app.fireEvent('user-role-assign', me);
					}
				}
			]
		},{
			xtype:'panel', layout:'fit', title:'未分配的系统角色', itemId:'panel-unassigned',
			width:220, height:'100%', border:false, bodyPadding:3,
			items:[{
				xtype:'dataview', autoScroll:true, emptyText:'无任何有效的系统角色', itemId:'dv-unassigned',
				store:unassignedStore,
				tpl:'<tpl for="."><div class="user-role-item"><span>{roleName}</span></div></tpl>',
				itemSelector:'div.user-role-item', 
				multiSelect:true, selectedItemCls:'user-role-item-selected', 
				trackOver:true, overItemCls:'user-role-item-over'
			}]
		}];
		this.buttons = [{
	    	icon:'/resources/images/cmd-close.png', iconCls:'button-cmd', text:'close',
	    	handler: function(){
	    		me.close();
	    	}
	    }]; 
		this.callParent(arguments);
	}
});