package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bar.annotation.HasUpperCaseChar;

public class UpperCaseCharValidator implements ConstraintValidator<HasUpperCaseChar, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(?=.*[A-Z])[\\w@#$%^&*+=]+$");
	}

}
