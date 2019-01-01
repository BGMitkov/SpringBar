package bar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bar.dao.ItemDAO;
import bar.dao.ItemTypeDAO;
import bar.dto.ItemDTO;
import bar.exception.SpringException;
import bar.model.Item;
import bar.model.ItemType;

@Controller
public class ItemController {

	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	@Autowired
	private UserContext userContext;

	// TODO add user context filter
	@RequestMapping(value = "/addItemForm", method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("addItem", "command", new Item());
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addItem(@ModelAttribute("Added Item") ItemDTO itemDTO, ModelMap model) {
		itemDAO.save(getItem(itemDTO));
		Item storedItem = itemDAO.findByName(itemDTO.getName());
		model.addAttribute(storedItem);
		return "addedItem";
	}

	private Item getItem(ItemDTO itemDTO) {
		ItemType itemType = itemTypeDAO.findByName(itemDTO.getType());
		Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemType, itemDTO.getDescription());
		return item;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItems(ModelMap modelMap) {
//		if (!userContext.isUserInRole(Role.MANAGER, Role.SERVER)) {
//			return "unauthorized";
//		}

//		modelMap.addAllAttributes(itemDAO.getItems());

		return "items";
	}
}
