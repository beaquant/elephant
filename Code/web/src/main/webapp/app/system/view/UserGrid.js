Ext.define('ECF.app.system.view.UserGrid', {
    requires:['ECF.app.system.metamodel.User','ECF.app.system.view.UserRoleAssignForm', 'ECF.app.system.view.UserForm'],
    extend:'Ext.grid.Panel',

    cls:'bef-tab-grid',
    header:false, stateful:false, 
    selModel:{ selType: 'cellmodel' },
    viewConfig:{stripeRows: true}, //相邻两行背景颜色交叉变化
    plugins:[Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 })],
    uuid:'user-list-grid',
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.User, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('find');
    	this.columns = [
    	    //grid的第一列：编辑按钮
    	    gb.actionCol(me.app, {width:70, items:['edit', 'role']})
    	    //name: 必须是元模型ECF.app.system.User中定义的属性名称
    	    //editable: 该字段在grid上可以直接编辑保存
    	    //width: 字段宽度，没有指定width时该字段采用自适应宽度
	        , gb.col({name:'loginId', editable:true})
	        , gb.col({name:'userName', editable:true})
	        , gb.col({name:'status', editable:true, width:60})
	        , gb.col({name:'email', editable:true})
	        , gb.col({name:'phone', editable:true})
	        , gb.col({name:'mobile', editable:true})
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
	            //Grid顶部查询条件Form
	            gb.qform(this, [[
	                {name:'loginId', id:'qryLoginId', labelWidth:65, width:150},
	                {name:'userName', id:'qryUserName', labelWidth:65, width:150},
	                {name:'status', id:'qryStatus', labelWidth:40, width:120, value:1},
	            ]])
	       	]
	    }];
        
        me.callParent();
    }
});