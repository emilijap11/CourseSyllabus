package com.example.kartonpredmeta.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(
        info = @Info(
                title = "Karton Predmeta API",
                version = "1.0.0",
                description = "REST API za evidenciju nastavnika i predmeta.",
                contact = @Contact(name = "Karton Predmeta Tim", email = "team@example.com"),
                license = @License(name = "MIT")
        ),


        servers = {
                @Server(url = "http://localhost:8081", description = "Local server")
        }
)


public class OpenApiConfig {
}
