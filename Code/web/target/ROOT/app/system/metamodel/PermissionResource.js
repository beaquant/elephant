Ext.define('ECF.app.system.metamodel.PermissionResource', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'rid', label:'资源ID', datatype:'string', domaintype:'id'},
        {name:'name', label:'名称', datatype:'string', domaintype:'string', maxlen:30},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', defaultValue:1},
        {name:'rtype', label:'资源类型', datatype:'number', domaintype:'enum', ref:'PermissionResourceType', defaultValue:1},
        {name:'checked', label:'', datatype:'bool', domaintype:'boolean'}
    ],
    api:[
        {name:'find-resource-tree', url:'/api/sys/permission/find-resource-tree'},
        {name:'assign', url:'/api/sys/permission/assign'}
    ]
});