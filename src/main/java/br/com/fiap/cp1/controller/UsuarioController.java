package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.usuario.CadastroUsuarioDto;
import br.com.fiap.cp1.dto.usuario.DetalhesUsuarioDto;
import br.com.fiap.cp1.model.user.Usuario;
import br.com.fiap.cp1.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> post(@RequestBody @Valid CadastroUsuarioDto dto,
                                                   UriComponentsBuilder builder){
        var usuario = new Usuario(dto.username(), passwordEncoder.encode(dto.password()), dto.email());
        usuarioRepository.save(usuario);
        var url = builder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesUsuarioDto(usuario));

        /*
        {
    "username": "generico",
    "password": 123
}
         */
    }

}
