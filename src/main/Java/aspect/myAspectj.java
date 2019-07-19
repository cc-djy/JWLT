package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class myAspectj {

    @Before("execution(* service.*.*(..))")
    public void aspectBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+".......前置通知");
    }
}
