package br.com.fiap.cp1.controller;


import br.com.fiap.cp1.dto.autenticacao.DadosLoginDto;
import br.com.fiap.cp1.dto.autenticacao.TokenJwtDto;
import br.com.fiap.cp1.model.user.Usuario;
import br.com.fiap.cp1.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJwtDto> post(@RequestBody @Valid DadosLoginDto dto){
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authenticate = authenticationManager.authenticate(autenticacaoToken);
        var token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(token));

        /*
        {
    "login": "Nome Do Usuario",
    "password": Senha Do Usuario (integer)
}
         */
    }

}
