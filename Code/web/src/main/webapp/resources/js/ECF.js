/**
 * Author: Richie, 刘志斌, yudi@sina.com
 * Date: 2012-09-13
 */
(function() {
if (typeof ECF === 'undefined' || !ECF) {
    var ecFramework = function() {
    };
   
    /**
     * 获取URL参数值。
     * @param name 参数名称。
     */
    ecFramework.prototype.getUrlParam = function(name){
       if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
          return decodeURIComponent(name[1]);
       return '';
    };
    
    ecFramework.prototype.htmlEncode = function(text){
		if(!text) return '';
		return text.replace(/\</g, "&lt;").replace(/\>/g, "&gt;").replace(/\n/g, "").replace(/\r/g, "<br />");
    };
    
    //***********************************************************
    // menus
    //***********************************************************
    ecFramework.prototype.menu = {
    	modules:[], openedItems:{},
    	init: function(modules){
    		if(!modules || modules.constructor!=Array || modules.length<=0) return;
    		this.modules = modules;
    	},
    	openMenu: function(item, tab){
    		this.openedItems['t-' + item.miid] = {mi:item, tab:tab};
    	},
    	closeMenu: function(miid){
    		this.openedItems['t-' + miid] = false;
    	},
    	hasOpened: function(miid){
    		return !!this.openedItems['t-' + miid];
    	},
    	getOpenMenuTab: function(miid){
    		return this.openedItems['t-' + miid].tab;
    	}
    };

    if (typeof Ext === 'undefined' || !Ext)
        throw "ExtJS framework not found!";

    ecFramework.prototype.request = function(con, o) {
    	con.request({
            params:o.params, scope:o.scope, disableCaching:o.disableCaching,
            success: function(response, options) {
                var data = null, decoded = true;
                try{
                	data = Ext.JSON.decode(response.responseText);
                }catch(e){
                	decoded=false;
                	alert(e.message);
                }
                if(!decoded || !data){
                	Ext.log({}, '>> 服务器发生错误，响应内容不是正确的JSON格式！');
                	Ext.log({indent:1}, 'Status Code: ' + response.status);
                	Ext.log({indent:1}, '响应内容：' + response.responseText);
                	ECF.msg.error('服务器执行错误', '服务器发生错误，响应内容不是正确的JSON格式！\r'
                			+ 'Status Code: ' + response.status + '\r'
                			+ '响应内容：\r' + response.responseText);
                	if(typeof o.failure === "function")
                		o.failure();
                	return;
                }
                if (data.success === true){
                	if(typeof o.success === "function")
                		o.success(data, options);
                	return;
                }
                Ext.log({}, '>> 服务器执行失败，请查看服务器日志了解详细错误信息！');
                Ext.log({indent:1}, 'JSON result: ' + response.responseText);
                ECF.msg.error('服务器执行错误', '服务器执行失败，请查看服务器日志了解详细错误信息！\rSuccess: false\r错误消息：' + data.message);
            },
            failure: function(response, options) {
            	if ((typeof o.failure) === "function") o.failure();
                if (options.failureType == 'connect')
                	ECF.msg.error('请求服务器错误', '无法连接服务器!\r' + 'URL地址: ' + con.getInitialConfig().url);
                else
                	ECF.msg.error('请求服务器错误', '请求服务器时发生未知错误!\r' 
                			+ 'Status Code: ' + response.status + '\r'
                			+ 'URL地址：' + con.getInitialConfig().url);
            }
        });
    };
    
    /**
     * 消息提示（提示、警告、错误信息）
     */
    ecFramework.prototype.msg = {
    	info : function(title, msg) {
    		Ext.Msg.show({
				title:title, msg:ECF.htmlEncode(msg)
				, maxHeight:500, buttons:Ext.Msg.OK, icon:Ext.MessageBox.INFO
    		});
        },
        warn : function(title, msg) {
    		Ext.Msg.show({
				title:title, msg:ECF.htmlEncode(msg)
				, maxHeight:500, buttons:Ext.Msg.OK, icon:Ext.MessageBox.WARNING
    		});
        },
        error : function(title, msg) {
    		Ext.Msg.show({
				title:title, msg:ECF.htmlEncode(msg)
				, maxHeight:500, buttons:Ext.Msg.OK, icon:Ext.MessageBox.ERROR
    		});
        }
    };
    
    /**
     * 处理HTTP和服务的异常消息，用于proxy的afterRequest事件。
     * 前提条件：为proxy的reader设置了successProperty: 'success', messageProperty: 'message'属性。
     */
    ecFramework.prototype.storeExceptionHandler = function(request, success){
    	if(!success) {
    		//发生了HTTP错误，该错误发生时会先执行store的load事件，再执行proxy的afterRequest事件；
    		//这类错误无法在store的onload中处理，因为onload中无法获取到相关错误信息（无法取到operation对象）；
    		if(!request.operation.error) return;
    		ECF.msg.error('系统错误', '错误消息:' 
    	     	+ '<br />HTTP Status Code: ' + request.operation.error.status 
    	     	+ '<br />' + ECF.htmlEncode(request.operation.error.statusText) 
    	     	+ '<br />URL: ' + request.url 
    	     	+ '<br />原因:<br />服务器不可用，或者服务发生了未处理的异常');
    		return;
    	}
    	//为proxy的reader设置了successProperty: 'success', messageProperty: 'message'属性，success=true时:
    	//    request.operation.success为服务器端返回的HttpJsonResult.success属性；
    	//    request.operation.error为服务器端返回的HttpJsonResult.message属性；
    	//request.operation.success为false表明服务器端执行发生了异常，通过HttpJsonResult返回给了客户端。
    	//这类错误也可以在store的load事件中处理，store.getProxy().getReader().rawData即为服务器端返回的
    	//    HttpJsonResult对象。
    	//另外可以考虑的方法是继承对应的store对象，重写processResponse方法。
    	if(request.operation.success) return;
		ECF.msg.error('服务错误', '错误消息:' 
			+ '<br />' + ECF.htmlEncode(request.operation.error)
			+ '<br />原因:<br />服务执行时发生了错误，通过JSON返回错误消息');
    };
    
    ECF = new ecFramework();	// Init Global Variable
    
    //***********************************************************
    //可能有用的函数
    //***********************************************************
    function enableComponent(selector, enable){
    	var components = Ext.ComponentQuery.query(selector);
    	if (components.length) {
    	    for (var i = 0, l = components.length; i < l; i++) {
    	    	if(enable) components[i].enable();
    	    	else components[i].disable();
    	    }
    	}
    }
    /**
     * 启用控件。
     * @param selector extjs的selector表达式
     * 控件ID：跟jquery相同，用 #id 表示
     * 控件xtype：同jquery用css class name查找，用 .xtype 表示
     * 属性条件，用中括号括起来
     */
    function enable(selector){
    	enableComponent(selector, true);
    }
    /**
     * 禁用控件（不可编辑状态）。
     * @param selector
     */
    function disable(selector){
    	enableComponent(selector, false);
    }
}
}());