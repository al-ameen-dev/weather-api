package com.alameendev.weatherApi.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "WEATHER API END POINTS", version = "1.0",
        description = "Weather application REST End Points....",
        contact = @Contact(name = "github.com/al-ameen-dev Al Ameen"))
)
public class OpenApiConfig {

}
