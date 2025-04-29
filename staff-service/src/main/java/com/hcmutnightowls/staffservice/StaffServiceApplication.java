package com.hcmutnightowls.staffservice;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class StaffServiceApplication {

    public static void main(String[] args) {
        // System.out.println("Current directory: " + Paths.get(".").toAbsolutePath().normalize());
        Dotenv dotenv = Dotenv.configure()
                .load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(StaffServiceApplication.class, args);
    }

}
