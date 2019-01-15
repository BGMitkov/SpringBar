package bar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bar.annotation.ValidationSequence;
import bar.constant.URI;
import bar.dao.ItemDAO;
import bar.dao.ItemTypeDAO;
import bar.dto.ItemDTO;
import bar.model.Item;
import bar.model.ItemType;

@Controller
public class ItemController {

	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;

	@RequestMapping(value = URI.ADD_ITEM_FORM, method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("addItem", "item", new ItemDTO());
	}

	@RequestMapping(value = URI.ADD_ITEM, method = RequestMethod.POST)
	public String addItem(@ModelAttribute("itemDTO") @Validated(ValidationSequence.class) ItemDTO itemDTO, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "addItem";
		}

		Item storedItem = itemDAO.save(convertToItem(itemDTO));
		model.addAttribute(storedItem);
		return "addedItem";
	}

	@RequestMapping(value = URI.ITEMS, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Item> getItems() {
		Iterable<Item> allItems = itemDAO.findAll();
		return allItems;
	}

	private Item convertToItem(ItemDTO itemDTO) {
		ItemType itemType = itemTypeDAO.findByName(itemDTO.getItemType());
		Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemType, itemDTO.getDescription());
		return item;
	}
}
