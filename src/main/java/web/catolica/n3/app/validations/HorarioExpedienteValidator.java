package web.catolica.n3.app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import web.catolica.n3.app.dto.request.EmpresaDtoRequest;

public class HorarioExpedienteValidator
    implements ConstraintValidator<ValidHorarioExpediente, EmpresaDtoRequest> {

    @Override
    public boolean isValid(
        EmpresaDtoRequest empresaDto,
        ConstraintValidatorContext context
    ) {
        if (empresaDto == null) return true;

        LocalTime inicio = empresaDto.inicioExpediente();
        LocalTime fim = empresaDto.fimExpediente();

        if (inicio == null || fim == null) return true;

        return !fim.isBefore(inicio);
    }
}
