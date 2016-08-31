function MultipleSelect(id, parentNode, items, onChangeCallback,readonly) {
	this.id = id;
	this.parentNode = parentNode;
	this.items = items;
	this.onChangeCallback = onChangeCallback;
	this.readonly = readonly
}

MultipleSelect.prototype.clearList = function() {
	var id = this.id;

	// Clear item
	var item = $('#'+this.id);
	var obj = item.chosen();						// Initialize
	obj.off('change');

	$('#'+id).remove();
	$('#'+id+'_chosen').remove();
};

MultipleSelect.prototype.createList = function(selectAll) {
	var id = this.id;
	var items = this.items;
	var parentNode = this.parentNode;

	// Clear list
	this.clearList();

	// Clear parent node
	$(parentNode).empty();

	// Prepare start point
	var selectPlaceHolder = "请选择";
	if( this.readonly ) {
		selectPlaceHolder ='请选择';
	}
	
	$('<select data-placeholder="'+selectPlaceHolder+'"  id="'+id+'" multiple class="form-control" data-rel="chosen">'+
	'</select>').appendTo(parentNode);

	var parent = $('#' + id);

	// Fill list item
	for(var i in items) {
		var item = items[i];
		// item.value = item.id;
		if( this.readonly ) {
			if ( item.selected ) {
				$('<option '+(selectAll?'selected':item.selected)+' value="'+item.value+'">'+item.name+'</option>').appendTo(parent);
			}
		} else {
			$('<option '+(selectAll?'selected':item.selected)+' value="'+item.value+'">'+item.name+'</option>').appendTo(parent);
		}
	}
   
	// Startup
	this.startUp();
};

MultipleSelect.prototype.startUp = function() {
	var item = $('#'+this.id);

	var obj = item.chosen();						// Initialize

	if( this.onChangeCallback )
		obj.change(this.onChangeCallback);			// Setup onchange call back function
	
	if( this.readonly ) {
		this.parentNode.find("a.search-choice-close").unbind("click");
	}

	// Change width
	$('.chosen-container-multi').css('width','100%');
};

/**
 * Get user selections
 */
MultipleSelect.prototype.getValues = function() {
	var id = this.id;

	var options = $('#' + id +"> option");

	var result = [];

	for(var i=0,len=options.length; i<len; i++) {
		var option = options[i];
//			console.log(option, option.innerHTML, option.selected);

		if(option.selected) {
			result.push(option.value);
		}
	}

	return result.join(",");
};

/**
 * Get user selections, name
 */
MultipleSelect.prototype.getSelections = function() {
	var id = this.id;

	var options = $('#' + id +"> option");

	var result = [];

	for(var i=0,len=options.length; i<len; i++) {
		var option = options[i];
//			console.log(option, option.innerHTML, option.selected);

		if(option.selected) {
			result.push(option.innerHTML);
		}
	}

	return result.join(",");
};

/**
 * Reset the value
 */
MultipleSelect.prototype.reset = function() {
	var item = $('#'+this.id);

	item.val('').trigger('liszt:updated');
};

function MultiSelectHelper ( parentDomId , id ) {
	this.multiSelectDom = $("#"+ (parentDomId || "aliasGroupId"));
	this.selectId = id || "multi";
	this.data = [];
	this.initValues ="";
}
MultiSelectHelper.prototype = {
		pushData : function ( name, value , selected) {
			if( selected ) {
				this.data.push( { name : name , value: value, selected: "selected" });
			} else {
				this.data.push( { name : name , value: value });
			}
		},
		emptyData : function () {
			this.data = [];
		},
		setData : function ( data ) {
			this.data = data;
		},
		show : function () {
			this.moduleSelect = new MultipleSelect(this.selectId, // Id for the new widgets
					this.multiSelectDom, 									// parent container
					this.data,													// Data
					null,													// On change call back		
					false);													// readonly
			this.moduleSelect.createList();
		},
		getValues : function () {
			if( this.moduleSelect ) {
				return  this.moduleSelect.getValues();
			}
			return null;
		}
}