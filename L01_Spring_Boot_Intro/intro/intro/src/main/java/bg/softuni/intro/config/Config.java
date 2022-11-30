package bg.softuni.intro.config;

import bg.softuni.intro.models.Animal;
import bg.softuni.intro.models.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Animal createDog () {
        return new Dog();
    }
}
