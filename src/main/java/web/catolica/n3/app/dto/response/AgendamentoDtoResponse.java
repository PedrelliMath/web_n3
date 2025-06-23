package web.catolica.n3.app.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoDtoResponse(
    UUID id,
    ServicoDtoResponse servico,
    UsuarioDtoResponse user,
    LocalDate data,
    LocalTime horaInicio
) {}
