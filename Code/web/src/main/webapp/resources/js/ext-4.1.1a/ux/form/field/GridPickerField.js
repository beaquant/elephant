Ext.define("Ext.ux.form.field.GridPickerField", {
    extend: "Ext.form.field.Picker",
    requires: ["Ext.grid.Panel"],
    alias: 'widget.gridpickerfield',
    "initComponent": function () {
        var me = this;
        Ext.apply(me, {
            //fieldLabel:me.fieldLabel, labelWidth:me.labelWidth, width:me.width,
            //ctStore:me.ctStore, ctValueField:me.ctValueField, ctDisplayField:me.ctDisplayField,
            //store: me.store, pkValueField:me.pkValueField,
            triggerCls:'x-form-search-trigger', editable:false
        });
        me.callParent();
    },
    "createPicker": function () {
    	var me=this, gridConfig = {autoScroll:true, floating:true, focusOnToFront:true, shadow:false,
			header:false, stateful:false, viewConfig:{stripeRows: true},
			ownerCt:me.ownerCt, store:me.pickerStore,
			combo:me};
    	Ext.apply(gridConfig, me.gridConfig);
    	me.picker = Ext.create('Ext.grid.Panel', gridConfig);
    	me.picker.on({
    		itemclick:function (grid, record, item, index, e, eOpts ){
    			Ext.getCmp(me.ctValueFieldId).setValue(record.get(me.pkValueField));
    			Ext.getCmp(me.ctDisplayFieldId).setValue(record.get(me.pkDisplayField));
    			//me.picker.hide();
    	    }
    	});
    	me.picker.getStore().load();
    	return me.picker;
    },
    "alignPicker": function () {
        var me = this, picker, isAbove, aboveSfx = '-above';
        if (this.isExpanded) {
            picker = me.getPicker();
            if (me.matchFieldWidth) {
                picker.setWidth(me.bodyEl.getWidth());
            }
            if (picker.isFloating()) {
                picker.alignTo(me.inputEl, "", me.pickerOffset); // ""->tl   
                isAbove = picker.el.getY() < me.inputEl.getY();
                me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls
          + aboveSfx);
                picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls
          + aboveSfx);
            }
        }
    }
});