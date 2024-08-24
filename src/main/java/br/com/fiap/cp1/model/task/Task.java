package br.com.fiap.cp1.model.task;

import br.com.fiap.cp1.dto.task.CadastroTaskDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="TB_CP_TASK")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Column(name="id_task")
    private Long id;

    @Column(name = "nm_task")
    private String titulo;

    @Column(name="ds_task")
    private String descricao;

    @Column(name = "dt_conclusao")
    private LocalTime dataConclusaoPrevista; //"data": "18:00:00"

    @Column(name = "st_task")
    private String status;

    @Column(name = "tipo_status", unique = true)
    private String tipoStatus;

    public Task(String tipoStatus){
        this.tipoStatus = tipoStatus;
    }

    public Task(String titulo, String descricao, LocalTime dataConclusaoPrevista, String status){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataConclusaoPrevista = dataConclusaoPrevista;
        this.status = status;
    }

    public void atualizarDados(CadastroTaskDto atualizacao){
        if(atualizacao.titulo() != null)
            titulo = atualizacao.titulo();
        if(atualizacao.descricao() != null)
            descricao = atualizacao.descricao();
        if(atualizacao.dataConclusaoPrevista() != null)
            dataConclusaoPrevista = atualizacao.dataConclusaoPrevista();
        if(atualizacao.status() != null)
            status = atualizacao.status();
    }
}
