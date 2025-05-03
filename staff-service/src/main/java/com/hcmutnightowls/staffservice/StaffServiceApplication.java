package com.hcmutnightowls.staffservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.hcmutnightowls.staffservice.service")
public class StaffServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffServiceApplication.class, args);
    }

}
