package com.example.javarushspring2springweb.lessons1_7.config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
//@EnableAspectJAutoProxy
public class TxAspectPointcut {

    @Pointcut("@annotation(com.example.javarushspring2springweb.lessons1_7.config.aspect.Tx)")
    public void isMethodAnnotation(){
    }
}
