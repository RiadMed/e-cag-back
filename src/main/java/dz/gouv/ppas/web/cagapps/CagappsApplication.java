package dz.gouv.ppas.web.cagapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CagappsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CagappsApplication.class, args);
    }

}
