package br.com.fiap.cp1.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroUsuarioDto(
        @NotBlank
        @Size(max = 15)
        @Schema(description = "Login do Usuario", type = "String", maximum = "50")
        String username,
        @NotBlank
        @Schema(description = "Senha do Usuario", type = "String", maximum = "50")
        String password,
        @NotBlank
        @Schema(description = "Email do Usuario", type = "String", maximum = "50")
        String email)
{
}
