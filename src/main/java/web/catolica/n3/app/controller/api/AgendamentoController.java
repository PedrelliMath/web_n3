package web.catolica.n3.app.controller.api;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.catolica.n3.app.dto.request.AgendamentoDtoRequest;
import web.catolica.n3.app.dto.response.AgendamentoDtoResponse;
import web.catolica.n3.app.service.AgendamentoService;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoDtoResponse> criarAgendamento(
        @RequestBody AgendamentoDtoRequest agendamento
    ) {
        AgendamentoDtoResponse novoAgendamento =
            agendamentoService.criarAgendamento(agendamento);
        return new ResponseEntity<>(novoAgendamento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDtoResponse>> listarAgendamentos() {
        List<AgendamentoDtoResponse> agendamentos =
            agendamentoService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDtoResponse> buscarPorId(
        @PathVariable UUID id
    ) {
        AgendamentoDtoResponse agendamento = agendamentoService.buscarPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AgendamentoDtoResponse>> listarPorUsuario(
        @PathVariable UUID usuarioId
    ) {
        List<AgendamentoDtoResponse> agendamentos =
            agendamentoService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/servico/{servico_id}")
    public ResponseEntity<List<AgendamentoDtoResponse>> listarPorServico(
        @PathVariable UUID servicoId
    ) {
        List<AgendamentoDtoResponse> agendamentos =
            agendamentoService.listarPorServico(servicoId);
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDtoResponse> atualizarAgendamento(
        @PathVariable UUID id,
        @RequestBody AgendamentoDtoRequest agendamentoAtualizado
    ) {
        AgendamentoDtoResponse agendamento =
            agendamentoService.atualizarAgendamento(id, agendamentoAtualizado);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable UUID id) {
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
