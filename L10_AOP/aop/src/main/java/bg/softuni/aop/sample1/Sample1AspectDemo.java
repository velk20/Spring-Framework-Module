package bg.softuni.aop.sample1;

import bg.softuni.aop.IncredibleMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
//in application.yml /.properties
@ConditionalOnProperty(
        value = "sample1.enabled",
        havingValue = "true"
)
public class Sample1AspectDemo implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample1AspectDemo.class);

    private final IncredibleMachine incredibleMachine;

    public Sample1AspectDemo(IncredibleMachine incredibleMachine) {
        this.incredibleMachine = incredibleMachine;
    }

    @Override
    public void run(String... args) throws Exception {
        incredibleMachine.saySomething();
        incredibleMachine.concat("sex", " na plaja");
        try {

            incredibleMachine.boom();
        } catch (Exception e) {
            LOGGER.info("EXCEPTIONNNNNNN");
        }
        incredibleMachine.echo("SEKSSSSSSS");
    }
}
