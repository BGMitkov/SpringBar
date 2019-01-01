package bar.utility;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bar.dao.EmployeeRoleDAO;
import bar.dao.ItemDAO;
import bar.dao.ItemTypeDAO;
import bar.dao.UserDAO;
import bar.model.EmployeeRole;
import bar.model.ItemType;
import bar.model.User;

@Component
public class DatabaseUtilities {
	private static final ItemType[] ITEM_TYPES = { new ItemType("type") };
	private static final EmployeeRole[] EMPLOYEE_ROLES = { new EmployeeRole("manager"), new EmployeeRole("server"),
			new EmployeeRole("bartender") };
	private static final User[] USERS = {
			new User("test_user", "testUser1@", "test.user@somemail.com", null, LocalDate.of(2018, 1, 1)),
//			new User("test2", "test", "test2.user@somemail.com", Role.SERVER, "2018-01-01"),
//			new User("test3", "test", "second.user@somemail.com", Role.BARTENDER, "2018-01-01"),
//			new User("Third User", "98411TA", "third.user@somemail.com", Role.MANAGER, "2018-01-01")
	};

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ItemTypeDAO itemTypeDAO;
	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;

	public void storeTestData() {
		deleteData();
		addTestEmployeeRoles();
		addTestUsers();
		addTestItemTypes();
	}

	private void addTestEmployeeRoles() {
		for (EmployeeRole employeeRole : EMPLOYEE_ROLES) {
			employeeRoleDAO.save(employeeRole);
		}
	}

	private void addTestUsers() {
		for (User user : USERS) {
			user.setEmployeeRole(employeeRoleDAO.findByName("manager"));
			userDAO.save(user);
		}
	}

	private void addTestItemTypes() {
		for (ItemType itemType : ITEM_TYPES) {
			itemTypeDAO.save(itemType);
		}
	}

	public void deleteData() {
		userDAO.deleteAll();
		itemTypeDAO.deleteAll();
		itemDAO.deleteAll();
		employeeRoleDAO.deleteAll();
	}
}
