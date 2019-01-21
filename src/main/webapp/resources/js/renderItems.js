/**
 * Script for rendering items.
 */

//$(document).ready(getItems);

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
		var tableName = item.itemType.name;
		var table = document.getElementById(tableName);
		var tbody = table.getElementsByTagName('tbody')[0];
		var row = document.createElement('tr');
		console.log("Adding item to table " + tableName);
		var name = document.createElement('td');	
		var price = document.createElement('td');
		var description = document.createElement('td');
		var addButton = document.createElement('td');
		var button = document.createElement('button');
		
		name.innerHTML = item.name;
		price.innerHTML = item.price;
		description.innerHTML = item.description;
		button.innerHTML = 'Add';
		addButton.appendChild(button);
		
		row.appendChild(name);
		row.appendChild(description);
		row.appendChild(price);
		row.appendChild(addButton);
		
		tbody.appendChild(row);
	}
