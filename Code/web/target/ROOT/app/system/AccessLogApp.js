Ext.define('ECF.app.system.AccessLogApp', {
    extend: 'ECF.app.AppBase', //从ECF.app.AppBase继承
    requires: ['ECF.app.system.view.AccessLogGrid', 'ECF.app.system.metamodel.AccessLog'],
    
    main: function(tab, menuItem){
    	var me = this, meta = ECF.app.system.metamodel.AccessLog, gb = meta.getGridBuilder();
    	var grid = Ext.create('ECF.app.system.view.AccessLogGrid', {app:me}); //创建grid控件对象
    	me.addTabItem(tab, grid); //将grid对象添加到tab中
    	
    	me.on('tbar-cmd-query', function() {
    		//调用父类AppBase的setQueryParam，为store设置查询参数
    		me.setQueryParam(
    			grid.getStore(), {
        			startTime: gb.getQFormValue(grid, 'qryStartTime'), 
        			endTime: gb.getQFormValue(grid, 'qryEndTime'),
        			userName: gb.getQFormValue(grid, 'qryUserName'),
        			visitUrl: gb.getQFormValue(grid, 'qryVisitUrl'),
        			clientIp: gb.getQFormValue(grid, 'qryClientIp'),
        			sessionId: gb.getQFormValue(grid, 'qrySessionId') 
        		}
    		);
    		//store使用新的查询参数调用服务器API重新加载数据
    		grid.getStore().loadPage(1);
    	});
    }
});