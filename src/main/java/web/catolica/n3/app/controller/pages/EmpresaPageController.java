package web.catolica.n3.app.controller.pages;

import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.EmpresaDtoRequest;
import web.catolica.n3.app.dto.response.EmpresaDtoResponse;
import web.catolica.n3.app.service.EmpresaService;
import web.catolica.n3.app.service.UsuarioService;

@Controller
@RequestMapping("/empresas")
public class EmpresaPageController {

    private final EmpresaService empresaService;
    private final UsuarioService usuarioService;

    public EmpresaPageController(
        EmpresaService empresaService,
        UsuarioService usuarioService
    ) {
        this.empresaService = empresaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarEmpresas(Model model) {
        List<EmpresaDtoResponse> empresas = empresaService.listarEmpresas();
        model.addAttribute("empresas", empresas);
        return "empresa/lista";
    }

    @GetMapping("/novo")
    public String formularioNovaEmpresa(Model model) {
        EmpresaDtoRequest novaEmpresa = new EmpresaDtoRequest(
            "", // cnpj
            "", // nome
            null, // userId
            LocalTime.of(8, 0), // inicioExpediente padrão, ex 08:00
            LocalTime.of(17, 0) // fimExpediente padrão, ex 17:00
        );
        model.addAttribute("empresaDtoRequest", novaEmpresa);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("id", null);
        return "empresa/form";
    }

    @PostMapping("/salvar")
    public String salvarNovaEmpresa(
        @Valid @ModelAttribute(
            "empresaDtoRequest"
        ) EmpresaDtoRequest empresaDtoRequest,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", null);
            return "empresa/form";
        }
        empresaService.criarEmpresa(empresaDtoRequest);
        return "redirect:/empresas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditarEmpresa(@PathVariable UUID id, Model model) {
        EmpresaDtoResponse empresa = empresaService.buscarPorId(id);
        EmpresaDtoRequest empresaDtoRequest = new EmpresaDtoRequest(
            empresa.cnpj(),
            empresa.nome(),
            empresa.userId(),
            empresa.inicioExpediente(),
            empresa.fimExpediente()
        );
        model.addAttribute("empresaDtoRequest", empresaDtoRequest);
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("id", id);
        return "empresa/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarEmpresa(
        @PathVariable UUID id,
        @Valid @ModelAttribute(
            "empresaDtoRequest"
        ) EmpresaDtoRequest empresaDtoRequest,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "empresa/form";
        }
        empresaService.atualizarEmpresa(id, empresaDtoRequest);
        return "redirect:/empresas";
    }

    @PostMapping("/deletar/{id}")
    public String deletarEmpresa(@PathVariable UUID id) {
        empresaService.deletarEmpresa(id);
        return "redirect:/empresas";
    }
}
