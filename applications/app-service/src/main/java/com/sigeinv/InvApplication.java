package com.sigeinv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.sigeinv"})
public class InvApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvApplication.class, args);
    }
}
