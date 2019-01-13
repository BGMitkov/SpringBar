package bar.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bar.dao.NameRepository;
import bar.validator.ExistsByNameValidator;

/**
 * Annotation for validating EmployeeRole.
 * 
 * @author bgmitkov
 *
 */
@Documented
@Constraint(validatedBy = ExistsByNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsInDatabase {
	String message() default "Invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<? extends NameRepository<?/*, ?*/>> repository();
}
