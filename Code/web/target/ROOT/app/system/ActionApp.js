Ext.define('ECF.app.system.ActionApp', {
    extend: 'ECF.app.AppBase', //从ECF.app.AppBase继承
    requires: ['ECF.app.system.view.ActionGrid', 'ECF.app.system.metamodel.Action'],
    
    main: function(tab, menuItem){
    	var me = this, meta = ECF.app.system.metamodel.Action, gb = meta.getGridBuilder();
    	var grid = Ext.create('ECF.app.system.view.ActionGrid', {app:me}); //创建grid控件对象
    	me.addTabItem(tab, grid); //将grid对象添加到tab中
    	
    	me.on('grid-cmd-edit', function(grid, rowIndex, colIndex) {
    		//将功能区屏蔽，使编辑表单达到模态窗口效果
    		me.getMenuFrame(tab).mask();
    		//创建编辑表单Form
    		var form = Ext.create('ECF.app.system.view.ActionForm', { app:me, renderTo:me.getRenderTo(tab), editMode:'edit' });
    		form.show();
    		//取当前行的数据记录
    		var record = grid.getStore().getAt(rowIndex);
    		//用数据记录填充编辑表单
    		Ext.getCmp('sys-action-form').getForm().loadRecord(record);
    		form.on('destroy', function(){
    			//编辑表单关闭时去掉功能区的屏蔽
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.ActionForm destroyed');
    		});
    	});
    	
    	me.on('tbar-cmd-create', function() {
    		var form = Ext.create('ECF.app.system.view.ActionForm', { app:me, renderTo:me.getRenderTo(tab), editMode:'create' });
    		form.show();
    		me.getMenuFrame(tab).mask();
    		form.on('destroy', function(){
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.ActionForm destroyed');
    		});
    	});
    	
    	me.on('form-cmd-save', function(form){
    		if(form.editMode!='edit' && form.editMode!='create'){
    			ECF.msg.error('系统错误', '表单的编辑模式editMode不正确');
    			return;
    		}
    		var record = Ext.getCmp('sys-action-form').getForm().getValues();
    		form.editMode=='edit'
    			? meta.update(record, function(data){ grid.getStore().reload(); } ) 
    			: meta.create(record, function(data){ grid.getStore().reload(); } );
    		form.close();
    	});
    	
    	me.on('tbar-cmd-save', function() {
    		meta.updateGrid(grid);
    	});
    	
    	me.on('tbar-cmd-query', function() {
    		Ext.log('action query event');
    		//调用父类AppBase的setQueryParam，为store设置查询参数
    		var status = gb.getQFormValue(grid, 'qryStatus');
    		if(status==='') status = null;
    		me.setQueryParam(
    			grid.getStore(), {
        			actKey: gb.getQFormValue(grid, 'qryActKey'), 
        			actName: gb.getQFormValue(grid, 'qryActName'),
        			remark: gb.getQFormValue(grid, 'qryRemark'),
        			status: status ,
        			menuName: gb.getQFormValue(grid, 'qryFnName') 
        		}
    		);
    		//store使用新的查询参数调用服务器API重新加载数据
    		grid.getStore().loadPage(1);
    	});
    	
    	//页面使用默认查询条件加载数据
    	//me.fireEvent('tbar-cmd-query');
    }
});