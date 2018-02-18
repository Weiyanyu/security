package top.yeonon.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* top.yeonon.web.controller.UserController.*(..))")
    public Object handleUserControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("aspect start");

        Arrays.stream(point.getArgs()).forEach(arg -> System.out.println("arg is " + arg));

        Object object = point.proceed();
        System.out.println("aspect finished");
        return object;
    }
}
