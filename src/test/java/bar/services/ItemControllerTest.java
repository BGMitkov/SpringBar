package bar.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import bar.SpringBarApplication;
import bar.model.Item;
import bar.model.ItemType;
import bar.service.SecurityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
@WebAppConfiguration
public class ItemControllerTest extends AbstractTest {

	@MockBean
	private SecurityService securityService;
	@MockBean
	private Item item;
	@Autowired
	private TestDataInserter testDataInserter;

	@Override
	@Before
	public void setup() {
		super.setup();
		this.item = new Item("item", 100, new ItemType("type"), "An item");
	}

	@Test
	public void whenContextIsBootstrapped_thenNoExceptions() {
	}

	@Test
	public void addItemFormTest() throws Exception {
		this.mvc.perform(get("/addItemForm")).andExpect(status().isOk()).andExpect(view().name("addItem"));
	}

	@Test
	public void addItemTest_isSuccessful_thenNoException() throws Exception {
		this.mvc.perform(buildPostRequest(item)).andExpect(status().isOk()).andExpect(view().name("addedItem"))
				.andExpect(model().attributeExists("item"));
	}

	private MockHttpServletRequestBuilder buildPostRequest(Item item) {
		return post("/addItem").param("name", item.getName()).param("price", "100")
				.param("itemType", item.getItemType().toString()).param("description", item.getDescription());
	}
}
