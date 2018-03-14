Ext.define('ECF.app.system.view.AccessLogGrid', {
    requires:['ECF.app.system.metamodel.AccessLog'],
    extend:'Ext.grid.Panel',

    cls:'bef-tab-grid',
    header:false, stateful:false, 
    viewConfig:{stripeRows: true}, //相邻两行背景颜色交叉变化
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.AccessLog, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('find');
    	this.columns = [
	        gb.col({name:'logTime', width:130, origin:{locked:true}})
	        , gb.col({name:'logType', width:40, origin:{locked:true}})
	        , gb.col({name:'success', width:40, origin:{locked:true}})
	        , gb.col({name:'userName', width:60, origin:{locked:true}})
	        , gb.col({name:'clientIp', editable:true, width:105, origin:{lockable:false}})
	        , gb.col({name:'visitUrl', editable:true, width:180})
	        , gb.col({name:'paramValue', editable:true, width:300})
	        , gb.col({name:'sessionId', editable:true, width:250})
	        , gb.col({name:'clientToken', editable:true, width:250})
	        , gb.col({name:'cookieValue', editable:true, width:300})
	        , gb.col({name:'refererUrl', width:250})
	        , gb.col({name:'agent', width:250})
	        , gb.col({name:'errorMsg', width:150})
	    ]; //this.columns = [

    	//顶部按钮工具栏
	    this.tbar = [
	        gb.tbarCmd(me.app, 'query'),
	        { xtype: 'tbseparator' }
	    ];
	    //底部分页控件工具栏
	    this.bbar = gb.pagingBar(this.store);
	    
	    //顶部查询条件框
	    var startTime = new Date();
	    //startTime = startTime.setTime(startTime.getTime()-1000*3600*24*3);
	    this.dockedItems = [{
	        xtype: 'toolbar', dock: 'top',
	        items: [
	            //Grid顶部查询条件Form
	            gb.qform(this, [[
	                {name:'startTime', id:'qryStartTime', label:'时间', labelWidth:40, width:140, cls:'no-right-margin'
	                	, datatype:'date', domaintype:'date', value:startTime, maxValue:new Date()},
	                {name:'endTime', id:'qryEndTime', label:'->', labelWidth:20, width:120
	                	, datatype:'date', domaintype:'date', value:new Date(), maxValue:new Date()},
	                {name:'userName', id:'qryUserName', labelWidth:45, width:120, emptyText:'精确匹配'},
	                {name:'visitUrl', id:'qryVisitUrl', labelWidth:55, width:200, emptyText:'模糊匹配'},
	                {name:'clientIp', id:'qryClientIp', labelWidth:55, width:160, emptyText:'精确匹配'},
	                {name:'sessionId', id:'qrySessionId', labelWidth:45, width:180, emptyText:'精确匹配'}
	            ]])
	       	]
	    }];
        
        me.callParent();
    }
});