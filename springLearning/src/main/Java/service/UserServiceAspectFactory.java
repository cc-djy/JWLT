package service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceAspectFactory {

    public static userServiceInterface createUserService(){
        //创建目标对象target
        //声明切面类对象
        //把切面类2个方法 应用 目标类
        final userServiceInterface userService = new UserService();
        final MyAspect myAspect = new MyAspect();

        //创建JDK代理
        /**
         *  newProxyInstance(ClassLoader loader, 类加载器
         *                   Class<?>[] interfaces, 接口的方法会被拦截
         *                   InvocationHandler h)  处理
         */
        userServiceInterface userProxy = (userServiceInterface) Proxy.newProxyInstance(
                UserServiceAspectFactory.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        myAspect.before();
                        Object reObject = method.invoke(userService,args);
                        System.out.println(reObject);
                        myAspect.after();
                        return reObject;//业务方法的返回值
                    }
                }
        );
        return userProxy;
    }
}
