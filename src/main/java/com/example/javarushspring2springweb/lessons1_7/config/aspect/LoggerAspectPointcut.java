package com.example.javarushspring2springweb.lessons1_7.config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
//@EnableAspectJAutoProxy
public class LoggerAspectPointcut {

    @Pointcut("@within(com.example.javarushspring2springweb.lessons1_7.config.aspect.LoggerAspect)")
    public void isAspectLoggerAnnotation() {
    }

    @Pointcut("@annotation(com.example.javarushspring2springweb.lessons1_7.config.aspect.LoggerAspect)")
    public void isMethodAnnotation(){
    }

    @Pointcut("execution(public * com..*Service.create(String, String))")
    public void isMethodByName(){
    }
}
