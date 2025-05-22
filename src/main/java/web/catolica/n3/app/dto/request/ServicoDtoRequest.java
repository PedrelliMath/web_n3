package web.catolica.n3.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;
import web.catolica.n3.app.validations.ValidDuracao;

public record ServicoDtoRequest(
    @NotBlank(message = "O nome do serviço é obrigatório") String nome,

    @NotNull(message = "O ID da empresa é obrigatório") UUID empresaId,

    @NotNull(message = "O valor do serviço é obrigatório")
    @Positive(message = "O valor do serviço deve ser positivo")
    BigDecimal valor,

    @PositiveOrZero(message = "A duração deve ser maior ou igual a zero")
    @ValidDuracao
    int duracao
) {}
