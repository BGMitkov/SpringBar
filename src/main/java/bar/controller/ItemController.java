package bar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import bar.repository.ItemTypeRepository;

@Controller
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemTypeRepository itemTypeRepository;
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping(value = URI.ADD_ITEM_FORM, method = RequestMethod.GET)
	public ModelAndView item() {
		return new ModelAndView("addItem", "item", new ItemDTO());
	}

	@RequestMapping(value = URI.ADD_ITEM, method = RequestMethod.POST)
	public String addItem(@ModelAttribute("item") @Validated(ValidationSequence.class) ItemDTO itemDTO,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "addItem";
		}

		jmsTemplate.convertAndSend("itemRepository", convertToItem(itemDTO));
		model.addAttribute("item", itemDTO);
		return "addedItem";
	}

	@RequestMapping(value = URI.ITEMS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Iterable<Item> getItems() {
		return itemRepository.findAll();
	}

	@RequestMapping(value = URI.ITEM_TYPES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Iterable<ItemType> itemTypes() {
		return itemTypeRepository.findAll();
	}

	private Item convertToItem(ItemDTO itemDTO) {
		ItemType itemType = itemTypeRepository.findByName(itemDTO.getItemType());
		Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemType, itemDTO.getDescription());
		return item;
	}
}
