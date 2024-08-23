package br.com.fiap.cp1.dto.task;

import br.com.fiap.cp1.model.task.Task;

public record DetalhesTaskStatusDto(String tipoStatus) {

    public DetalhesTaskStatusDto(Task task){
        this(task.getTipoStatus());
    }
}
