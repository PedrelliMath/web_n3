package web.catolica.n3.app.mapper;

import web.catolica.n3.app.dto.request.UsuarioDtoRequest;
import web.catolica.n3.app.dto.response.UsuarioDtoResponse;
import web.catolica.n3.app.schemas.UsuarioSchema;

public class UsuarioMapper {

    public static UsuarioSchema toEntity(UsuarioDtoRequest dto) {
        UsuarioSchema entity = new UsuarioSchema();
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());

        return entity;
    }

    public static UsuarioDtoResponse toDTO(UsuarioSchema entity) {
        return new UsuarioDtoResponse(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getCpf()
        );
    }
}
