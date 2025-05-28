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

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoPageController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoPageController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
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
        return "agendamento/form";
    }

    // Página para editar um agendamento existente
    @GetMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable UUID id, Model model) {
        AgendamentoDtoResponse agendamentoResponse =
            agendamentoService.buscarPorId(id);

        // Preencher um DTO de request para popular o form (se precisar)
        AgendamentoDtoRequest agendamentoForm = new AgendamentoDtoRequest(
            agendamentoResponse.servicoId(), // substitua pelos campos reais
            agendamentoResponse.userId(),
            agendamentoResponse.data(),
            agendamentoResponse.horaInicio()
        );
        model.addAttribute("agendamento", agendamentoForm);
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
