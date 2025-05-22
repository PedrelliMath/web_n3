package web.catolica.n3.app.dto.response;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record AgendamentoDtoResponse(
    UUID id,
    UUID servicoId,
    UUID userId,
    Date data,
    LocalTime horaInicio
) {}
