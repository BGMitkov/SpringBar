package bar.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import bar.annotation.HasDigit;
import bar.annotation.HasLowerCaseCharacter;
import bar.annotation.HasSpecialSymbol;
import bar.annotation.HasUpperCaseChar;
import bar.annotation.UserNameConstraint;

@Entity
@Table(name = "user")
public class User {
	public final static String PROPERTY_NAME = "name";
	public final static String PROPERTY_EMAIL = "email";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(unique = true)
	@NotEmpty(message = "*Please provide your username")
	@UserNameConstraint(message = "*The username contains invalid characters")
	private String name;
	@HasLowerCaseCharacter
	@HasUpperCaseChar
	@HasDigit
	@HasSpecialSymbol
	private String password;
	@Column(unique = true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@NotNull(message = "*Please select an employee role")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_role_id")
	private EmployeeRole employeeRole;
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "*Please select your birthdate")
	@Past
	private LocalDate birthDate;

	public User(String name, String password, String email, EmployeeRole employeeRole, LocalDate birthDate) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.employeeRole = employeeRole;
		this.birthDate = birthDate;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployeeRole(EmployeeRole role) {
		this.employeeRole = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", employeeRole="
				+ employeeRole + ", birthDate=" + birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((employeeRole == null) ? 0 : employeeRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (employeeRole != other.employeeRole)
			return false;
		return true;
	}

}
