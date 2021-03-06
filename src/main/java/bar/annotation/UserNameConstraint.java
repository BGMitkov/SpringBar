package bar.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bar.validator.UserNameValidator;

@Documented
@Constraint(validatedBy=UserNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {
	String message() default "Invalid phone number";
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default {};
}
