package bar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/view/addItemForm", method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("addItem", "command", new ItemDTO());
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addItem(@ModelAttribute("Added Item") @Valid ItemDTO itemDTO, BindingResult bindingResult,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "addItem";
		}
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
