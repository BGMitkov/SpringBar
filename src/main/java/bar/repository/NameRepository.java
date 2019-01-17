package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * An extension to the CrudRepository that adds a query methods for searching
 * with a name;
 * 
 * @author bgmitkov
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface NameRepository<T/*, ID*/> /*extends CrudRepository<T, ID>*/ {

	T findByName(String name);

	boolean existsByName(String name);
}
