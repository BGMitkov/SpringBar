package bar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.Permission;

@Repository
public interface PermissionDAO extends CrudRepository<Permission, Long> {
	Permission findByUri(String uri);
}
