Ext.define('ECF.app.system.PermissionApp', {
    extend: 'ECF.app.AppBase', //从ECF.app.AppBase继承
    requires: ['ECF.app.system.view.PermissionOwnerGrid'
	       , 'ECF.app.system.view.PermissionResourceForm'
	       , 'ECF.app.system.metamodel.PermissionOwner'
	       , 'ECF.app.system.metamodel.PermissionResource'],
    
    main: function(tab, menuItem){
    	var me = this
    		, meta = ECF.app.system.metamodel.PermissionOwner
    		, gb = meta.getGridBuilder()
    		, metaRes = ECF.app.system.metamodel.PermissionResource;
    	var grid = Ext.create('ECF.app.system.view.PermissionOwnerGrid', {app:me}); //创建grid控件对象
    	me.addTabItem(tab, grid); //将grid对象添加到tab中
    	
    	me.on('grid-cmd-assign', function(grid, rowIndex, colIndex) {
    		var record = grid.getStore().getAt(rowIndex);
    		//将功能区屏蔽，使编辑表单达到模态窗口效果
    		me.getMenuFrame(tab).mask();
    		//创建编辑表单Form
    		var win = Ext.create('ECF.app.system.view.PermissionResourceForm'
    				, { app:me, renderTo:me.getRenderTo(tab)
    					, permOwnerId:record.get('id'), permOwnerName:record.get('name'), permOwnerType:record.get('type') });
    		win.show();
    		win.on('destroy', function(){
    			//编辑表单关闭时去掉功能区的屏蔽
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.PermissionResourceForm destroyed');
    		});
    	});
    	
    	me.on('tbar-cmd-query', function() {
    		Ext.log('permission owner query event');
    		//调用父类AppBase的setQueryParam，为store设置查询参数
    		var status = gb.getQFormValue(grid, 'qryStatus');
    		if(status==='') status = null;
    		me.setQueryParam(
    			grid.getStore(), {
        			type: gb.getQFormValue(grid, 'qryType'), 
        			name: gb.getQFormValue(grid, 'qryName'),
        			status: status 
        		}
    		);
    		//store使用新的查询参数调用服务器API重新加载数据
    		grid.getStore().load({ pi:1 });
    	});
    	
    	me.on('form-cmd-save', function(form){
    		var tree = form.getComponent('sys-perm-resource-form');
    		var records = tree.getView().getChecked();
    		var ids = [];
    		Ext.each(records, function(rec){
    			ids.push(rec.get('rid'));
    		});
    		metaRes.doPost({ownerId:form.permOwnerId, ownerType:form.permOwnerType, ids:ids.join(',')}
    			, function(){ form.close(); }
    			, 'assign');
    	});
    }
});