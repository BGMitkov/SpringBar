// Script for filling waiting orders table with data from server
$(document).ready(fillTableWithWaitingOrders());

function fillTableWithWaitingOrders() {
	$.ajax({
		url : 'orders/waiting',
		type : "GET",
		dataType : "json",
		success : function(data) {
			if (typeof data != 'undefined') {
				renderTable(data);
			}
		}
	})
}

function renderTable(data) {
// $("#waiting_orders_table")
	console.log(data);
//	var orders = 
}