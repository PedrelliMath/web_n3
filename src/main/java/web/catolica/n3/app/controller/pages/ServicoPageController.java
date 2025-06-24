package web.catolica.n3.app.controller.pages;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.ServicoDtoRequest;
import web.catolica.n3.app.dto.response.ServicoDtoResponse;
import web.catolica.n3.app.service.EmpresaService;
import web.catolica.n3.app.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoPageController {

    private final ServicoService servicoService;
    private final EmpresaService empresaService;

    @Autowired
    public ServicoPageController(
        ServicoService servicoService,
        EmpresaService empresaService
    ) {
        this.servicoService = servicoService;
        this.empresaService = empresaService;
    }

    @GetMapping
    public String listarServicos(Model model) {
        List<ServicoDtoResponse> servicos = servicoService.listarServicos();
        model.addAttribute("servicos", servicos);
        return "servico/lista"; // templates/servico/lista.html
    }

    @GetMapping("/novo")
    public String novoServico(Model model) {
        model.addAttribute("servico", new ServicoDtoRequest("", null, null, 0));
        model.addAttribute("empresas", empresaService.listarEmpresas());
        return "servico/form"; // templates/servico/form.html
    }

    @PostMapping("/salvar")
    public String salvarNovoServico(
        @Valid @ModelAttribute("servico") ServicoDtoRequest servicoDtoRequest,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("empresas", empresaService.listarEmpresas());
            model.addAttribute("id", null);
            return "servico/form";
        }
        servicoService.criarServico(servicoDtoRequest);
        return "redirect:/servicos";
    }

    @GetMapping("/editar/{id}")
    public String editarServico(@PathVariable UUID id, Model model) {
        ServicoDtoResponse servico = servicoService.buscarPorId(id);
        model.addAttribute(
            "servico",
            new ServicoDtoRequest(
                servico.nome(),
                servico.empresa().id(),
                servico.valor(),
                servico.duracao()
            )
        );
        model.addAttribute("empresas", empresaService.listarEmpresas());
        model.addAttribute("id", id);
        return "servico/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarServico(
        @Valid @ModelAttribute("servico") ServicoDtoRequest servicoDtoRequest,
        BindingResult bindingResult,
        @PathVariable UUID id,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "servico/form";
        }
        servicoService.atualizarServico(id, servicoDtoRequest);
        return "redirect:/servicos";
    }

    @PostMapping("/deletar/{id}")
    public String deletarServico(@PathVariable UUID id) {
        servicoService.deletarServico(id);
        return "redirect:/servicos";
    }
}
