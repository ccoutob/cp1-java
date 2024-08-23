package br.com.fiap.cp1.dto.task;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public record CadastroTaskDto(@NotBlank String titulo, @NotBlank String descricao,
                              @NotBlank LocalTime dataConclusaoPrevista, @NotBlank String status, @NotBlank String tipoStatus) {
}
