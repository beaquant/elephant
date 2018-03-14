/**
 * 用户管理的application。
 * 
 * app类名的命名规范：Ext.app.[ModuleName].[ApplicationName]App。
 * 
 * 任何一个菜单项都对应一个app，例如[系统管理]=>[用户管理]菜单项使用的是ECF.app.system.UserApp。
 * 1. 在CBS后台管理中设置菜单项时，必须指定这个菜单项使用的app完整类名；
 * 2. 在CBS系统中的菜单区域鼠标点击某个菜单项时，系统将：
 * 	  2.1 在功能区域创建一个tab；
 *    2.2 创建Ext.app.[ModuleName].[ApplicationName]App对象，并执行其main方法；
 * 3. 任何一个app，必须在main方法中进行以下工作：
 *    3.1 创建界面对象，并显示在工作区tab中；例如用户管理，则应当创建显示用户列表的ECF.app.system.view.UserGrid对象并显示在工作区tab中；
 *    3.2 完成各种事件处理绑定及逻辑处理；
 */
Ext.define('ECF.app.system.UserApp', {
    extend: 'ECF.app.AppBase', //任何一个app都必须从ECF.app.AppBase继承
    //这个js文件中需要用到的各种类，与java的import类似，ExtJS会动态自动加载这些类。加载相关的路径设置，
    //参考index.html中Ext.Loader.setPath部分。
    requires: ['ECF.app.system.view.UserGrid', 'ECF.app.system.metamodel.User', 'ECF.app.system.metamodel.Role'],
    
    /**
     * 每个app都必须有一个main函数。
     * @param tab 工作区的tab对象。
     * @param menuItem 当前点击的菜单项对象。
     */
    main: function(tab, menuItem){
    	var me = this
    		//用户的元数据定义
    		, meta = ECF.app.system.metamodel.User
    		//角色的元数据定义
    		, roleMeta = ECF.app.system.metamodel.Role
    		//构建Grid对象的辅助工具类
    		, gb = meta.getGridBuilder();
    	//创建用户列表grid对象
    	//第二个参数对象上的每一个属性都会设置到UserGrid对象上，例如在UserGrid的initComponent方法中，可以通过this.app得到
    	//这里传过去的me对象，即ECF.app.system.UserApp对象
    	var grid = Ext.create('ECF.app.system.view.UserGrid', {app:me});
    	//将grid对象添加到tab中。addTabItem为ECF.app.AppBase中的方法
    	me.addTabItem(tab, grid); 
    	
    	//Grid的第一列编辑按钮事件处理
    	me.on('grid-cmd-edit', function(grid, rowIndex, colIndex) {
    		//将功能区屏蔽，使编辑表单达到模态窗口效果
    		me.getMenuFrame(tab).mask();
    		//创建编辑表单Form对象。
    		//跟前面创建grid对象相同，第二个参数对象上的每一个属性都会设置到ECF.app.system.view.UserForm对象上。
    		var form = Ext.create('ECF.app.system.view.UserForm', { app:me, renderTo:me.getRenderTo(tab), editMode:'edit' });
    		form.show();
    		//从grid中取当前这一行的数据记录
    		var record = grid.getStore().getAt(rowIndex);
    		//用数据记录填充表单Form（Form上面的各个字段就有值了）
    		Ext.getCmp('sys-user-form').getForm().loadRecord(record);
    		form.on('destroy', function(){
    			//编辑表单关闭时去掉功能区的屏蔽
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.UserForm destroyed');
    		});
    	});
    	
    	 //行首角色编辑按钮事件处理
    	me.on('grid-cmd-role', function(grid, rowIndex, colIndex){
    		me.getMenuFrame(tab).mask();
    		var record = grid.getStore().getAt(rowIndex);
    		var form = Ext.create('ECF.app.system.view.UserRoleAssignForm'
    			, { app:me, renderTo:me.getRenderTo(tab), 
    				userId:record.get('userId'), userName:record.get('userName'), loginId:record.get('loginId')});
    		form.on('destroy', function(){
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.UserRoleAssignForm destroyed');
    		});
    	});
    	
    	//为用户分配角色的按钮事件处理
    	me.on('user-role-assign', function(form){
    		Ext.log('[sys][role] Entering assign roles to user event, userId=' + form.userId + '.');
    		//获取分配角色表单上的未分配角色列表所在的panel对象
    		var dv = form.getComponent('panel-unassigned');
    		if(!dv) {
    			Ext.log('[sys][role] Assigned roles dataview not found');
    			return;
    		}
    		//获取未分配角色列表的DataView对象
    		var dv = dv.getComponent('dv-unassigned');
    		if(!dv){
    			Ext.log('[sys][role] Assigned roles dataview not found');
    			return;
    		}
    		//获取未分配角色列表dataview中选择的记录集
    		var roleIds = [];
    		Ext.each(dv.getRecords(dv.getSelectedNodes()), function(record){
    			roleIds.push(record.get('roleId'));
    		});
    		if(roleIds.length<=0) return;
    		//向服务器提交，为用户分配角色
    		//form.userId：在grid-cmd-role事件处理中创建form对象时传递过去的参数
    		roleMeta.doPost({userId:form.userId, roleIds:roleIds.join(',')}, function(){
    			//未分配角色列表重新刷新数据（刚刚分配给用户的角色将从未分配列表中消失）
    			dv.getStore().reload();
    			//已分配角色列表重新刷新数据（刚刚分配给用户的角色将出现在这里）
    			form.getComponent('panel-assigned').getComponent('dv-assigned').getStore().reload();
    		}, 'user-role-assign');
    	});
    	
    	//取消分配给用户的角色，处理同user-role-assign
    	me.on('user-role-unassign', function(form){
    		Ext.log('[sys][role] Entering unassign roles to user event, userId=' + form.userId + '.');
    		var dv = form.getComponent('panel-assigned');
    		if(!dv) {
    			Ext.log('[sys][role] Unassigned roles dataview not found');
    			return;
    		}
    		var dv = dv.getComponent('dv-assigned');
    		if(!dv){
    			Ext.log('[sys][role] Unassigned roles dataview not found');
    			return;
    		}
    		var roleIds = [];
    		Ext.each(dv.getRecords(dv.getSelectedNodes()), function(record){
    			roleIds.push(record.get('roleId'));
    		});
    		if(roleIds.length<=0) return;
    		roleMeta.doPost({userId:form.userId, roleIds:roleIds.join(',')}, function(){
    			dv.getStore().reload();
    			form.getComponent('panel-unassigned').getComponent('dv-unassigned').getStore().reload();
    		}, 'user-role-unassign');
    	});
    	
    	//Grid工具栏上创建按钮的事件处理
    	me.on('tbar-cmd-create', function() {
    		var form = Ext.create('ECF.app.system.view.UserForm', { app:me, renderTo:me.getRenderTo(tab), editMode:'create' });
    		form.show();
    		me.getMenuFrame(tab).mask();
    		form.on('destroy', function(){
    			me.getMenuFrame(tab).unmask();
    			Ext.log('ECF.app.system.view.UserForm destroyed');
    		});
    	});
    	
    	//编辑表单弹窗上的保存按钮事件处理
    	me.on('form-cmd-save', function(form){
    		if(form.editMode!='edit' && form.editMode!='create'){
    			ECF.msg.error('系统错误', '表单的编辑模式editMode不正确');
    			return;
    		}
    		//获取表单上用户输入、选择的值
    		var record = Ext.getCmp('sys-user-form').getForm().getValues();
    		form.editMode=='edit'
    			//编辑用户资料时执行update操作（执行完毕后刷新grid）
    			? meta.update(record, function(data){ grid.getStore().reload(); } )
    			//创建用户资料时执行create操作
    			: meta.create(record, function(data){ grid.getStore().reload(); } );
    		form.close();
    	});
    	
    	//Grid工具栏上的保存按钮事件处理
    	me.on('tbar-cmd-save', function() {
    		//ECF.app.MetaBase中的工具方法，方便批量更新grid上的修改
    		meta.updateGrid(grid);
    	});

    	me.on('tbar-cmd-query', function() {
    		var status = gb.getQFormValue(grid, 'qryStatus');
    		if(status==='') status=null;
    		//调用父类AppBase的setQueryParam，为store设置查询条件参数值
    		me.setQueryParam(
    			grid.getStore(), {
        			name: gb.getQFormValue(grid, 'qryUserName'), //获取用户输入的查询条件值
        			loginId: gb.getQFormValue(grid, 'qryLoginId'),
        			status: status
        		}
    		);
    		//store使用新的查询参数调用服务器API重新加载数据
    		grid.getStore().loadPage(1);
    	});
    	
    	//页面使用默认查询条件加载数据（备注：目前页面改成第一次打开时默认不显示任何数据）
    	//me.fireEvent('tbar-cmd-query');
    }
});