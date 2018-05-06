package top.sillyfan.security.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* top.sillyfan.security.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {

        System.out.println("time aspect start");

        Object o = point.proceed();

        System.out.println(point.getArgs());

        System.out.println("time aspect end");

        return o;
    }
}
