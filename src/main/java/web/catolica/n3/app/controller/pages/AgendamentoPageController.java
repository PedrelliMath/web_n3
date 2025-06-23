package web.catolica.n3.app.controller.pages;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.catolica.n3.app.dto.request.AgendamentoDtoRequest;
import web.catolica.n3.app.dto.response.AgendamentoDtoResponse;
import web.catolica.n3.app.service.AgendamentoService;
import web.catolica.n3.app.service.ServicoService;
import web.catolica.n3.app.service.UsuarioService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoPageController {

    private final AgendamentoService agendamentoService;
    private final ServicoService servicoService;
    private final UsuarioService usuarioService;

    @Autowired
    public AgendamentoPageController(
        AgendamentoService agendamentoService,
        ServicoService servicoService,
        UsuarioService usuarioService
    ) {
        this.agendamentoService = agendamentoService;
        this.servicoService = servicoService;
        this.usuarioService = usuarioService;
    }

    // Página para listar agendamentos
    @GetMapping
    public String listarAgendamentos(Model model) {
        List<AgendamentoDtoResponse> agendamentos =
            agendamentoService.listarAgendamentos();
        model.addAttribute("agendamentos", agendamentos);
        return "agendamento/lista"; // templates/agendamento/lista.html
    }

    // Página para mostrar formulário para novo agendamento
    @GetMapping("/novo")
    public String novoAgendamento(Model model) {
        AgendamentoDtoRequest agendamentoVazio = new AgendamentoDtoRequest(
            null,
            null,
            null,
            null
        );
        model.addAttribute("agendamento", agendamentoVazio);
        model.addAttribute("servicos", servicoService.listarServicos());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "agendamento/form";
    }

    // Página para editar um agendamento existente
    @GetMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable UUID id, Model model) {
        AgendamentoDtoResponse agendamentoResponse =
            agendamentoService.buscarPorId(id);

        // Preencher um DTO de request para popular o form (se precisar)
        AgendamentoDtoRequest agendamentoForm = new AgendamentoDtoRequest(
            agendamentoResponse.servico().id(),
            agendamentoResponse.user().id(),
            agendamentoResponse.data(),
            agendamentoResponse.horaInicio()
        );

        model.addAttribute("agendamento", agendamentoForm);
        model.addAttribute("servicos", servicoService.listarServicos());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("id", id);

        return "agendamento/form"; // templates/agendamento/form.html
    }

    // Processa o envio do formulário para criar agendamento
    @PostMapping("/salvar")
    public String salvarAgendamento(
        @ModelAttribute("agendamento") AgendamentoDtoRequest agendamento
    ) {
        agendamentoService.criarAgendamento(agendamento);
        return "redirect:/agendamentos";
    }

    // Processa o envio do formulário para atualizar agendamento
    @PostMapping("/atualizar/{id}")
    public String atualizarAgendamento(
        @PathVariable UUID id,
        @ModelAttribute("agendamento") AgendamentoDtoRequest agendamento
    ) {
        agendamentoService.atualizarAgendamento(id, agendamento);
        return "redirect:/agendamentos";
    }

    // Excluir agendamento e redirecionar para lista
    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(@PathVariable UUID id) {
        agendamentoService.deletarAgendamento(id);
        return "redirect:/agendamentos";
    }
}
