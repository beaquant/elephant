Ext.define('Ext.ux.form.field.DateTimeField', {
	extend : 'Ext.form.FieldContainer',
	mixins : {
		field : 'Ext.form.field.Field'
	},
	alias : 'widget.datetimefield',
	layout : 'hbox',
	combineErrors : true,
	//msgTarget : 'side',
	dateTimeFormat : 'Y-m-d H:i:s',
	dateCfg : {},
	timeCfg : {},

	initComponent : function() {
		var me = this;
		me.buildField(me);
		me.callParent();
		this.dateField = this.down('datefield')
		this.timeField = this.down('timefield')
		//me.initField();
	},

	// @private
	buildField : function(me) {
		me.items = [ 
		    Ext.apply({xtype:'datefield', format:'Y-m-d', allowBlank:false, editable:true, width:95}, me.dateCfg), 
		    Ext.apply({xtype:'timefield', format:'H:i',	allowBlank:false, submitFormat:'H:i:s', width:60}, me.timeCfg)
		]
	},

	getValue : function() {
		var value, 
			date = this.dateField.getSubmitValue(), 
			time = this.timeField.getSubmitValue();
		if(!date) date = Ext.Date.format(Ext.Date.parse('1900-01-01', 'Y-m-d'), this.dateField.submitFormat || this.dateField.format);
		if (time) {
			var format = this.getFormat();
			value = Ext.Date.parse(date + ' ' + time, format)
		} else {
			value = this.dateField.getValue()
		}
		return value
	},

	getSubmitValue : function() {
		var value = this.getValue()
		var format = this.getFormat()
		return value ? Ext.Date.format(value, format) : '';
	},

	setValue : function(value) {
		this.dateField.setValue(value)
		this.timeField.setValue(value)
	},

	getSubmitData : function() {
		var value = this.getValue()
		var format = this.getFormat()
		return value ? Ext.Date.format(value, format) : '';
	},

	getFormat : function() {
		return (this.dateField.submitFormat || this.dateField.format)
				+ ' '
				+ (this.timeField.submitFormat || this.timeField.format)
	}
});