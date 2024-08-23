package br.com.fiap.cp1.dto.usuario;

import br.com.fiap.cp1.model.user.Usuario;

public record DetalhesUsuarioDto(Long id, String username, String email) {

    public DetalhesUsuarioDto(Usuario usuario) {
        this(usuario.getId(),usuario.getUsername(), usuario.getEmail());
    }

}
