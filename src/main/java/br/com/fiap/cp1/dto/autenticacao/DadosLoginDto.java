package br.com.fiap.cp1.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginDto(@NotBlank
                            String login,
                            @NotBlank
                            String password,
                            @NotBlank
                            String email) {
}
