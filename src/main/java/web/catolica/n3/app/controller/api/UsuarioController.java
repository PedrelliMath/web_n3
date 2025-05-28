package web.catolica.n3.app.controller.api;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.UsuarioDtoRequest;
import web.catolica.n3.app.dto.response.UsuarioDtoResponse;
import web.catolica.n3.app.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> criarUsuario(
        @Valid @RequestBody UsuarioDtoRequest usuarioDTO
    ) {
        UsuarioDtoResponse novoUsuario = usuarioService.criarUsuario(
            usuarioDTO
        );
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResponse>> listarUsuarios() {
        List<UsuarioDtoResponse> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> buscarPorId(
        @PathVariable UUID id
    ) {
        UsuarioDtoResponse usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDtoResponse> buscarPorEmail(
        @PathVariable String email
    ) {
        UsuarioDtoResponse usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoResponse> atualizarUsuario(
        @PathVariable UUID id,
        @Valid @RequestBody UsuarioDtoRequest usuarioDTO
    ) {
        UsuarioDtoResponse usuario = usuarioService.atualizarUsuario(
            id,
            usuarioDTO
        );
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
