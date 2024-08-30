package br.com.fiap.cp1.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public record CadastroTaskDto(@NotBlank
                              @Schema(description = "Titulo da Task", type = "String", maximum = "50")
                              String titulo,
                              @NotBlank
                              @Schema(description = "Descricao da Task", type = "String", maximum = "50")
                              String descricao,
                              @NotBlank
                              @Schema(description = "Data Conclusao da Task", type = "String", maximum = "50")
                              LocalTime dataConclusaoPrevista,
                              @NotBlank
                              @Schema(description = "Status da Task", type = "String", maximum = "50")
                              String status) {
}
