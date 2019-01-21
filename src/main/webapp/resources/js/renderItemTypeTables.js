/**
 * Script for rendering the tables for each item type.
 * Requires the renderItems.js script to be imported in the html.
 */

$(document).ready(getItemTypes);

	function getItemTypes() {
		$.ajax({
			url: '/SpringBar/rest/itemTypes',
			type: 'GET',
			dataType: "json",
			success : function(itemTypes) {
				renderTables(itemTypes);
				getItems();
			}
		})
	}
	
	function renderTables(itemTypes) {
		itemTypes.forEach(renderTable);
	}
	
	function renderTable(itemType) {
		var tables = document.getElementById("tables");
		var tableId = itemType.name.replace(/ /g, '_');
		var table = document.createElement('table');
		var tbody = document.createElement('tbody');
		table.setAttribute('class', 'table')
		table.setAttribute('id', tableId);
		table.appendChild(tbody);
		tables.appendChild(table);
	}
