package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.task.CadastroTaskStatusDto;
import br.com.fiap.cp1.dto.task.DetalhesTaskStatusDto;
import br.com.fiap.cp1.dto.usuario.CadastroUsuarioDto;
import br.com.fiap.cp1.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.cp1.model.task.Task;
import br.com.fiap.cp1.model.user.Usuario;
import br.com.fiap.cp1.repository.TaskRepository;
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
@RequestMapping("/public")
public class TaskStatusController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("registrar")
    @Transactional
    public ResponseEntity<DetalhesTaskStatusDto> post(@RequestBody @Valid CadastroTaskStatusDto dto,
                                                      UriComponentsBuilder builder) {
        var task = new Task(dto.tipoStatus());
        taskRepository.save(task);
        var url = builder.path("status-task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesTaskStatusDto(task));
    }

    @GetMapping("status")
    public ResponseEntity<List<DetalhesTaskStatusDto>> listar(Pageable pageable){
        var lista = taskRepository.findAll(pageable)
                .stream().map(DetalhesTaskStatusDto::new).toList();
        return ResponseEntity.ok(lista);
    }
}
