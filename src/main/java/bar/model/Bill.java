package bar.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private long id;
	@OneToMany(mappedBy = "bill", cascade = CascadeType.PERSIST)
	private List<Order> orders;
	// TODO make a table model to allow configuration
	private String tableNumber;
	private int price;
	// TODO make a model for status so that it can be configurable
	private BillStatus status;

	public Bill(String tableNumber, BillStatus billStatus) {
		this.orders = new LinkedList<>();
		this.tableNumber = tableNumber;
		this.status = billStatus;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getPrice() {
		int sum = 0;
		for (Order order : orders) {
			sum += order.getTotalPrice();
		}
		return sum;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	private String ordersToString() {
		StringBuilder builder = new StringBuilder();
		for (Order order : orders) {
			builder.append(order.toString()).append(System.lineSeparator());
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return "Bill [orders=" + ordersToString() + ", tableNumber=" + tableNumber + ", price=" + price + ", status="
				+ status + "]";
	}
}
