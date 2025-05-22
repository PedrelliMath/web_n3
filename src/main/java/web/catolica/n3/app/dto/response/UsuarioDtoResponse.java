package web.catolica.n3.app.dto.response;

import java.util.UUID;

public record UsuarioDtoResponse(
    UUID id,
    String name,
    String email,
    String cpf
) {}
