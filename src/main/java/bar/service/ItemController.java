package bar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
import bar.dto.ItemDTO;
import bar.model.Item;
import bar.model.ItemType;
import bar.repository.ItemRepository;
import bar.repository.ItemTypeDAO;

@Controller
public class ItemController {

	@Autowired
	private ItemRepository itemDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping(value = URI.ADD_ITEM_FORM, method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("addItem", "item", new ItemDTO());
	}

	@RequestMapping(value = URI.ADD_ITEM, method = RequestMethod.POST)
	public String addItem(@ModelAttribute("itemDTO") @Validated(ValidationSequence.class) ItemDTO itemDTO,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "addItem";
		}

		jmsTemplate.convertAndSend("itemRepository", convertToItem(itemDTO));
//		Item storedItem = itemDAO.save(convertToItem(itemDTO));
		model.addAttribute("item", itemDTO);
		return "addedItem";
	}

	@RequestMapping(value = URI.ITEMS, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Item> getItems() {
		Iterable<Item> allItems = itemDAO.findAll();
		return allItems;
	}

//	@RequestMapping(value = "/itemTypes", method = RequestMethod.GET, produces = "apl")
//	public Iterable<ItemType> itemTypes() {
//		Iterable<ItemType> itemTypes = itemTypeDAO.findAll();
//		return itemTypes;
//	}
	
	private Item convertToItem(ItemDTO itemDTO) {
		ItemType itemType = itemTypeDAO.findByName(itemDTO.getItemType());
		Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemType, itemDTO.getDescription());
		return item;
	}
}
