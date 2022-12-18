package by.smirnov.chequeprintproject;

import by.smirnov.chequeprintproject.config.OpenAPIConfig;
import by.smirnov.chequeprintproject.config.PersistenceProvidersConfiguration;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "by.smirnov.chequeprintproject")
@Import({PersistenceProvidersConfiguration.class, OpenAPIConfig.class})
public class ChequePrintProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChequePrintProjectApplication.class, args);
    }

}
