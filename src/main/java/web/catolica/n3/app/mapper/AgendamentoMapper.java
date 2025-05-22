package web.catolica.n3.app.mapper;

import web.catolica.n3.app.dto.request.AgendamentoDtoRequest;
import web.catolica.n3.app.dto.response.AgendamentoDtoResponse;
import web.catolica.n3.app.schemas.AgendamentoSchema;
import web.catolica.n3.app.schemas.ServicoSchema;
import web.catolica.n3.app.schemas.UsuarioSchema;

public class AgendamentoMapper {

    public static AgendamentoSchema toEntity(
        AgendamentoDtoRequest dto,
        UsuarioSchema usuario,
        ServicoSchema servico
    ) {
        AgendamentoSchema agendamento = new AgendamentoSchema();
        agendamento.setData(dto.data());
        agendamento.setHoraInicio(dto.horaInicio());
        agendamento.setUser(usuario);
        agendamento.setServico(servico);
        return agendamento;
    }

    public static AgendamentoDtoResponse toDTO(AgendamentoSchema agendamento) {
        return new AgendamentoDtoResponse(
            agendamento.getId(),
            agendamento.getServico().getId(),
            agendamento.getUser().getId(),
            agendamento.getData(),
            agendamento.getHoraInicio()
        );
    }
}
