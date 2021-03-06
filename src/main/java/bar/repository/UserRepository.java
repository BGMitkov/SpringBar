package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByName(String name);
	
	User findByEmail(String email);
	
//	@Autowired
//	private UserMapper userMapper;
//
//	public UserDAO() {
//		setClazz(User.class);
//	}
//
//	public User findByProperty(String propertyName, Object propertyValue) {
//		String queryString = String.format("SELECT * FROM User u WHERE u.%s=\"%s\" Limit 1", propertyName,
//				propertyValue.toString());
//
//		List<User> query = jdbcTemplateObject.query(queryString, userMapper);
//		return query.size() > 0 ? query.get(0) : null;
//	}
//
//	@Override
//	public void create(User entity) throws EntityExistsException {
//		String insert = "insert into User (name, password, email, role, birthdate) values (?, ?, ?, ?, ?)";
//		jdbcTemplateObject.update(insert, entity.getName(), entity.getPassword(), entity.getEmail(),
//				entity.getRole().toString(), entity.getBirthDate());
//		super.create(entity);
//	}
}
