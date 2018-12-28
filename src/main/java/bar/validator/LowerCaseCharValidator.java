package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bar.annotation.HasLowerCaseCharacter;

public class LowerCaseCharValidator implements ConstraintValidator<HasLowerCaseCharacter, String> {
	@Override
	public void initialize(HasLowerCaseCharacter constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
//		boolean isValid = password.matches("^(?=.{8,20}$)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!\\s+$)$");

//		return value.matches("^(?=[a-z]+)(?=[A-Z]+)(?=[0-9]+)(?=[@#$%^&*+=]+)[a-zA-Z0-8@#$%^&*+=]{8,60}$");
		return value.matches("^(?=.*[a-z])[a-zA-Z0-9@#$%^&*+=]+$");
	}
}
