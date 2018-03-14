Ext.define('ECF.app.system.metamodel.Action', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'actId', label:'操作ID', datatype:'number', domaintype:'id'},
        {name:'actKey', label:'操作代码', datatype:'string', domaintype:'string', maxlen:30, nullable:false},
        {name:'actName', label:'操作名称', datatype:'string', domaintype:'string', maxlen:30, nullable:false},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', nullable:false, defaultValue:1},
        {name:'fnId', label:'菜单ID', datatype:'number', domaintype:'number', defaultValue:0},
        {name:'fnName', label:'菜单名称', datatype:'string', domaintype:'string', maxlen:30},
        {name:'url', label:'URL地址', datatype:'string', domaintype:'string', nullable:false},
        {name:'remark', label:'备注', datatype:'string', domaintype:'string', maxlen:100}
    ],
    api:[
        {name:'find', url:'/api/sys/action/find'},
        {name:'create', url:'/api/sys/action/create'},
        {name:'update', url:'/api/sys/action/update'}
    ]
});