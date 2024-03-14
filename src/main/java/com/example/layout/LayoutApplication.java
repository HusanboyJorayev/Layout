package com.example.layout;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Tag(name = "Layout Api")
public class LayoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayoutApplication.class, args);
    }
}
