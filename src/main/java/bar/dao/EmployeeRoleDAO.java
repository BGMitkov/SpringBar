package bar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.EmployeeRole;

@Repository
public interface EmployeeRoleDAO extends CrudRepository<EmployeeRole, Long> {
	EmployeeRole findByName(String name);

	boolean existsByName(String name);
}
