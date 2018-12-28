package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bar.annotation.UserNameConstraint;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, String> {

	@Override
	public void initialize(UserNameConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return name != null && name.matches("^(?=.{8,20}$)(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
	}
}
