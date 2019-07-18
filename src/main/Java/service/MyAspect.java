package service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *切面类：增强代码与切入点结合
 */
public class MyAspect implements MethodInterceptor {

    //这些用于JDK和cglib代理
    public void before(){
        System.out.println("开启事务....");
    }

    public void after(){
        System.out.println("提交事务....");
    }

    //aop联盟
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //拦截方法

        System.out.println("开启事务");
        //放行
        Object retobj = methodInvocation.proceed();

        System.out.println("提交事务");
        return null;
    }
}
