package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import bar.annotation.HasSpecialSymbol;

public class SpecialSymbolValidator implements ConstraintValidator<HasSpecialSymbol, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches("^(?=.*[@#$%^&*_+=])[\\w@#$%^&*+=]+$");
	}

}
