Ext.define('ECF.app.system.view.UserForm', {
	requires:['Ext.form.FormPanel','ECF.app.system.metamodel.User'],
    extend:'Ext.window.Window',
    
    //弹出窗口的基本属性设置
    constrain:true, 
    title:'用户资料', closeAction:'destroy', 
    width:500, minWidth:500, y:40, x:200, resizable:true, modal:false, 
    layout: 'fit', 
    
    initComponent: function() {
    	var me = this;
    	var meta = ECF.app.system.metamodel.User
    		//FormBuilder: 创建表单Form的辅助工具类
    		, fb = meta.getFormBuilder();
    	
    	this.items = [{
    		xtype:'form', id:'sys-user-form', border:false, bodyPadding:10, 
    		//Form中放置的字段的默认设置
    		fieldDefaults:{enforceMaxLength:true, labelAlign:'right', msgTarget:'qtip'},
            
    		//各个字段
            items: [
                //hboxFieldPanel构造一个field panel，可以收缩。field panel中的各个控件水平依次排列（hbox布局方式）
                fb.hboxFieldPanel({id:'panel-user-info',title:'用户资料', fieldsDefault:{labelWidth:70}}, 
                [
                    //这个field panel中需要放置的字段：
                    //1. 一个数组代表一行，数组中的各个对象代表在这一行中需要放置的各个字段；
                    //2. FormBuilder将根据元模型ECF.app.system.metamodel.User上的设置进行显示，例如标题、
                    //	 处理枚举类型、字段长度限制、日期类型格式化等
                    //3. 如果因为FormBuilder封装的不够，需要使用ExtJS原本方式来定义字段，请参考ECF.app.system.view.ActionForm
                    //部分参数说明：
                    //name: 必须是元模型ECF.app.system.User中定义的属性名称
                    //hidden: 隐藏字段，不显示在Form表单上
                    //width: 字段长度（注意，这个长度是字段的总长度，包括label长度以及显示字段值的控件长度
                	[{name:'userId', hidden:true}, {name:'loginId', width:250}, {name:'password', width:180}],
                    [{name:'userName', width:250, emptyText:'使用真实姓名操作日志更清晰'}, {name:'status', width:180}],
                    [{name:'email', width:430}],
                    [{name:'phone', width:250}, {name:'mobile', width:180}]
                ]), 
                fb.hboxFieldPanel({id:'panel-log-info', title:'日志信息', fieldsDefault:{labelWidth:90}}, [
                    //readonly: 该字段不可以编辑
                	[{name:'createUser', readonly:true, width:200}, {name:'createTime', readonly:true, width:220}],
                    [{name:'updateUser', readonly:true, width:200}, {name:'updateTime', readonly:true, width:220}]
                ])
            ],
            
            //在Form中显示的按钮
            buttons: [
	            fb.cmd('ok', me), 
	            fb.cmd('close', me)
            ], 
            //按钮居中显示
            buttonAlign:'center'
    	}];
        
    	//callParent必须为最后执行的语句。
        this.callParent();
    }
});