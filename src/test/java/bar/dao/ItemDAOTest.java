package bar.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bar.SpringBarApplication;
import bar.model.Item;
import bar.model.ItemType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
public class ItemDAOTest {
	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;

	@Test
	public void saveTest() {
		ItemType itemType = itemTypeDAO.findByName("TestItemType");
		Item item = new Item("testItem", 1, itemType, "testDescription");
		Item storedItem = itemDAO.save(item);
		assertNotNull(storedItem);
		assertThat(storedItem.getId(), Matchers.greaterThan(0l));
	}
}
