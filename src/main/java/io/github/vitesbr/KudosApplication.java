package io.github.vitesbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KudosApplication {

    public static void main(String[] args) {
        SpringApplication.run(KudosApplication.class, args);
    }
}
