package web.catolica.n3.app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DuracaoValidator
    implements ConstraintValidator<ValidDuracao, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value > 0 && value % 30 == 0;
    }
}
