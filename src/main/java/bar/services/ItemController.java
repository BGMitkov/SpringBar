package bar.services;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bar.dao.ItemDAO;
import bar.exceptions.SpringException;
import bar.model.Item;
import bar.model.Role;

@Controller
public class ItemController {

	private ItemDAO itemDAO;
	private UserContext userContext;

	// TODO add user context filter
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("item", "command", new Item());
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String addItem(@ModelAttribute("Added Item") Item item, ModelMap model) {
		if (!userContext.isUserInRole(Role.MANAGER)) {
			throw new SpringException("Unauthorized request!");
		}

		itemDAO.addItem(item);
		model.addAttribute("id", item.getId());
		model.addAttribute("name", item.getName());
		model.addAttribute("description", item.getDescription());
		model.addAttribute("price", item.getPrice());
		model.addAttribute("type", item.getType());

		return "addedItem";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItems(ModelMap modelMap) {
		if (!userContext.isUserInRole(Role.MANAGER, Role.SERVER)) {
			return "unauthorized";
		}

		/*
		 * Collection<Item> items = itemDAO.getItems();
		 * modelMap.addAllAttributes(items);
		 */

		return "items";
	}
}
