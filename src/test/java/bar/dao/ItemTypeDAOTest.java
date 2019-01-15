package bar.dao;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bar.SpringBarApplication;
import bar.model.ItemType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBarApplication.class)
public class ItemTypeDAOTest {
	@Autowired
	private ItemTypeDAO itemTypeDAO;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void saveTest_whenSucessful() {
		ItemType itemType = new ItemType("ValidTestItemType");
		ItemType savedItemType = itemTypeDAO.save(itemType);
		assertNotNull(savedItemType);
		assertEquals("ValidTestItemType", savedItemType.getName());
		assertThat(savedItemType.getId(), greaterThan(0l));
	}

	@Test
	public void saveTest_fails_whenNullName() {
		exceptionRule.expect(ConstraintViolationException.class);
		exceptionRule.expectMessage("must not be blank");
		ItemType itemType = new ItemType(null);
		itemTypeDAO.save(itemType);
	}

	@Test
	public void saveTest_fails_whenEmptyName() {
		exceptionRule.expect(ConstraintViolationException.class);
		exceptionRule.expectMessage("must not be blank");
		ItemType itemType = new ItemType("");
		itemTypeDAO.save(itemType);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void saveTestDuplicate_thenDataIntegrityViolationException() {
		ItemType itemType = new ItemType("TestItemType");
		itemTypeDAO.save(itemType);
	}

}
