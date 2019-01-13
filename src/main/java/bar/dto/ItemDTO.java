package bar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import bar.annotation.ExistsInDatabase;
import bar.annotation.Extended;
import bar.dao.ItemTypeDAO;

public class ItemDTO {
	@NotBlank
	private String name;
	@Positive
	private int price;
	@NotBlank
	@ExistsInDatabase(repository = ItemTypeDAO.class, groups = { Extended.class })
	private String itemType;
	@NotBlank
	private String description;

	public ItemDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
