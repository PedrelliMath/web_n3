package web.catolica.n3.app.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public record AgendamentoDtoRequest(
    @NotNull UUID servicoId,

    @NotNull UUID userId,

    @NotNull @Future(message = "A data deve ser futura") Date data,

    @NotNull LocalTime horaInicio
) {}
