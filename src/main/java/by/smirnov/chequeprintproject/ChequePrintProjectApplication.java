package by.smirnov.chequeprintproject;

import by.smirnov.chequeprintproject.config.PersistenceProvidersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "by.smirnov.chequeprintproject")
@Import({PersistenceProvidersConfiguration.class})
public class ChequePrintProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChequePrintProjectApplication.class, args);
    }

}
