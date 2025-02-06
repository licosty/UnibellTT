package com.gmail.chigantseva.unibelltesttask.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "Documentation Api for clients and contacts",
                title = "UnibellTT Api",
                version = "1.0-SNAPSHOT"),
        servers = {@Server(
                description = "Local ENV",
                url = "http://localhost:8081"
        )}
)
public class SwaggerConfig {
}
