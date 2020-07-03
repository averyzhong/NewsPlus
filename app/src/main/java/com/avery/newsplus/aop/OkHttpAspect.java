package com.avery.newsplus.aop;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author AveryZhong
 */

@Aspect
public class OkHttpAspect  {


    @Pointcut("execution(* com.avery.newsplus.api.intercept.LoggingInterceptor.interce*(..))")
    public void methodToIntercept() {
    }

    @Around("methodToIntercept()")
    public Object onMethodIntercepted(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        System.out.println("===================result:> " + result);
        return result;
    }
}
