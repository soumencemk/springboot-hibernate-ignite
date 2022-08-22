package com.soumen.poc.ignitevalidatorpoc;

import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableCaching
public class IgniteValidatorPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IgniteValidatorPocApplication.class, args);
    }
    @Bean
    ApplicationRunner applicationRunner(EmployeeRepository repository) {
        return args -> {
            Stream.of("Simon","David","Ian","Bob","Vasilis","Adam","Eliza")
                    .map(name -> new Employee(null, name))
                    .forEach(repository::save);
        };
    }
}