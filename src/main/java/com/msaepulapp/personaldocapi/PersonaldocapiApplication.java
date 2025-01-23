package com.msaepulapp.personaldocapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersonaldocapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaldocapiApplication.class, args);
	}

	@Bean
	public OpenAPI openApi(){
		return new OpenAPI()
				.info(new Info().title("DOC Personaldocapis"))
				.addSecurityItem(new SecurityRequirement().addList("X-API-TOKEN"))
				.components(new io.swagger.v3.oas.models.Components()
						.addSecuritySchemes("X-API-TOKEN", new SecurityScheme()
								.type(SecurityScheme.Type.APIKEY)
								.in(SecurityScheme.In.HEADER)
								.name("X-API-TOKEN")));
	}
}
