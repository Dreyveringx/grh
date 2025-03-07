package com.datacenter.GRH.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Gestión de Módulos")
                .version("1.0")
                .description("Documentación de la API para gestionar módulos en el sistema.")
                .contact(new Contact()
                    .name("Equipo de Desarrollo")
                    .email("soporte@empresa.com")
                    .url("https://empresa.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .externalDocs(new ExternalDocumentation()
                .description("Documentación Adicional")
                .url("https://empresa.com/docs"));
    }
}
