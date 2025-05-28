package web.catolica.n3.app.controller.pages;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.ServicoDtoRequest;
import web.catolica.n3.app.dto.response.ServicoDtoResponse;
import web.catolica.n3.app.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoPageController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoPageController(ServicoService servicoService) {
        this.servicoService = servicoService;
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
        return "servico/form"; // templates/servico/form.html
    }

    @PostMapping
    public String salvarServico(
        @ModelAttribute @Valid ServicoDtoRequest servico
    ) {
        servicoService.criarServico(servico);
        return "redirect:/servicos";
    }

    @GetMapping("/{id}/editar")
    public String editarServico(@PathVariable UUID id, Model model) {
        ServicoDtoResponse servico = servicoService.buscarPorId(id);
        model.addAttribute(
            "servico",
            new ServicoDtoRequest(
                servico.nome(),
                servico.empresaId(),
                servico.valor(),
                servico.duracao()
            )
        );
        model.addAttribute("id", id);
        return "servico/form";
    }

    @PostMapping("/{id}/editar")
    public String atualizarServico(
        @PathVariable UUID id,
        @ModelAttribute @Valid ServicoDtoRequest servico
    ) {
        servicoService.atualizarServico(id, servico);
        return "redirect:/servicos";
    }

    @PostMapping("/{id}/deletar")
    public String deletarServico(@PathVariable UUID id) {
        servicoService.deletarServico(id);
        return "redirect:/servicos";
    }
}
