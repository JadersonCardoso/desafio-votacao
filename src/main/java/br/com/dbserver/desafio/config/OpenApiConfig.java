package br.com.dbserver.desafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                .title("API para cadastro de paustas.")
                    .version("v1")
                    .description("API para cadastro de paustas.")
                .termsOfService("http://localhost:8080")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://ocalhost:8080")
                    )
                );
    }
}