package com.rasulov;

import com.rasulov.config.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = {PersistenceConfiguration.class})
public class PrototypeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }
}
