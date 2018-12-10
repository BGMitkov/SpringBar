package bar.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.TypedQuery;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import bar.model.User;

public class UserDAO extends AbstractJpaDAO<User> {

	public UserDAO() {
		setClazz(User.class);
	}

//	public User findByProperty(String propertyName, Object propertyValue) {
//		String queryString = String.format("SELECT u FROM User u WHERE u.%s=:propertyValue", propertyName);
//		TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
//		query.setParameter("propertyValue", propertyValue);
//		return query(query);
//	}

	@Override
	public void create(User entity) throws EntityExistsException {
		String insert = "insert into User (name, password, email, role, birthdate) values (?, ?, ?, ?, ?)";
		jdbcTemplateObject.update(insert, entity.getName(), entity.getPassword(), entity.getEmail(), entity.getRole().toString(),
				entity.getBirthDate());
		super.create(entity);
	}

//	private User query(TypedQuery<User> query) {
//		try {
//			return query.getSingleResult();
//		} catch (Exception e) {
//			return null;
//		}
//	}
}
