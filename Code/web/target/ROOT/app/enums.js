Ext.ns('ECF.app');

/**
 * 公共的枚举管理类。系统所有的枚举都注册到这里。
 */
ECF.app.enums = {
	maps:{
		Success:[
			{value:1, text:'成功', icon:'/resources/images/status-enabled.png'},
			{value:0, text:'失败', icon:'/resources/images/status-deleted.png'}
		],
		Status:[
			{value:1, text:'有效', icon:'/resources/images/status-enabled.png'},
			{value:0, text:'禁用', icon:'/resources/images/status-disabled.png'},
			{value:-1, text:"删除", icon:'/resources/images/status-deleted.png'}
		],
		MenuItemType:[
		    {value:'mdl', text:'模块'},
		    {value:'app', text:'ExtJS菜单'},
		    {value:'url', text:'普通菜单'}
		],
		PermissionOwnerType:[
		    {value:1, text:'用户'},
		    {value:2, text:'角色'}
		],
		PermissionResourceType:[
	        {value:0, text:'模块'},
 		    {value:1, text:'操作'},
 		    {value:2, text:'菜单'}
 		],
 		AccessLogType:[
 		    {value:1, text:'操作'},
 		    {value:2, text:'登陆'},
 		    {value:3, text:'退出'}
 		],
        
        ChannelId:[
            {value:'TB',text:'淘宝'},
            {value:'SC',text:'商城'},
            {value:'DK',text:'大客户'}
        ],
        ShopId:[
            {value:100, text:'海尔商城'},
            {value:200, text:'海尔官方淘宝旗舰店'},
            {value:201, text:'海尔热水器专卖店'},
            {value:202, text:'淘宝网统帅日日顺乐家专卖店'},
            {value:203, text:'海尔生活电器专卖店'},
            {value:300, text:'龙卡商城'},
            {value:301, text:'善融商城'},
            {value:303, text:'1号店'}
        ],
        
		SaleOrderStatus:[
 			{value:10, text:'待定', cls:'s-ord-wait-policy'},
 			{value:70, text:'自动确认中', cls:'s-ord-auto'},
 			{value:80, text:'待人工确认', cls:'s-ord-manual'},
 			{value:100, text:'已确认', cls:'s-ord-confirm'},
 			{value:200, text:'取消', cls:'s-ord-cancel'},
 			{value:210, text:'完成关闭', cls:'s-ord-close'}
 		],
 		SaleOrderConfirmStatus:[
            {value:0, text:'未确认'},
            {value:1, text:'自动确认'},
            {value:2, text:'人工确认'}
        ],
 		SaleOrderType:[
   		    {value:0,text:'普通订单'},
   		    {value:4,text:'订金尾款', cls:'t-ord-front-final'},
   		    {value:1,text:'订金订单', cls:'t-ord-front'},
   		    {value:2,text:'尾款订单', cls:'t-ord-final'},
   		    {value:3,text:'团购订单', cls:'t-ord-group'}
   		],
        SaleOrderLineStatus:[
            {value:0,text:'初始状态'},
            {value:10,text:'已确认'},
            {value:20,text:'待同步HP'},
            {value:21,text:'已同步HP'},
            {value:30,text:'待同步LES'},
            {value:31,text:'已同步LES'},
            {value:32,text:'LES已出库'},
            {value:40,text:'网点签收'},
            {value:41,text:'网点拒绝'},
            {value:42,text:'网点已发货'},
            {value:50,text:'用户签收'},
            {value:51,text:'用户拒收'},
            {value:100,text:'完成关闭'},
            {value:101,text:'取消关闭'}
        ],
        SaleOrderStockStatus:[
            {value:0, text:'未分配', cls:'s-stock-init'},
            {value:10, text:'待分配', cls:'s-stock-wait'},
            {value:20, text:'已分配', cls:'s-stock-allocated'}
        ],
        SaleOrderPaymentTypeId:[
 	        {value:1,text:'支付宝'},
 	        {value:2,text:'建行在线支付'},
 	        {value:3,text:'招行在线支付'},
 	       	{value:4,text:'银联在线支付'},
 	      	{value:5,text:'电子钱包在线支付'},
 	     	{value:6,text:'货到付款', cls:'t-pay-cod'}
 	    ],
 		SaleOrderPaymentStatus:[
		   	{value:0, text:'未付款', cls:'s-pay-unpaid'},
			{value:10, text:'已付订金', cls:'s-pay-front'},
		   	{value:20, text:'已付款', cls:'s-pay-paid'}
		],
		SaleOrderInvoiceType:[
   	        {value:1,text:'增票', cls:'t-invo-vat'},
   	        {value:2,text:'普票'}
   	    ],
 	    SaleOrderInvoiceStatus:[
            {value:0,text:'待开票'},
            {value:1,text:'开票中'},
            {value:4,text:'已开票'},
            {value:5,text:'取消开票'},
            {value:6,text:'作废发票'}
        ],
        InvoiceInfoAuditStatus:[
            {value:-1, text:'审核拒绝', cls:'s-invo-rejected'},
            {value:0, text:'待审核', cls:'s-invo-to-audit'},
            {value:1, text:'审核通过', cls:'s-invo-approved'}
        ],
 	    SaleOrderDeliveryMode:[
 		 	{value:0,text:'普通模式'},
 		    {value:1,text:'其他模式'}
 	 	],
        ItemProperty:[
            {value:'A',text:'良品'},
            {value:'X',text:'不良品'},
            {value:'U',text:'待检'}
        ],
        CODAudit:[
            {value:2,text:'确认有效'},
            {value:3,text:'确认无效'}
        ]
	},
	getEnum: function(name){
		if(!this.maps[name]) {
			Ext.log('枚举'+name+'未在ECF.app.enums中定义');
			return null;
		}
		return this.maps[name];
	},
	getRender: function(name){
		var enumItems = this.getEnum(name);
		return function(value){
    		for(var i=0; enumItems!=null && i<enumItems.length; i++){
    			if(enumItems[i].value==value){
    				if(enumItems[i].icon) return '<img src="'+enumItems[i].icon+'" title="'+enumItems[i].text+'" />';
    				if(enumItems[i].cls) return '<span class="'+enumItems[i].cls+'">'+enumItems[i].text+'</span>';
    				return enumItems[i].text==null?value:enumItems[i].text;
    			}
    		}
    		return value;
    	};
	},
	getStore: function(name, emptyItem, includes, excludes){
		var cfg = {
			fields: ['value', 'text'], data: []
		};
		if(emptyItem)
			cfg.data.push({value:emptyItem.value, text:emptyItem.text});
		var enumItems = this.getEnum(name);
		for(var i=0; enumItems && i<enumItems.length; i++){
			if(includes && !this.contains(includes, enumItems[i].value)) continue;
			if(excludes && this.contains(excludes, enumItems[i].value)) continue;
			cfg.data.push({value:enumItems[i].value, text:enumItems[i].text});
		}
		return Ext.create('Ext.data.Store', cfg);
	},
	contains: function(list, item){
		if(!list || !item) return false;
		for(var i=0; i<list.length; i++){
			if(list[i]==item) return true;
		}
		return false;
	}
}