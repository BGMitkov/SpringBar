/**
 * Script for rendering the tables for each item type.
 */

$(document).ready(getItemTypes);

	function getItemTypes() {
		$.ajax({
			url: '/SpringBar/rest/itemTypes',
			type: 'GET',
			dataType: "json",
			success : function(itemTypes) {
				renderTables(itemTypes);
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
		table.setAttribute('class', 'table')
		table.setAttribute('id', tableId);
		tables.appendChild(table);
	}
