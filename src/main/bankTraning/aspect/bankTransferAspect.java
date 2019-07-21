package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class bankTransferAspect {
    @Pointcut("execution(* Service.*.*(..))")
    public void myPointCut(){};

    @Around("myPointCut()")
    public void accountAroundAspect(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("pjp.getSignature().getName()......."+pjp.getSignature().getName());
        Object retObj = pjp.proceed();
        System.out.println("retobj.............."+retObj);
    }

}
