Ext.define('ECF.app.system.MenuApp', {
    extend: 'ECF.app.AppBase', //从ECF.app.AppBase继承
    requires: ['ECF.app.system.view.MenuGrid'],
    
    main: function(tab, menuItem){
    	var me = this, meta = ECF.app.system.metamodel.Menu, roleMeta = ECF.app.system.metamodel.Menu
    		, gb = meta.getGridBuilder();
    	//创建grid控件对象
    	var tree = Ext.create('ECF.app.system.view.MenuGrid', {app:me, renderTo:me.getRenderTo(tab)});
    	//将grid对象添加到tab中
    	//必须让TreePanel自动触发TreeStore加载数据，否则会无法正常展示树状grid，报错：can not read 'internelId'的错误
    	me.addTabItem(tab, tree);
    	
    	//添加模块按钮事件处理
    	me.on('cmd-add-module', function(){
    		addMenuHandler('mdl', 0);
    	});
    	
    	//添加菜单项按钮事件处理
    	me.on('cmd-add-item', function(){
    		var selected = tree.getSelectionModel().getSelection();
    		if(selected==null || selected.length<=0) {
    			ECF.msg.info('操作说明','请在列表中单击鼠标选择一个模块，然后再执行【创建菜单项】的操作');
    			return;
    		}
    		//只允许选择单行记录
    		if(selected[0].get('mtype')!='mdl'){
    			ECF.msg.info('操作说明','请在列表中单击鼠标选择一个模块，然后再执行【创建菜单项】的操作。\r当前选择的【'+selected[0].get('name')+'】是一个菜单项，不是模块');
    			return;
    		}
    		addMenuHandler('', selected[0].get('mid'));
    	});
    	
    	function addMenuHandler(mtype, pid){
    		var formName = '';
    		if(mtype=='mdl') formName = 'ECF.app.system.view.MenuModuleForm';
    		else formName = 'ECF.app.system.view.MenuItemForm';
    		
    		var form = Ext.create(formName, { app:me, renderTo:me.getRenderTo(tab), editMode:'create',
    			mtype:mtype, pid:pid});
    		form.show();
    		me.getMenuFrame(tab).mask();
    		form.on('destroy', function(){
    			//编辑表单关闭时去掉功能区的屏蔽
    			me.getMenuFrame(tab).unmask();
    			Ext.log(formName + ' destroyed');
    		});
    	}
    	
    	me.on('grid-cmd-edit', function(tree, rowIndex, colIndex){
    		var record = tree.getStore().getAt(rowIndex);
    		//编辑菜单项
    		var formName = '';
    		if(record.get('mtype')=='app' || record.get('mtype')=='url') 
    			formName = 'ECF.app.system.view.MenuItemForm';
    		else if(record.get('mtype')=='mdl')
    			formName = 'ECF.app.system.view.MenuModuleForm';
    		if(formName=='') {
    			ECF.msg.error('系统错误', '无效的菜单类型：'+record.get('mtype'));
    			return;
    		}
    		
			var form = Ext.create(formName, { app:me, renderTo:me.getRenderTo(tab), editMode:'edit' });
    		form.show();
    		me.getMenuFrame(tab).mask();
    		form.getComponent('sys-menu-form').getForm().loadRecord(record);
    		form.on('destroy', function(){
    			//编辑表单关闭时去掉功能区的屏蔽
    			me.getMenuFrame(tab).unmask();
    			Ext.log(formName + ' destroyed');
    		});
    	});
    	
    	me.on('save-menu', function(form){
    		if(form.editMode!='edit' && form.editMode!='create'){
    			ECF.msg.error('系统错误', '表单的编辑模式editMode不正确');
    			return;
    		}
    		var record = form.getComponent('sys-menu-form').getForm().getValues();
    		if(form.editMode=='create'){
    			if(form.mtype) { record.mtype = form.mtype; }
    			if(form.pid) { record.pid = form.pid; }
    		}
    		form.editMode=='edit'
    			? meta.update(record, function(data){ tree.getStore().reload(); } ) 
    			: meta.create(record, function(data){ tree.getStore().reload(); } );
    		form.close();
    	});
    }
});