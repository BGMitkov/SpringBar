package bar.utility;

import java.security.Permissions;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bar.dao.EmployeeRoleDAO;
import bar.dao.ItemDAO;
import bar.dao.ItemTypeDAO;
import bar.dao.PermissionDAO;
import bar.dao.UserDAO;
import bar.model.EmployeeRole;
import bar.model.ItemType;
import bar.model.Permission;
import bar.model.User;

@Component
public class DatabaseUtilities {
	private static final ItemType[] ITEM_TYPES = { new ItemType("type") };
	private static final EmployeeRole[] EMPLOYEE_ROLES = { new EmployeeRole("manager"), new EmployeeRole("server"),
			new EmployeeRole("bartender") };
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

	public void storeTestData() {
		deleteData();
		addTestEmployeeRoles();
		addTestUsers();
		addTestItemTypes();
		addTestPermissions();
	}

	private void addTestPermissions() {
		EmployeeRole manager = employeeRoleDAO.findByName("manager");
		EmployeeRole server = employeeRoleDAO.findByName("server");
		EmployeeRole bartender = employeeRoleDAO.findByName("bartenter");

		permissionDAO.save(new Permission("/SpringBar/view/registerEmployee", manager));
		permissionDAO.save(new Permission("/SpringBar/registerEmployeeSubmit", manager));
		permissionDAO.save(new Permission("/SpringBar/order", manager, server));
		permissionDAO.save(new Permission("/SpringBar/getWaitingOrders", manager, bartender, server));
		permissionDAO.save(new Permission("/SpringBar/getAcceptedOrders", manager, bartender));
		permissionDAO.save(new Permission("/SpringBar/acceptOrder", manager, bartender));
		permissionDAO.save(new Permission("/SpringBar/printBill", manager, server));
		permissionDAO.save(new Permission("/SpringBar/view/addItemForm", manager));
	}

	private void addTestEmployeeRoles() {
		for (EmployeeRole employeeRole : EMPLOYEE_ROLES) {
			employeeRoleDAO.save(employeeRole);
		}
	}

	private void addTestUsers() {
		EmployeeRole employeeRole = employeeRoleDAO.findByName("manager");
		User user = new User("test_user", "testUser1@", "test.user@somemail.com", employeeRole,
				LocalDate.of(2018, 1, 1));

		userDAO.save(user);
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
