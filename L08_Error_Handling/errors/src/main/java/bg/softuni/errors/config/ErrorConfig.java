package bg.softuni.errors.config;

import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class ErrorConfig {
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties excMapping = new Properties();
        //map Exception to View
        excMapping.put(NullPointerException.class.getSimpleName(), "npe");
        //map Exception to View
        excMapping.put(ProductNotFoundException.class.getSimpleName(), "product-not-found");

        resolver.setExceptionMappings(excMapping);

        return resolver;
    }
}
