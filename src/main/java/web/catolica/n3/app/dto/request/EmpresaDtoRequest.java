package web.catolica.n3.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.UUID;
import web.catolica.n3.app.validations.ValidHorarioExpediente;

@ValidHorarioExpediente
public record EmpresaDtoRequest(
    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(
        regexp = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}",
        message = "CNPJ inválido. Formato esperado: 00.000.000/0000-00"
    )
    String cnpj,

    @NotBlank(message = "Nome é obrigatório") String nome,

    @NotNull(message = "ID do usuário é obrigatório") UUID userId,

    @NotNull(message = "Início do expediente é obrigatório")
    LocalTime inicioExpediente,

    @NotNull(message = "Fim do expediente é obrigatório")
    LocalTime fimExpediente
) {}
