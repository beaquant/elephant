Ext.define('ECF.app.system.metamodel.Menu', {
	extend: 'ECF.app.MetaBase',
	singleton: true,
    properties: [
        {name:'mid', label:'菜单ID', datatype:'number', domaintype:'id'},
        {name:'pid', label:'父级ID', datatype:'number', domaintype:'number', nullable:false},
        {name:'name', label:'菜单名称', datatype:'string', domaintype:'string', maxlen:40, nullable:false},
        {name:'status', label:'状态', datatype:'number', domaintype:'enum', ref:'Status', nullable:false, defaultValue:1},
        {name:'orderBy', label:'排序', datatype:'number', domaintype:'number', maxlen:5, defaultValue:0},
        {name:'mtype', label:'菜单类型', datatype:'string', domaintype:'enum', ref:'MenuItemType', nullable:false, defaultValue:'app'},
        {name:'mdata', label:'菜单地址', datatype:'string', domaintype:'string', maxlen:500, nullable:false},
        {name:'updateUser', label:'最后更新人', datatype:'string', domaintype:'string'},
        {name:'updateTime', label:'最后更新时间', datatype:'date', domaintype:'datetime'}
    ],
    api:[
        {name:'menutree', url:'/api/sys/menu/find/menutree'},
        {name:'create', url:'/api/sys/menu/create'},
        {name:'update', url:'/api/sys/menu/update'}
    ]
});