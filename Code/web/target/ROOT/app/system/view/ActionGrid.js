Ext.define('ECF.app.system.view.ActionGrid', {
    requires:['ECF.app.system.metamodel.Action'],
    extend:'Ext.grid.Panel',

    cls:'bef-tab-grid',
    header:false, stateful:false, 
    selModel:{ selType: 'cellmodel' },
    viewConfig:{stripeRows: true}, //相邻两行背景颜色交叉变化
    plugins:[Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 })],
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.Action, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('find');
    	this.columns = [
    	    //grid的第一列：编辑按钮
    	    gb.actionCol(me.app, {width:35, items:['edit']})
    	    , gb.col({name:'fnName', width:100})
	        , gb.col({name:'actKey', editable:true, width:120})
	        , gb.col({name:'actName', editable:true, width:140})
	        , gb.col({name:'status', editable:true, width:55})
	        , gb.col({name:'url', editable:true})
	        , gb.col({name:'remark', editable:true})
	    ]; //this.columns = [

    	//顶部按钮工具栏
	    this.tbar = [
	        gb.tbarCmd(me.app, 'add'), 
	        { xtype: 'tbseparator' },
	        gb.tbarCmd(me.app, 'save'),
	        { xtype: 'tbseparator' },
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
	                {name:'fnName', id:'qryFnName', labelWidth:65, width:180},
	                {name:'actKey', id:'qryActKey', labelWidth:65, width:180},
	                {name:'actName', id:'qryActName', labelWidth:65, width:180},
	                {name:'remark', id:'qryRemark', labelWidth:35, width:180},
	                {name:'status', id:'qryStatus', labelWidth:35, width:120, value:1}
	            ]])
	       	]
	    }];
        
        me.callParent();
    }
});