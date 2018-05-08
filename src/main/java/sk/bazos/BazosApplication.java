package sk.bazos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
public class BazosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BazosApplication.class, args);
    }


}
