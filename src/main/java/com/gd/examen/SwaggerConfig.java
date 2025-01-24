package com.gd.examen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
* <h1>@Configuration()</h1>
* Clase de configuracion para swagger
* @param   Nada
* @return  Nada
* @author  Manuel Dirsio
* @version 1.0
* @since   31/05/2024
*/
@Configuration
public class SwaggerConfig {     
     @Bean
      public OpenAPI springOpenAPI() {
    	  // Configuración del esquema de seguridad
         SecurityScheme securityScheme = new SecurityScheme()
                 .type(SecurityScheme.Type.HTTP)
                 .scheme("bearer")
                 .bearerFormat("JWT");

         // Agregar el esquema a los componentes de OpenAPI
         Components components = new Components()
                 .addSecuritySchemes("BearerAuth", securityScheme);
      // Requerir la autenticación para todos los endpoints
         SecurityRequirement securityRequirement = new SecurityRequirement().addList("BearerAuth");

         
          return new OpenAPI()
        		  .components(components)
                  .addSecurityItem(securityRequirement)
                  .info(new Info().title("Microservicio")
                  .description("Microservicio de pago de servicio")
                  .version("v0.0.1")
                  .license(new License().name("Examen").url("https://www.xx.com.mx/")))
                  .externalDocs(new ExternalDocumentation()
                  .description("Sistemas")
                  .url("https://www.switch-comercial.com.mx/"));
      }
}