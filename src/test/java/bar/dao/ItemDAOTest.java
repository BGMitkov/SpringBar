package bar.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bar.SpringBarApplication;
import bar.model.Item;
import bar.model.ItemType;
import bar.repository.ItemRepository;
import bar.repository.ItemTypeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
public class ItemDAOTest {
	@Autowired
	private ItemRepository itemDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	private ItemType itemType;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		itemType = itemTypeDAO.findByName("TestItemType");
	}

	@Test
	public void saveTest() {
		Item item = new Item("testItem", 1, itemType, "testDescription");
		Item storedItem = itemDAO.save(item);
		assertNotNull(storedItem);
		assertThat(storedItem.getId(), Matchers.greaterThan(0l));
	}

	@Test
	public void when_save_dublicateName_thenDataIntegrityViolationException() {
		expectedException.expect(DataIntegrityViolationException.class);
		Item item = new Item("Test Item", 1, itemType, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_nullName() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("must not be blank");
		Item item = new Item(null, 1, itemType, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_blankName() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("must not be blank");
		Item item = new Item("", 1, itemType, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_zeroPrice() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("must be greater than 0");
		Item item = new Item("testItem", 0, itemType, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_nullItemType() {
		expectedException.expect(DataIntegrityViolationException.class);
		Item item = new Item("testItem", 1, null, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_newItemType() {
		expectedException.expect(InvalidDataAccessApiUsageException.class);
		ItemType itemType = new ItemType("Invalid Item Type");
		Item item = new Item("testItem", 1, itemType, "testDescription");
		itemDAO.save(item);
	}

	@Test
	public void when_save_nullDescription() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("must not be blank");
		Item item = new Item("testItem", 1, itemType, null);
		itemDAO.save(item);
	}

	@Test
	public void when_save_blankDescription() {
		expectedException.expect(ConstraintViolationException.class);
		expectedException.expectMessage("must not be blank");
		Item item = new Item("testItem", 1, itemType, "");
		itemDAO.save(item);
	}
}
