package web.catolica.n3.app.mapper;

import web.catolica.n3.app.dto.request.EmpresaDtoRequest;
import web.catolica.n3.app.dto.response.EmpresaDtoResponse;
import web.catolica.n3.app.schemas.EmpresaSchema;
import web.catolica.n3.app.schemas.UsuarioSchema;

public class EmpresaMapper {

    public static EmpresaSchema toEntity(
        EmpresaDtoRequest dto,
        UsuarioSchema usuario
    ) {
        EmpresaSchema empresa = new EmpresaSchema();
        empresa.setCnpj(dto.cnpj());
        empresa.setNome(dto.nome());
        empresa.setUsuario(usuario);
        empresa.setInicioExpediente(dto.inicioExpediente());
        empresa.setFimExpediente(dto.fimExpediente());
        return empresa;
    }

    public static EmpresaDtoResponse toDTO(EmpresaSchema entity) {
        return new EmpresaDtoResponse(
            entity.getId(),
            entity.getUsuario().getId(),
            entity.getCnpj(),
            entity.getNome(),
            entity.getInicioExpediente(),
            entity.getFimExpediente()
        );
    }
}
