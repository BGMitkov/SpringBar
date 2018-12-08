package bar.model;

import java.util.Date;
import java.util.List;

public class Order {
	private Long orderId;
	private List<Item> orderedItems;
	private String tableNumber;
	private User executor;
	private Status status;
	private Date orderDate;
	private Date acceptanceDate;
	private float totalPrice;

	public Order() {
	}

	public Order(List<Item> orderedItems, String tableNumber) {
		this.orderedItems = orderedItems;
		this.tableNumber = tableNumber;
		this.status = Status.WAITING;
		this.orderDate = new Date();
		this.totalPrice = 0.0f;
	}

	public Long getOrderId() {
		return orderId;
	}

	public User getExecutor() {
		return executor;
	}

	public void setExecutor(User executor) {
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
		return new Date(orderDate.getTime());
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
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
		        + ", status=" + status + ", orderDate=" + orderDate + ", acceptanceDate=" + acceptanceDate
		        + ", totalPrice=" + totalPrice + "]";
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
