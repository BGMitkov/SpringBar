package bar.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToMany
	@JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
	private List<Item> items;
	private String table;
	@ManyToOne
	@JoinColumn(name = "executor_id")
	private User executor;
	private OrderStatus status;
	private long orderDate;
	private long acceptanceDate;
	private int totalPrice;
	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public Order() {
	}

	public Order(List<Item> items, String table) {
		this.items = new LinkedList<>(items);
		this.table = table;
		this.status = OrderStatus.WAITING;
		this.orderDate = new Date().getTime();
		this.totalPrice = 0;
	}

	public void setId(Long orderId) {
		this.id = orderId;
	}

	public void addItems(List<Item> items) {
		this.items.addAll(items);
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public User getExecutor() {
		return executor;
	}

	public void setExecutor(User executor) {
		this.executor = executor;
	}

	public String getTable() {
		return table;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
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
		return items;
	}

	public int getTotalPrice() {
		setTotalPrice(calculateTotalPrice());
		return totalPrice;
	}

	private int calculateTotalPrice() {
		int sumPrice = 0;
		for (Item item : items) {
			sumPrice += item.getPrice();
		}

		return sumPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderedItems=" + itemsToString() + ", tableNumber=" + table + ", status="
				+ status + ", orderDate=" + new Date(orderDate).toString() + ", acceptanceDate="
				+ new Date(acceptanceDate).toString() + ", totalPrice=" + getTotalPrice() + "]";
	}

	private String itemsToString() {
		StringBuilder builder = new StringBuilder();
		for (Item item : items) {
			builder.append(item.toString()).append(System.lineSeparator());
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
