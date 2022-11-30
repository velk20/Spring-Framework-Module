package bg.softuni.intro.models;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PreDestroy;

public class Dog implements Animal, BeanNameAware {
    @Override
    public void makeSound() {
        System.out.println("Bark Bark!");
    }

    @Override
    public void doSomething() {
        System.out.println("The dog is doing something");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("The Bean name is " + name);
    }

    @PreDestroy
    public void preDestroyDog() {
        System.out.println("Dog is destroying");

    }
}
