Ext.define('ECF.app.system.view.MenuGrid', {
    requires:['ECF.app.system.metamodel.Menu'],
    extend:'Ext.tree.Panel',

    cls:'bef-tab-grid', viewConfig:{stripeRows:true}, //相邻两行背景颜色交叉变化
    stateful:false, collapsible:false, useArrows:false, rootVisible:false, singleExpand:false, 
    
    initComponent: function() {
    	var me = this, meta = ECF.app.system.metamodel.Menu, gb = meta.getGridBuilder();
    	
    	//为grid设置数据仓库
    	this.store = meta.getStore('menutree', 'Ext.data.TreeStore', true);
    	this.columns = [
    	    gb.actionCol(me.app, {width:35, items:['edit']}),{
			    xtype:'treecolumn', 
			    dataIndex:'name', text:'菜单名称',
			    width:160, sortable: false, menuDisabled:true
			}
    	    , gb.col({name:'status', width:40})
	        , gb.col({name:'orderBy', width:40})
	        , gb.col({name:'mtype', width:80})
	        , gb.col({name:'mdata'})
	        , gb.col({name:'updateUser', width:80})
	        , gb.col({name:'updateTime', width:135})
	    ]; //this.columns = [

    	//顶部按钮工具栏
	   this.tbar = [
	        gb.tbarCmd(me.app, {text:'创建模块', event:'cmd-add-module', icon:'/resources/images/cmd-add-folder.png'}), 
	        { xtype: 'tbseparator' },
	        gb.tbarCmd(me.app, {text:'创建菜单项', event:'cmd-add-item', icon:'/resources/images/cmd-add-item.png'}),
	        { xtype: 'tbseparator' }
	    ];
        
        me.callParent(arguments);
    }
});