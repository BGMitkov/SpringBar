package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.EmployeeRole;

@Repository
public interface EmployeeRoleRepository extends NameRepository<EmployeeRole/* , Long */>, CrudRepository<EmployeeRole, Long> {
//	EmployeeRole findByName(String name);

//	boolean existsByName(String name);
}
