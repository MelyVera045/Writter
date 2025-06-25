package Nexus_company.Writter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Api de biblioteca Writter")
            .version("1.1")
            .description("Con esta API se puede administrar los libros de la aplicación Writter, incluyendo la creación, actualización y eliminación de libros, asi como dejar sus comentarios.")
        );
    }

}

