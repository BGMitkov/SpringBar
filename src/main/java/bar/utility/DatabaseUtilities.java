package bar.utility;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import bar.dao.EmployeeRoleDAO;
import bar.dao.ItemDAO;
import bar.dao.ItemTypeDAO;
import bar.dao.PermissionDAO;
import bar.dao.UserDAO;
import bar.model.EmployeeRole;
import bar.model.Item;
import bar.model.ItemType;
import bar.model.Permission;
import bar.model.User;

@Component
@Transactional
public class DatabaseUtilities {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseUtilities.class);

	private static final ItemType[] ITEM_TYPES = { new ItemType("TestItemType") };
	private static final EmployeeRole[] EMPLOYEE_ROLES = { new EmployeeRole("testEmployeeRole"),
			new EmployeeRole("manager"), new EmployeeRole("server"), new EmployeeRole("bartender"),
			new EmployeeRole("testRoleWithPermission"), new EmployeeRole("testRoleWithOutPermission") };
//	private static final Item[] ITEMS = { new Item("Test Item", 1, null, "A test item") };
//	private static final User[] USERS = {
//			new User("test_user", "testUser1@", "test.user@somemail.com", null, LocalDate.of(2018, 1, 1)),
//			new User("test2", "test", "test2.user@somemail.com", Role.SERVER, "2018-01-01"),
//			new User("test3", "test", "second.user@somemail.com", Role.BARTENDER, "2018-01-01"),
//			new User("Third User", "98411TA", "third.user@somemail.com", Role.MANAGER, "2018-01-01")
//	};

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;
	@Autowired
	private PermissionDAO permissionDAO;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void storeTestData() {
		logger.info("Storing test data.");
		deleteData();
		addTestEmployeeRoles();
		addTestUsers();
		addTestItemTypes();
		addTestItems();
		addTestPermissions();
	}

	private void addTestPermissions() {
		EmployeeRole manager = employeeRoleDAO.findByName("manager");
		EmployeeRole server = employeeRoleDAO.findByName("server");
		EmployeeRole bartender = employeeRoleDAO.findByName("bartenter");
		EmployeeRole testRoleWithPermission = employeeRoleDAO.findByName("testRoleWithPermission");
		permissionDAO.save(new Permission("/SpringBar/view/registerEmployee", manager));
		permissionDAO.save(new Permission("/SpringBar/registerEmployeeSubmit", manager));
		permissionDAO.save(new Permission("/SpringBar/order", manager, server));
		permissionDAO.save(new Permission("/SpringBar/getWaitingOrders", manager, bartender, server));
		permissionDAO.save(new Permission("/SpringBar/getAcceptedOrders", manager, bartender));
		permissionDAO.save(new Permission("/SpringBar/acceptOrder", manager, bartender));
		permissionDAO.save(new Permission("/SpringBar/printBill", manager, server));
		permissionDAO.save(new Permission("/SpringBar/view/addItemForm", manager));
		permissionDAO.save(new Permission("/SpringBar/addItem", manager));
//		permissionDAO.save(new Permission("/SpringBar/", manager, bartender, server));
		permissionDAO.save(new Permission("/SpringBar/signOut", manager, bartender, server));
		permissionDAO.save(new Permission("/SpringBar/test", testRoleWithPermission));
		permissionDAO.save(new Permission("/SpringBar/items", manager, server));
	}

	private void addTestEmployeeRoles() {
		logger.info("Adding test employee roles");
		for (EmployeeRole employeeRole : EMPLOYEE_ROLES) {
			employeeRoleDAO.save(employeeRole);
		}
	}

	private void addTestUsers() {
		logger.info("Adding test users");
		EmployeeRole employeeRole = employeeRoleDAO.findByName("manager");
		String encodedPassword = bCryptPasswordEncoder.encode("testUser1@");
		User user = new User("test_user", encodedPassword, "test.user@somemail.com", employeeRole,
				LocalDate.of(2018, 1, 1));

		userDAO.save(user);
	}

	private void addTestItemTypes() {
		logger.info("Adding test item types");
		for (ItemType itemType : ITEM_TYPES) {
			itemTypeDAO.save(itemType);
			logger.info("Added Item Type : {}", itemType.toString());
		}
	}

	public void addTestItems() {
		logger.info("Adding test items.");

		ItemType itemType = itemTypeDAO.findByName("TestItemType");
		Item item = new Item("Test Item", 1, itemType, "A test item");
		item.setItemType(itemType);

		item = itemDAO.save(item);

		logger.info("Item added: {} with type: {}", item, itemType);
	}

	private void deleteData() {
		logger.info("Deleting all data");
		employeeRoleDAO.deleteAll();
		userDAO.deleteAll();
		itemDAO.deleteAll();
		itemTypeDAO.deleteAll();
		itemDAO.deleteAll();
		permissionDAO.deleteAll();
	}
}
