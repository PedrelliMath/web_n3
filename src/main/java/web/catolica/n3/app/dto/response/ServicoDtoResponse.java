package web.catolica.n3.app.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoDtoResponse(
    UUID id,
    UUID empresaId,
    String nome,
    BigDecimal valor,
    int duracao
) {}
