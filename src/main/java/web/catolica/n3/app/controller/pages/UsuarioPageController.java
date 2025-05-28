package web.catolica.n3.app.controller.pages;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.UsuarioDtoRequest;
import web.catolica.n3.app.dto.response.UsuarioDtoResponse;
import web.catolica.n3.app.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioPageController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioPageController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<UsuarioDtoResponse> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario/lista"; // templates/usuario/lista.html
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDtoRequest("", "", ""));
        return "usuario/form";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute UsuarioDtoRequest usuario) {
        usuarioService.criarUsuario(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable UUID id, Model model) {
        UsuarioDtoResponse usuario = usuarioService.buscarPorId(id);

        UsuarioDtoRequest usuarioForm = new UsuarioDtoRequest(
            usuario.name(),
            usuario.email(),
            usuario.cpf()
        );

        model.addAttribute("usuario", usuarioForm);
        model.addAttribute("id", id);

        return "usuario/form"; // templates/usuario/form.html
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarUsuario(
        @PathVariable UUID id,
        @ModelAttribute UsuarioDtoRequest usuario
    ) {
        usuarioService.atualizarUsuario(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return "redirect:/usuarios";
    }
}
