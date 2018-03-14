Ext.define('ECF.app.system.metamodel.PermissionOwner', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'id', label:'权限拥有者ID', datatype:'number', domaintype:'id'},
        {name:'name', label:'名称', datatype:'string', domaintype:'string', maxlen:30},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', defaultValue:1},
        {name:'type', label:'权限拥有者', datatype:'number', domaintype:'enum', ref:'PermissionOwnerType', defaultValue:2}
    ],
    api:[
        {name:'find', url:'/api/sys/permission/find-owner'}
    ]
});