package edu.dosw.taller.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Collaborative Task Manager API")
                        .version("1.0")
                        .description("API para un un Gestor de Tareas Colaborativo")
                        .contact(new Contact()
                                .name("Equipo Romanos")
                                .email("taller@dosw.edu")));
    }
}
