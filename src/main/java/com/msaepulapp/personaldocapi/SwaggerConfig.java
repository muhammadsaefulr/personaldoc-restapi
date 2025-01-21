package com.msaepulapp.personaldocapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch()
                .build();
    }

    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("DOC Personaldocapi"));
    }
}
