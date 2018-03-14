Ext.define('ECF.app.system.view.PermissionOwnerGrid', {
    requires:['ECF.app.system.metamodel.PermissionOwner'],
    extend:'Ext.grid.Panel',

    cls:'bef-tab-grid',
    header:false, stateful:false, 
    viewConfig:{stripeRows: true}, //相邻两行背景颜色交叉变化
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.PermissionOwner, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('find');
    	this.columns = [
    	    //grid的第一列：编辑按钮
    	    gb.actionCol(me.app, {width:35, items:[{
    	    	icon:'/resources/images/cmd-lock.png', 
    	    	iconCls:'grid-cmd', 
    	    	event:'grid-cmd-assign'}]}
    	    )
    	    , gb.col({name:'status', width:60})
	        , gb.col({name:'name'})
	    ]; //this.columns = [

    	//顶部按钮工具栏
	    this.tbar = [
	        gb.tbarCmd(me.app, 'query'),
	        { xtype: 'tbseparator' }
	    ];
	    //底部分页控件工具栏
	    this.bbar = gb.pagingBar(this.store);
	    
	    //顶部查询条件框
	    this.dockedItems = [{
	        xtype: 'toolbar', dock: 'top',
	        items: [
	            gb.qform(me, [[
	                {name:'type', id:'qryType', noAll:true, labelWidth:80, width:150, value:2},
	                {name:'name', id:'qryName', labelWidth:35, width:180},
	                {name:'status', id:'qryStatus', labelWidth:35, width:120, value:1}
	            ]])
	       	]
	    }];
        
        me.callParent();
    }
});