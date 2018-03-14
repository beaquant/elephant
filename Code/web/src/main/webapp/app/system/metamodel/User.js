Ext.define('ECF.app.system.metamodel.User', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        /*
         * name: 属性名称。
         * label: 属性的标题，显示在Grid的标题栏中，Form的字段的label上。
         * datatype: 属性的数据类型，必须为ExtJS支持的数据类型：string, number, date等。
         * domaintype: 属性的领域类型，
         * 		id：属性的主键
         * 		enum：枚举类型，必须再通过ref属性指定枚举类型的名称，并且枚举必须注册到ECF.app.enums中
         * 		password：密码
         * 		email、phone、mobile：邮件地址、电话、手机
         * 		date、time、datetime：日期、时间类型
         * nullable: 是否允许为空。
         * maxlen: 字符串类型的属性最大长度。
         */
        {name:'userId', label:'用户ID', datatype:'number', domaintype:'id'},
        {name:'userName', label:'用户姓名', datatype:'string', domaintype:'string', maxlen:25, nullable:false},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', nullable:false, defaultValue:1},
        {name:'loginId', label:'登陆账号', datatype:'string', domaintype:'string', maxlen:40, nullable:false},
        {name:'password', label:'登陆密码', datatype:'string', domaintype:'password', maxlen:18, nullable:false},
        {name:'email', label:'邮箱', datatype:'string', domaintype:'email', maxlen:50},
        {name:'phone', label:'电话', datatype:'string', domaintype:'phone', maxlen:20},
        {name:'mobile', label:'手机', datatype:'string', domaintype:'mobile', maxlen:15},
        {name:'updateUser', label:'最后更新人', datatype:'string', domaintype:'string'},
        {name:'updateTime', label:'最后更新时间', datatype:'date', domaintype:'datetime'},
        {name:'createUser', label:'创建人', datatype:'string', domaintype:'string'},
        {name:'createTime', label:'创建时间', datatype:'date', domaintype:'datetime'}
    ],
    api:[
        {name:'get', url:'/api/sys/user/get'},
        {name:'update', url:'/api/sys/user/update'},
        {name:'create', url:'/api/sys/user/create'},
        {name:'find', url:'/api/sys/user/find'},
        {name:'login', url:'/api/sys/login'},
        {name:'logout', url:'/api/sys/logout'}
    ]
});