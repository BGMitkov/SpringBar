package bar.utility;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bar.dao.UserDAO;
import bar.model.Role;
import bar.model.User;

@Component
public class DatabaseUtilities {
	@Autowired
	private UserDAO userDAO;

	private static User[] USERS = {
			new User("test", "test", "test.user@somemail.com", Role.MANAGER, LocalDate.of(2018, 1, 1)),
//			new User("test2", "test", "test2.user@somemail.com", Role.SERVER, "2018-01-01"),
//			new User("test3", "test", "second.user@somemail.com", Role.BARTENDER, "2018-01-01"),
//			new User("Third User", "98411TA", "third.user@somemail.com", Role.MANAGER, "2018-01-01")
	};

	public void storeTestData() {
		deleteData();
		addTestUsers();
	}

	private void addTestUsers() {
		for (User user : USERS) {
			userDAO.save(user);
		}
	}

	public void deleteData() {
		userDAO.deleteAll();
	}
}
