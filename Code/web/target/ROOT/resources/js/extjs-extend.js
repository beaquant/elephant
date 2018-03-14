/**
 * 对Ext JS的扩展。
 * @author 戎麦
 */

///**
// * 将Ext JS消息框的文本改为中文，这个方式在Ext JS 3中可用，Ext JS 4加载Ext的过程中，已经使用
// * 默认配置对Ext.Msg实例化，这里再设置就没有作用了。目前直接修改了Ext JS dev的源码。
// */
//if(Ext.Msg){
//	Ext.Msg.buttonText = {
//		ok     : "确定",
//		cancel : "取消",
//		yes    : "是",
//		no     : "否"
//	};
//	Ext.Msg.msgButtons.yes.text = '是';
//	Ext.Msg.msgButtons.no.text = '否';
//	Ext.Msg.msgButtons.ok.text = '确定';
//	Ext.Msg.msgButtons.cancel.text = '取消';
//}

/**
 * extjs的DisplayField不支持日期时间类型的格式化显示，自定义一个
 */
Ext.define('Ext.form.field.DateTimeDisplay', {
    extend:'Ext.form.field.Base',
    alias: 'widget.datetimedisplayfield',
    requires: ['Ext.util.Format', 'Ext.XTemplate'],
    alternateClassName: ['Ext.form.DateTimeDisplayField', 'Ext.form.DateTimeDisplay'],
    
    fieldSubTpl: [
        '<div id="{id}" class="{fieldCls}"></div>',
        {
            compiled: true,
            disableFormats: true
        }
    ],
    fieldCls: Ext.baseCSSPrefix + 'form-display-field',
    htmlEncode: false,
    validateOnChange: false,
    initEvents: Ext.emptyFn,
    submitValue: false,
    
    constructor: function(config){
    	this.format = config.format || '';
    	this.callParent([config]);
    },
    isValid: function() {
        return true;
    },
    validate: function() {
        return true;
    },
    getRawValue: function() {
        return this.rawValue;
    },
    setRawValue: function(value) {
        var me = this;
        value = Ext.value(value, '');
        if(!value) value = '';
        else if(this.format && this.format.length>0) value = Ext.util.Format.dateRenderer(this.format)(value);
        me.rawValue = value;
        if (me.rendered) {
            me.inputEl.dom.innerHTML = value;
        }
        return value;
    },
    getContentTarget: function() {
        return this.inputEl;
    }
});