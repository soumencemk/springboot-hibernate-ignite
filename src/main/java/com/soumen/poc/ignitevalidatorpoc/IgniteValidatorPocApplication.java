package com.soumen.poc.ignitevalidatorpoc;

import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class IgniteValidatorPocApplication {

    public static void main(String[] args) {
        //Ignition.start(IgniteConfig.igniteServerConfiguration());
        SpringApplication.run(IgniteValidatorPocApplication.class, args);
    }

    @Bean
    public IgniteSpringBean igniteSpringBean(IgniteConfiguration igniteClientConfiguration) {
        IgniteSpringBean igniteSpringBean = new IgniteSpringBean();
        igniteSpringBean.setConfiguration(igniteClientConfiguration);
        return igniteSpringBean;
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
