package bar.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractJpaDAO<T extends Serializable> {

	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T findOne(long id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return castList(entityManager.createQuery("from" + clazz.getName()).getResultList());
	}

	public void create(T entity) throws EntityExistsException {
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

	private List<T> castList(Collection<?> collection) {
		List<T> results = new ArrayList<>(collection.size());
		for (Object object : collection) {
			results.add(clazz.cast(object));
		}
		return results;

	}
}
