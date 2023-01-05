package bg.softuni.aop.sample1;

import bg.softuni.aop.IncredibleMachine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Sample1Aspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample1Aspect.class);

    @Pointcut("execution(* bg.softuni.aop.IncredibleMachine.*(..))")
    void onAllIncredibleMachineMethods() {

    }

    @Pointcut("execution(* bg.softuni.aop.IncredibleMachine.echo(..))")
    void onEchoCalled() {

    }

    @Before("onAllIncredibleMachineMethods()")
    public void beforeEachMethod(JoinPoint joinPoint) {
        LOGGER.info("Before call method {} with args {}",joinPoint.getSignature(), Arrays.stream(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* bg.softuni.aop.IncredibleMachine.boom())",throwing = "throwable")
    public void afterReturn(JoinPoint joinPoint,Throwable throwable) {
        LOGGER.error("UPSSSSSSSSs method {} failed {}",joinPoint.getSignature(),throwable);
    }
}
