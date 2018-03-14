Ext.define('ECF.app.system.view.RoleGrid', {
    requires:['ECF.app.system.metamodel.Role'],
    extend:'Ext.grid.Panel',

    cls:'bef-tab-grid',
    header:false, stateful:false, 
    selModel:{ selType: 'cellmodel' },
    viewConfig:{stripeRows: true}, //相邻两行背景颜色交叉变化
    plugins:[Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 })],
    uuid:'role-list-grid',
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.Role, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('find');
    	this.columns = [
    	    //grid的第一列：编辑按钮
    	    gb.actionCol(me.app, {width:35, items:['edit']})
	        , gb.col({name:'roleName', editable:true, width:140})
	        , gb.col({name:'status', editable:true, width:60})
	        , gb.col({name:'remark', editable:true})
	        , gb.col({name:'updateUser', width:80})
	        , gb.col({name:'updateTime', width:135})
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
	            gb.qform(this, [[
	                {name:'roleName', id:'qryRoleName', labelWidth:65, width:150},
	                {name:'status', id:'qryStatus', labelWidth:40, width:120, value:1},
	            ]])
	       	]
	    }];
        
        me.callParent();
    }
});