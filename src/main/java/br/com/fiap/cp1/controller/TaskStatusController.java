package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.task.CadastroTaskDto;
import br.com.fiap.cp1.dto.task.CadastroTaskStatusDto;
import br.com.fiap.cp1.dto.task.DetalhesTaskDto;
import br.com.fiap.cp1.dto.task.DetalhesTaskStatusDto;
import br.com.fiap.cp1.dto.usuario.CadastroUsuarioDto;
import br.com.fiap.cp1.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.cp1.model.task.Task;
import br.com.fiap.cp1.model.user.Usuario;
import br.com.fiap.cp1.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task", description = "Realiza ações de CRUD da Task")
public class TaskStatusController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Registrar os status das tarefas (para o usuario ter acesso aos tipos dos status das tarefas)
    @PostMapping("/public/registrar")
    @Transactional
    @Operation(summary = "Cadastra o status de uma Task", description = "Cadastra o status da Task em nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status da task cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskStatusDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Status da task não autenticada",content = @Content)
    })
    public ResponseEntity<DetalhesTaskStatusDto> post(@RequestBody @Valid CadastroTaskStatusDto dto,
                                                      UriComponentsBuilder builder) {
        var task = new Task(dto.tipoStatus());
        taskRepository.save(task);
        var url = builder.path("/status-task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTaskStatusDto(task));
    }

    //Buscar os status das tarefas "disponivel", "em andamento" etc
    @GetMapping("/status")
    @Operation(summary = "Busca os status de uma Task", description = "Busca os status da Task em nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status da task encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskStatusDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Status da task não autenticada",content = @Content)
    })
    public ResponseEntity<List<DetalhesTaskStatusDto>> listarTaskStatus(Pageable pageable){
        var lista = taskRepository.findAll(pageable)
                .stream().map(DetalhesTaskStatusDto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra uma Task", description = "Cadastra a Task em nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Task inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "Task não autenticada",content = @Content)
    })
    public ResponseEntity<DetalhesTaskDto> cadastrar(@RequestBody CadastroTaskDto dto,
                                                     UriComponentsBuilder uri){
        var task = new Task(dto.titulo(), dto.descricao(), dto.dataConclusaoPrevista(), dto.status());
        taskRepository.save(task);
        var url = uri.path("/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTaskDto(task));
    }

    @GetMapping
    @Operation(summary = "Busca uma Task", description = "Busca as Task de nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tasks não encontradas", content = @Content),
            @ApiResponse(responseCode = "403", description = "Task não autenticada",content = @Content)
    })
    public ResponseEntity<List<DetalhesTaskDto>> listarTasks(Pageable pageable){
        var lista = taskRepository.findAll(pageable)
                .stream().map(DetalhesTaskDto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca uma Task por id", description = "Busca as Task de nossa aplicação pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Id da task não encontrada", content = @Content),
            @ApiResponse(responseCode = "403", description = "Task não autenticada",content = @Content)
    })
    public ResponseEntity<DetalhesTaskDto> listarTask(@PathVariable("id") Long id){
        var task = taskRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesTaskDto(task));
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza uma Task", description = "Atualiza as Task de nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Id da task não encontrada", content = @Content),
            @ApiResponse(responseCode = "403", description = "Task não autenticada",content = @Content)
    })
    public ResponseEntity<DetalhesTaskDto> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody CadastroTaskDto dto){
        var task = taskRepository.getReferenceById(id);
        task.atualizarDados(dto);
        return ResponseEntity.ok(new DetalhesTaskDto(task));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Deleta uma Task", description = "Deleta uma Task em nossa aplicação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task deletada com sucesso",
                    content = @Content(schema = @Schema(implementation = DetalhesTaskStatusDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Id da task não encontrada", content = @Content),
            @ApiResponse(responseCode = "403", description = "Task não autenticada",content = @Content)
    })
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
