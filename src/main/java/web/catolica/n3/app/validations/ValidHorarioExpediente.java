package web.catolica.n3.app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioExpedienteValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorarioExpediente {
    String message() default "Fim do expediente não pode ser antes do início do expediente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
