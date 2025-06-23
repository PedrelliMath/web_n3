package web.catolica.n3.app.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoDtoResponse(
    UUID id,
    EmpresaDtoResponse empresa,
    String nome,
    BigDecimal valor,
    int duracao
) {}
