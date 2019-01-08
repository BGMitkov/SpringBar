package bar.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String uri;
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "permission_employee_role", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "employee_role_id"))
	private Set<EmployeeRole> employeeRoles;

	public Permission() {
		this.employeeRoles = new HashSet<>();
	}

	public Permission(String uri, EmployeeRole... employeeRoles) {
		this.uri = uri;
		this.employeeRoles = new HashSet<>(Arrays.asList(employeeRoles));
	}

	public void addEmployeeRole(EmployeeRole employeeRole) {
		employeeRoles.add(employeeRole);
	}

	public void removeEmployeeRole(EmployeeRole employeeRole) {
		employeeRoles.remove(employeeRole);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Set<EmployeeRole> getEmployeeRoles() {
		return employeeRoles;
	}

	public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	public boolean hasAccess(EmployeeRole employeeRole) {
		return employeeRoles.contains(employeeRole);
	}
}
