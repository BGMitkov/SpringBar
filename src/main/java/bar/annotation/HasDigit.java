package bar.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bar.validator.DigitValidator;
import bar.validator.UpperCaseCharValidator;

@Documented
@Constraint(validatedBy = DigitValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasDigit {
	String message() default "Does not contain a digit";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
