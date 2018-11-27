package bar.model;

import java.util.List;

public class Bill {
	private List<Order> orders;
	private String tableNumber;
	// TODO refactor to use currency and locale
	private float price;
	private BillStatus status;

	public Bill(List<Order> orders, String tableNumber, BillStatus billStatus) {
		this.orders = orders;
		this.tableNumber = tableNumber;
		this.status = billStatus;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	//TODO implement scenario of table swap
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public List<Order> getOrders() {
		return orders;
	}
}
