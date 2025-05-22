package web.catolica.n3.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuracaoValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDuracao {
    String message() default "A duração deve ser múltipla de 30 minutos (ex: 30, 60, 90, 120...)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
