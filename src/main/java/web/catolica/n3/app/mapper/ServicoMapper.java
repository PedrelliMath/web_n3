package web.catolica.n3.app.mapper;

import web.catolica.n3.app.dto.request.ServicoDtoRequest;
import web.catolica.n3.app.dto.response.ServicoDtoResponse;
import web.catolica.n3.app.schemas.ServicoSchema;

public class ServicoMapper {

    public static ServicoSchema toEntity(ServicoDtoRequest dto) {
        ServicoSchema entity = new ServicoSchema();
        entity.setNome(dto.nome());
        entity.setValor(dto.valor());
        entity.setDuracao(dto.duracao());
        return entity;
    }

    public static ServicoDtoResponse toDTO(ServicoSchema entity) {
        return new ServicoDtoResponse(
            entity.getId(),
            entity.getEmpresa().getId(),
            entity.getNome(),
            entity.getValor(),
            entity.getDuracao()
        );
    }
}
