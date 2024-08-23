package br.com.fiap.cp1.dto.task;

import jakarta.validation.constraints.NotBlank;

public record CadastroTaskStatusDto(@NotBlank String tipoStatus) {
}
