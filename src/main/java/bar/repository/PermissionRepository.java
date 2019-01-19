package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
	Permission findByUri(String uri);
}
