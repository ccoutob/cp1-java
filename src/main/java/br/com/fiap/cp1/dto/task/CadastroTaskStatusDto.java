package br.com.fiap.cp1.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CadastroTaskStatusDto(@NotBlank
                                    @Schema(description = "Tipo da Task", type = "String", maximum = "50")
                                    String tipoStatus) {
}
