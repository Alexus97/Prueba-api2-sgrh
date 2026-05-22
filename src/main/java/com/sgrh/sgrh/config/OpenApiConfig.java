package com.sgrh.sgrh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("SGRSH - Sistema para la Gestión de Reservas y Servicios Hoteleros")
                                                .version("1.0.0")
                                                .description("API REST para la gestión integral de reservas, habitaciones y servicios hoteleros")
                                                .contact(new Contact()
                                                                .name("Equipo de Desarrollo SGRSH")
                                                                .url("https://sgrsh.local"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
        }
}
