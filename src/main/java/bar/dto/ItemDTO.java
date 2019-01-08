package bar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemDTO {
	@NotBlank
	private String name;
	@NotNull
	private int price;
	@NotNull
	private String type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
