package com.swp.birthdaybooking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Bùi Mạnh Thành",
                        email = "manhthanh147@gmail.com"
                ),
                title = "Birthday Booking API",
                version = "v1",
                description = "Birthday Booking API"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local server"
                )
        }
)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT Token auth",
        scheme = "bearer",
        type = SecuritySchemeType.APIKEY,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
