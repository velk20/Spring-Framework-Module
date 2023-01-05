package bg.softuni.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IncredibleMachine {

    private static final Logger LOGGER = LoggerFactory.getLogger(IncredibleMachine.class);

    public void saySomething() {
        LOGGER.info("I am saying!");

    }

    public void boom() {
        throw new NullPointerException("Ups, i did not work");
    }

    public void echo(String echo) {
        LOGGER.info("I am echo this {}", echo);
    }

    public String concat(String a, String b) {
        return a + b;
    }
}
