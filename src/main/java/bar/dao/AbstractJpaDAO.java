package bar.dao;

import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractJpaDAO<T> {

	private Class<T> clazz;
	
	@Qualifier("jdbcTest")
	@Autowired
	JdbcTemplate jdbcTemplateObject;

//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@PostConstruct
//	public void init() {
//		entityManager = sessionFactory.createEntityManager();
//	}
	
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	
//	public T findOne(long id) {
//		return entityManager.find(clazz, id);
//	}
//
//	public List<T> findAll() {
//		return castList(entityManager.createQuery("from" + clazz.getName()).getResultList());
//	}
//
	public void create(T entity) throws EntityExistsException {
	};
//
//	public T update(T entity) {
//		return entityManager.merge(entity);
//	}
//
//	public void delete(T entity) {
//		entityManager.remove(entity);
//	}
//
//	public void deleteById(long entityId) {
//		T entity = findOne(entityId);
//		delete(entity);
//	}
//
//	private List<T> castList(Collection<?> collection) {
//		List<T> results = new ArrayList<>(collection.size());
//		for (Object object : collection) {
//			results.add(clazz.cast(object));
//		}
//		return results;
//
//	}
}
