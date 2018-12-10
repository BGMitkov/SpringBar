package bar.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractJpaDAO<T extends Serializable> {

	private Class<T> clazz;

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
		
	}
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
