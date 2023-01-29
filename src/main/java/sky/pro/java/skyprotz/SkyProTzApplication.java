package sky.pro.java.skyprotz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SkyProTzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyProTzApplication.class, args);
    }

}
