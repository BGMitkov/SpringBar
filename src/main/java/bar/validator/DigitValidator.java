package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bar.annotation.HasDigit;

public class DigitValidator implements ConstraintValidator<HasDigit, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(?=.*[0-9])[\\w@#$%^&*+=]+$");
	}

}
