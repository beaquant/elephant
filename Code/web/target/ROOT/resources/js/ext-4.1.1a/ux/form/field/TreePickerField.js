Ext.define("Ext.ux.form.field.TreePickerField", {
    extend: "Ext.form.field.Picker",
    requires: ["Ext.tree.Panel"],
    alias: 'widget.treepickerfield',
    "initComponent": function () {
        var me = this;
        Ext.apply(me, { triggerCls:'x-form-search-trigger', editable:false });
        me.callParent();
    },
    "createPicker": function () {
    	var me=this, pickerConfig = {autoScroll:true, floating:true, focusOnToFront:true, shadow:false,
			header:false, stateful:false, viewConfig:{stripeRows: true},
			collapsible:false, useArrows:false, rootVisible:false, singleExpand:false,
			ownerCt:me.ownerCt, store:me.pickerStore,
			combo:me};
    	Ext.apply(pickerConfig, me.pickerConfig);
    	me.picker = Ext.create('Ext.tree.Panel', pickerConfig);
    	me.picker.on({
    		itemclick:function (grid, record, item, index, e, eOpts ){
    			if(typeof me.pickerConfig.selectHandler=='function'){
    				if(!me.pickerConfig.selectHandler(record)) return;
    			}
    			Ext.getCmp(me.ctValueFieldId).setValue(record.get(me.pkValueField));
    			Ext.getCmp(me.ctDisplayFieldId).setValue(record.get(me.pkDisplayField));
    			me.picker.hide();
    	    },
    	    destroy:function(){
    	    	Ext.log('Ext.ux.form.field.TreePickerField\'s picker destroyed');
    	    }
    	});
    	return me.picker;
    }/*,
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
    }*/
});