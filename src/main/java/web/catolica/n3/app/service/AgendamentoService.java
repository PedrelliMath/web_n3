package web.catolica.n3.app.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.catolica.n3.app.dto.request.AgendamentoDtoRequest;
import web.catolica.n3.app.dto.response.AgendamentoDtoResponse;
import web.catolica.n3.app.mapper.AgendamentoMapper;
import web.catolica.n3.app.repository.AgendamentoRepository;
import web.catolica.n3.app.repository.ServicoRepository;
import web.catolica.n3.app.repository.UsuarioRepository;
import web.catolica.n3.app.schemas.AgendamentoSchema;
import web.catolica.n3.app.schemas.ServicoSchema;
import web.catolica.n3.app.schemas.UsuarioSchema;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;

    @Autowired
    public AgendamentoService(
        AgendamentoRepository agendamentoRepository,
        UsuarioRepository usuarioRepository,
        ServicoRepository servicoRepository
    ) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
    }

    public AgendamentoDtoResponse criarAgendamento(AgendamentoDtoRequest dto) {
        UsuarioSchema usuario = usuarioRepository
            .findById(dto.userId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " + dto.userId()
                )
            );

        ServicoSchema servico = servicoRepository
            .findById(dto.servicoId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Serviço não encontrado com ID: " + dto.servicoId()
                )
            );

        AgendamentoSchema entidade = AgendamentoMapper.toEntity(
            dto,
            usuario,
            servico
        );
        return AgendamentoMapper.toDTO(agendamentoRepository.save(entidade));
    }

    public List<AgendamentoDtoResponse> listarAgendamentos() {
        return agendamentoRepository
            .findAll()
            .stream()
            .map(AgendamentoMapper::toDTO)
            .toList();
    }

    public AgendamentoDtoResponse buscarPorId(UUID id) {
        AgendamentoSchema agendamento = agendamentoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Agendamento não encontrado com ID: " + id
                )
            );
        return AgendamentoMapper.toDTO(agendamento);
    }

    public List<AgendamentoDtoResponse> listarPorUsuario(UUID userId) {
        return agendamentoRepository
            .findByUsuarioId(userId)
            .stream()
            .map(AgendamentoMapper::toDTO)
            .toList();
    }

    public List<AgendamentoDtoResponse> listarPorServico(UUID servicoId) {
        return agendamentoRepository
            .findByServicoId(servicoId)
            .stream()
            .map(AgendamentoMapper::toDTO)
            .toList();
    }

    public AgendamentoDtoResponse atualizarAgendamento(
        UUID id,
        AgendamentoDtoRequest agendamentoAtualizado
    ) {
        AgendamentoSchema agendamento = agendamentoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Agendamento não encontrado com ID: " + id
                )
            );

        UsuarioSchema usuario = usuarioRepository
            .findById(agendamentoAtualizado.userId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Usuário não encontrado com ID: " +
                    agendamentoAtualizado.userId()
                )
            );

        ServicoSchema servico = servicoRepository
            .findById(agendamentoAtualizado.servicoId())
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Serviço não encontrado com ID: " +
                    agendamentoAtualizado.servicoId()
                )
            );

        agendamento.setUser(usuario);
        agendamento.setServico(servico);
        agendamento.setData(agendamentoAtualizado.data());
        agendamento.setHoraInicio(agendamentoAtualizado.horaInicio());

        return AgendamentoMapper.toDTO(agendamentoRepository.save(agendamento));
    }

    public void deletarAgendamento(UUID id) {
        AgendamentoSchema agendamento = agendamentoRepository
            .findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Agendamento não encontrado com ID: " + id
                )
            );
        agendamentoRepository.delete(agendamento);
    }
}
