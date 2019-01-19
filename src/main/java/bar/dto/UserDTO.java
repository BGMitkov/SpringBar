package bar.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import bar.annotation.ExistsInDatabase;
import bar.annotation.Extended;
import bar.annotation.HasDigit;
import bar.annotation.HasLowerCaseCharacter;
import bar.annotation.HasSpecialSymbol;
import bar.annotation.HasUpperCaseChar;
import bar.annotation.UserNameConstraint;
import bar.repository.EmployeeRoleRepository;

public class UserDTO {
	@UserNameConstraint(message = "*The username contains invalid characters")
	private String name;
	@HasLowerCaseCharacter
	@HasUpperCaseChar
	@HasDigit
	@HasSpecialSymbol
	private String password;
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@NotEmpty(message = "*Please select an employee role")
	@ExistsInDatabase(repository = EmployeeRoleRepository.class, groups = Extended.class)
	private String employeeRole;
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "*Please select your birthdate")
	@Past
	private LocalDate birthDate;

	public UserDTO() {
	}

	public UserDTO(String name, String password, String email, String employeeRole, LocalDate birthDate) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.employeeRole = employeeRole;
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
