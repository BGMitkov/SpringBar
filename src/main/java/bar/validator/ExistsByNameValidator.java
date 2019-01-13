package bar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import bar.annotation.ExistsInDatabase;
import bar.dao.NameRepository;

public class ExistsByNameValidator implements ConstraintValidator<ExistsInDatabase, String> {

	@Autowired
	private ApplicationContext applicationContext;
	private NameRepository<?/* , ? */> nameRepository;

	@Override
	public void initialize(ExistsInDatabase constraintAnnotation) {
		nameRepository = applicationContext.getBean(constraintAnnotation.repository());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return nameRepository.existsByName(value);
	}

}
