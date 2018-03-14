Ext.define('ECF.app.system.metamodel.AccessLog', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'logId', label:'日志ID', datatype:'number', domaintype:'id'},
        {name:'success', label:'结果', datatype:'number', domaintype:'enum', ref:'Success'},
        {name:'logType', label:'类型', datatype:'number', domaintype:'enum', ref:'AccessLogType'},
        {name:'logTime', label:'时间', datatype:'date', domaintype:'datetime'},
        {name:'userId', label:'用户ID', datatype:'number'},
        {name:'userName', label:'用户名', datatype:'string', maxlen:40},
        {name:'clientIp', label:'客户端IP', datatype:'string', maxlen:15},
        {name:'serverIp', label:'服务端IP', datatype:'string'},
        {name:'visitUrl', label:'访问地址', datatype:'string', maxlen:300},
        {name:'refererUrl', label:'来源地址', datatype:'string'},
        {name:'paramValue', label:'参数', datatype:'string'},
        {name:'sessionId', label:'会话ID', datatype:'string', maxlen:40},
        {name:'clientToken', label:'客户端标识', datatype:'string'},
        {name:'cookieValue', label:'Cookie值', datatype:'string'},
        {name:'agent', label:'浏览器AGENT', datatype:'string'},
        {name:'errorMsg', label:'错误信息', datatype:'string'}
    ],
    api:[
        {name:'find', url:'/api/sys/accesslog/find'}
    ]
});