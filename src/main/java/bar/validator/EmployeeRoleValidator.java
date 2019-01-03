package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import bar.annotation.ExistsInDataBase;
import bar.dao.EmployeeRoleDAO;

public class EmployeeRoleValidator implements ConstraintValidator<ExistsInDataBase, String> {

	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return employeeRoleDAO.existsByName(value);
	}

}
