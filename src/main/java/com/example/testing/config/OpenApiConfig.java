package com.example.testing.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nabin Rai",
                        email = "nabin.rai@amniltech.com",
                        url = "https://index.com"
                ),
                description = "OpenApi documentation for ......",
                title = "OpenApi specification - Nabin",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "some link"
                ),
                termsOfService = "Terms and Service"
        )
//        ,
//        servers = {
//                @Server(
//                        description = "LOCAL ENV",
//                        url = "https://localhost:8080"
//                ),
//                @Server(
//                        description = "PRODUCTION ENV",
//                        url = "https://api.example.com"
//                )
//        }
)
public class OpenApiConfig {

}
