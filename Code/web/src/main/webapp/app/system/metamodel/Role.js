Ext.define('ECF.app.system.metamodel.Role', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'roleId', label:'角色ID', datatype:'number', domaintype:'id'},
        {name:'roleName', label:'角色名称', datatype:'string', domaintype:'string', maxlen:30, nullable:false},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', nullable:false, defaultValue:1},
        {name:'remark', label:'备注', datatype:'string', domaintype:'string', maxlen:100},
        {name:'updateUser', label:'最后更新人', datatype:'string', domaintype:'string'},
        {name:'updateTime', label:'最后更新时间', datatype:'date', domaintype:'datetime'},
        {name:'createUser', label:'创建人', datatype:'string', domaintype:'string'},
        {name:'createTime', label:'创建时间', datatype:'date', domaintype:'datetime'}
    ],
    api:[
        {name:'get', url:'/api/sys/role/get'},
        {name:'update', url:'/api/sys/role/update'},
        {name:'create', url:'/api/sys/role/create'},
        {name:'find', url:'/api/sys/role/find'},
        {name:'user-role-assigned',url:'/api/sys/role/user/assigned'},
        {name:'user-role-unassigned',url:'/api/sys/role/user/unassigned'},
        {name:'user-role-assign',url:'/api/sys/role/user/assign'},
        {name:'user-role-unassign',url:'/api/sys/role/user/unassign'}
    ]
});