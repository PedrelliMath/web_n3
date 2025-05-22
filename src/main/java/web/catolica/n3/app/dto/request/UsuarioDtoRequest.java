package web.catolica.n3.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioDtoRequest(
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(
        min = 2,
        max = 100,
        message = "Nome deve ter entre 2 e 100 caracteres"
    )
    String name,

    @NotBlank(message = "E-mail não pode ser vazio")
    @Pattern(
        regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
        message = "E-mail inválido"
    )
    String email,

    @NotBlank(message = "CPF não pode ser vazio")
    @Pattern(
        regexp = "\\d{11}",
        message = "CPF deve conter 11 dígitos numéricos"
    )
    String cpf
) {}
