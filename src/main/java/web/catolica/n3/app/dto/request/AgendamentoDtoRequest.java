package web.catolica.n3.app.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoDtoRequest(
    @NotNull UUID servicoId,

    @NotNull UUID userId,

    @NotNull @Future(message = "A data deve ser futura") LocalDate data,

    @NotNull LocalTime horaInicio
) {}
