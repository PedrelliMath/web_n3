package web.catolica.n3.app.controller.api;

import jakarta.validation.Valid;
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
import web.catolica.n3.app.dto.request.ServicoDtoRequest;
import web.catolica.n3.app.dto.response.ServicoDtoResponse;
import web.catolica.n3.app.service.ServicoService;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoDtoResponse> criarServico(
        @RequestBody @Valid ServicoDtoRequest dto
    ) {
        ServicoDtoResponse novoServico = servicoService.criarServico(dto);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServicoDtoResponse>> listarServicos() {
        List<ServicoDtoResponse> servicos = servicoService.listarServicos();
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDtoResponse> buscarPorId(
        @PathVariable UUID id
    ) {
        ServicoDtoResponse servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(servico);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ServicoDtoResponse>> listarPorEmpresa(
        @PathVariable UUID empresaId
    ) {
        List<ServicoDtoResponse> servicos = servicoService.listarPorEmpresa(
            empresaId
        );
        return ResponseEntity.ok(servicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDtoResponse> atualizarServico(
        @PathVariable UUID id,
        @RequestBody @Valid ServicoDtoRequest dto
    ) {
        ServicoDtoResponse servico = servicoService.atualizarServico(id, dto);
        return ResponseEntity.ok(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable UUID id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
