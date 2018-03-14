/**
 * ECF的Extjs应用（controller）基类。
 * @author: 刘志斌
 * @date 2012-10-10
 */
Ext.define('ECF.app.AppBase', {
    mixins: {
        observable: 'Ext.util.Observable'
    },
    
    constructor: function(data){
    	var me = this;
    	me.mixins.observable.constructor.call(me);
    },
    
    setQueryParam: function(store, params){
    	if(!store || !store.getProxy()) return;
    	store.getProxy().extraParams = Ext.apply({}, params || {});
    },
    
    addTabItem: function(tab, cmp){
    	var menuFrame = Ext.ComponentQuery.query('#menu-frame-'+tab.miid);
    	if(!menuFrame || menuFrame.length<=0){
    		ECF.msg.error('系统错误', '无法获取菜单内容区框架对象：menu item id:'+tab.miid);
    		return;
    	}
    	menuFrame[0].add(cmp);
    },
    
    getMenuFrame: function(tab){
    	var menuFrame = Ext.ComponentQuery.query('#menu-frame-'+tab.miid);
    	if(!menuFrame || menuFrame.length<=0){
    		ECF.msg.error('系统错误', '无法获取菜单内容区框架对象：menu item id:'+tab.miid);
    		return null;
    	}
    	return menuFrame[0];
    },
    
    getRenderTo: function(tab){
    	var mf = this.getMenuFrame(tab);
    	return mf == null ? null : mf.body;
    },
    
	main: function(tab, menuItem) {}
});