package bar.dao;

import javax.persistence.TypedQuery;

import bar.model.User;

public class UserDAO extends AbstractJpaDAO<User> {

	public UserDAO() {
		setClazz(User.class);
	}

	public User findByProperty(String propertyName, Object propertyValue) {
		String queryString = String.format("SELECT u FROM User u WHERE u.%s=:propertyValue", propertyName);
		TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
		query.setParameter("propertyValue", propertyValue);
		return query(query);
	}

	private User query(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
