package br.com.fiap.cp1.swager;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(contact = @Contact(name = "Cauã Couto", email = "rm97755@fiap.com.br"),
        title = "FIAP Autenticator",
        description = "Sistema de autorização para usuarios",
        version = "1.0"),
        servers = @Server(url = "http://localhost:8080"),
        security = @SecurityRequirement(name = "auteticadorJWT")
)

public class OpenAiConfig {
}
