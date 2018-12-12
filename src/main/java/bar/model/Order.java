package bar.model;

import java.util.Date;
import java.util.List;

public class Order {
	private Long orderId;
	private List<Item> orderedItems;
	private String tableNumber;
	private String executor;
	private Status status;
	private long orderDate;
	private long acceptanceDate;
	private float totalPrice;

	public Order() {
	}

	public Order(List<Item> orderedItems, String tableNumber) {
		this.orderedItems = orderedItems;
		this.tableNumber = tableNumber;
		this.status = Status.WAITING;
		this.orderDate = new Date().getTime();
		this.totalPrice = 0.0f;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setOrderedItems(List<Item> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getOrderDate() {
		return new Date(orderDate);
	}

	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

	public Date getAcceptanceDate() {
		return new Date(acceptanceDate);
	}

	public void setAcceptanceDate(long acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public List<Item> getOrderedItems() {
		return orderedItems;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void calculateTotalPrice() {
		float sumPrice = 0.0f;
		for (Item item : orderedItems) {
			sumPrice += Float.parseFloat(item.getPrice());
		}
		totalPrice = sumPrice;
	}

	@Override
	public String toString() {
		// TODO make fancier
		return "Order [orderId=" + orderId + ", orderedItems=" + orderedItems + ", tableNumber=" + tableNumber
				+ ", status=" + status + ", orderDate=" + new Date(orderDate).toString() + ", acceptanceDate="
				+ new Date(acceptanceDate).toString() + ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
