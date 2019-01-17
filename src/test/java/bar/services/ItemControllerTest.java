package bar.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.LinkedList;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import bar.SpringBarApplication;
import bar.constant.URI;
import bar.dto.ItemDTO;
import bar.interceptor.UserServiceInterceptor;
import bar.model.Item;
import bar.model.ItemType;
import bar.repository.ItemRepository;
import bar.repository.ItemTypeDAO;
import bar.service.SecurityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
@Transactional
public class ItemControllerTest extends AbstractTest {

	@MockBean
	private SecurityService securityService;
	private Item item;
	private ItemDTO itemDTO;
	@MockBean
	private UserServiceInterceptor userServiceInterceptor;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	@MockBean
	private ItemRepository itemDAO;

	@Override
	@Before
	public void setup() {
		super.setup();
		itemDTO = new ItemDTO();
		itemDTO.setName("item");
		itemDTO.setPrice(1);
		itemDTO.setDescription("An item");
		itemDTO.setItemType("Test Type");
		ItemType itemType = itemTypeDAO.findByName("Test Type");
		this.item = new Item("item", 1, itemType, "An item");
		try {
			when(userServiceInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addItemFormTest() throws Exception {
		this.mvc.perform(get(URI.ADD_ITEM_FORM)).andExpect(status().isOk()).andExpect(view().name("addItem"));
	}

	@Test
	public void addItemTest_isSuccessful_thenNoException() throws Exception {
		when(itemDAO.save(any(Item.class))).thenReturn(item);
		this.mvc.perform(buildPostRequest(itemDTO)).andExpect(status().isOk()).andExpect(model().hasNoErrors())
				.andExpect(view().name("addedItem")).andExpect(model().attributeExists("item"));
	}

	@Test
	public void addItemTest_withNullValues_Failed_thenNoException() throws Exception {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setName(null);
		itemDTO.setPrice(-1);
		itemDTO.setDescription(null);
		itemDTO.setItemType(null);
		when(itemDAO.save(any(Item.class))).thenReturn(item);
		this.mvc.perform(buildPostRequest(itemDTO)).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("itemDTO"))
				.andExpect(model().attributeHasFieldErrors("itemDTO", "name", "price", "description", "itemType"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "name", "NotBlank"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "price", "Positive"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "itemType", "NotBlank"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "description", "NotBlank"))
				.andExpect(view().name("addItem"));
	}

	@Test
	public void addItemTest_withBlankValues_Failed_thenNoException() throws Exception {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setName("");
		itemDTO.setPrice(0);
		itemDTO.setDescription("");
		itemDTO.setItemType("");
		when(itemDAO.save(any(Item.class))).thenReturn(item);
		this.mvc.perform(buildPostRequest(itemDTO)).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("itemDTO"))
				.andExpect(model().attributeHasFieldErrors("itemDTO", "name", "price", "description", "itemType"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "name", "NotBlank"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "price", "Positive"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "itemType", "NotBlank"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "description", "NotBlank"))
				.andExpect(view().name("addItem"));
	}

	@Test
	public void addItemTest_withInvalidItemType() throws Exception {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setName(this.itemDTO.getName());
		itemDTO.setPrice(this.itemDTO.getPrice());
		itemDTO.setDescription(this.itemDTO.getDescription());
		itemDTO.setItemType("invalidType");
		when(itemDAO.save(any(Item.class))).thenReturn(item);
		this.mvc.perform(buildPostRequest(itemDTO)).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("itemDTO"))
				.andExpect(model().attributeHasFieldErrors("itemDTO", "itemType"))
				.andExpect(model().attributeHasFieldErrorCode("itemDTO", "itemType", "ExistsInDatabase"))
				.andExpect(view().name("addItem"));
	}

	@Test
	public void getItemsTest_successful() throws Exception {
		LinkedList<Item> list = new LinkedList<Item>();
		list.add(item);
		when(itemDAO.findAll()).thenReturn(list);
		MvcResult result = this.mvc.perform(get(URI.ITEMS)).andExpect(status().isOk()).andReturn();
		assertEquals(
				"[{\"id\":null,\"name\":\"item\",\"price\":1,\"itemType\":{\"id\":1,\"name\":\"Test Type\"},\"description\":\"An item\"}]",
				result.getResponse().getContentAsString());
	}

	private MockHttpServletRequestBuilder buildPostRequest(ItemDTO itemDTO) {
		return post(URI.ADD_ITEM).param("name", itemDTO.getName()).param("price", String.valueOf(itemDTO.getPrice()))
				.param("itemType", itemDTO.getItemType()).param("description", itemDTO.getDescription());
	}
}
