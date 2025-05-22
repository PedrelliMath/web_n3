package web.catolica.n3.app.dto.response;

import java.time.LocalTime;
import java.util.UUID;

public record EmpresaDtoResponse(
    UUID id,
    UUID userId,
    String cnpj,
    String nome,
    LocalTime inicioExpediente,
    LocalTime fimExpediente
) {}
