package com.soumen.poc.ignitevalidatorpoc;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class IgniteValidatorPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgniteValidatorPocApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(EmployeeRepository repository) {
        return args -> {
            Stream.of("Soumen", "Mita", "Puchu")
                    .map(name -> new Employee(null, name))
                    .forEach(repository::save);
        };
    }
}
