package br.com.fiap.cp1.dto.task;

import br.com.fiap.cp1.model.task.Task;

import java.time.LocalTime;

public record DetalhesTaskDto(Long id, String titulo, String descricao, String status, LocalTime dataConclusaoPrevista) {

    public DetalhesTaskDto(Task task){
        this(task.getId(), task.getTitulo(), task.getDescricao(), task.getStatus(), task.getDataConclusaoPrevista());
    }
}
