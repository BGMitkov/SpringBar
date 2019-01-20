/**
 * Script for rendering items.
 */

$(document).ready(getItems);

	function getItems() {
		$.ajax({
			url : '/SpringBar/rest/items',
			type : 'GET',
			dataType : "json",
			success : function(data) {
				renderItems(data);
			}

		});
	}

	function renderItems(items) {
		console.log(items);
		items.forEach(renderItem);
	}

	function renderItem(item) {
		//TODO complete function;
	}