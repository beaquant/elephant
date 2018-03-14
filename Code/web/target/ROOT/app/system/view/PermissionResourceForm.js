Ext.define('ECF.app.system.view.PermissionResourceForm', {
	requires:['Ext.tree.Panel', 'ECF.app.system.metamodel.PermissionResource'],
    extend:'Ext.window.Window',
    
    constrain:true, 
    title:'权限管理', closeAction:'destroy', 
    width:400, y:40, x:200, resizable:true, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.PermissionResource
    		, gb = meta.getGridBuilder(), fb = meta.getFormBuilder();
    	var store = meta.getStore('find-resource-tree', 'Ext.data.TreeStore', true);
    	me.app.setQueryParam(store, {ownerId:me.permOwnerId, ownerType:me.permOwnerType});
    	
    	this.title = '分配权限：' + me.permOwnerName;
    	this.items = [{
    		xtype:'treepanel',
    		border:false, bodyPadding:10, itemId:'sys-perm-resource-form',
    		header:false, stateful:false, 
    		autoScroll:true, focusOnToFront:true, shadow:false,
    		collapsible:false, useArrows:false, rootVisible:false, singleExpand:false,
    		store:store, height:400,
    		columns: [{
				    xtype:'treecolumn', 
				    dataIndex:'name', text:'名称',
				    width:280, sortable:false, menuDisabled:true
	            },
	            gb.col({name:'status', width:50})
	        ]
    	}];
    	
        this.buttons = [ fb.cmd('ok', me), fb.cmd('close', me)];
        this.buttonAlign = 'center';
        
        this.callParent();
    }
});