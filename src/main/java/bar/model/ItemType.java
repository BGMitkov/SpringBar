package bar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_type")
public class ItemType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	public ItemType() {
	}

	public ItemType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
