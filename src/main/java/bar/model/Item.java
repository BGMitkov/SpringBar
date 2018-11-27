package bar.model;

public class Item {
	private Long itemId;
	private String itemName;
	private String price;
	private String type;
	private String description;

	public Item(String itemName, String price, String type, String description) {
		super();
		// TODO increment the id
		this.itemName = itemName;
		this.price = price;
		this.type = type;
		this.description = description;
	}

	public Long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		// TODO make it fancier
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", type=" + type
		        + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

}
