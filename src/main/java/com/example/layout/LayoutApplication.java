package com.example.layout;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(description = "Layout Project"))
public class LayoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayoutApplication.class, args);
    }

}
