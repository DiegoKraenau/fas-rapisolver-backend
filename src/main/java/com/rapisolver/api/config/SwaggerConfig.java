package com.rapisolver.api.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean(name = "RapiSolverDoc")
    public OpenAPI rapiSolverOpenApi(){
        // Available at
        // http://localhost:8080/swagger-ui.html
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("RapiSolver API").description(
                        "Open API Documentation"));
    }
}

