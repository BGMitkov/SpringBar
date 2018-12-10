package bar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable{
	
	private static final long serialVersionUID = 7309067095122952489L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String name;
	private String price;
	private String type;
	private String description;

	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String name, String price, String type, String description) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
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
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type
		        + ", description=" + description + "]";
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
