package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class myAspectj {

    @Pointcut("execution(* service.*.*(..))")
    public void getPointCut(){};

    @Before("execution(* service.*.*(..))")
    public void aspectBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+".......前置通知");
    }

    @AfterReturning(pointcut = "getPointCut()",returning = "retValue")
    public void aspectAfter(JoinPoint joinPoint,Object retValue){
        System.out.println(joinPoint.getSignature().getName()+".......后置通知");
    }
}
