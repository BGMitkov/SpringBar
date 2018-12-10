package bar.utilities;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import bar.dao.UserDAO;
import bar.model.Role;
import bar.model.User;

public class DatabaseUtilities {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private UserDAO userDAO;

	private static User[] USERS = { new User("test", "test", "test.user@somemail.com", Role.MANAGER, "2018-01-01"),
	        new User("test2", "test", "test2.user@somemail.com", Role.SERVER, "2018-01-01"),
	        new User("test3", "test", "second.user@somemail.com", Role.BARTENDER, "2018-01-01"),
	        new User("Third User", "98411TA", "third.user@somemail.com", Role.MANAGER, "2018-01-01") };

	public void storeTestData() {
		addTestUsers();
	}

	private void addTestUsers() {
		for (User user : USERS) {
			userDAO.create(user);
		}
	}

	public void deleteData() {
		em.createQuery("DELETE FROM User").executeUpdate();
	}
}
