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
import web.catolica.n3.app.dto.request.EmpresaDtoRequest;
import web.catolica.n3.app.dto.response.EmpresaDtoResponse;
import web.catolica.n3.app.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaDtoResponse> criarEmpresa(
        @RequestBody EmpresaDtoRequest empresa
    ) {
        EmpresaDtoResponse novaEmpresa = empresaService.criarEmpresa(empresa);
        return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDtoResponse>> listarEmpresas() {
        List<EmpresaDtoResponse> empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDtoResponse> buscarPorId(
        @PathVariable UUID id
    ) {
        EmpresaDtoResponse empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaDtoResponse> buscarPorCnpj(
        @PathVariable String cnpj
    ) {
        EmpresaDtoResponse empresa = empresaService.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDtoResponse> atualizarEmpresa(
        @PathVariable UUID id,
        @RequestBody EmpresaDtoRequest empresaAtualizada
    ) {
        EmpresaDtoResponse empresa = empresaService.atualizarEmpresa(
            id,
            empresaAtualizada
        );
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable UUID id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}
