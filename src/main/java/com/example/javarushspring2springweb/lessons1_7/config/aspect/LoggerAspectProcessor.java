package com.example.javarushspring2springweb.lessons1_7.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspectProcessor {

    @Before("LoggerAspectPointcut.isAspectLoggerAnnotation()")
    public void beforeLoggerAnnotation(JoinPoint point) {
        log.warn("Before LoggerAspect annotated {} ", point.toShortString());
    }
    @After("LoggerAspectPointcut.isAspectLoggerAnnotation()")
    public void afterLoggerAnnotation(JoinPoint point) {
        log.warn("After LoggerAspect annotated {} ", point.toShortString());
    }

    long time;

    @Before("LoggerAspectPointcut.isMethodAnnotation()")
    public void beforeMethodLoggerAnnotation(JoinPoint point) {
        time = System.nanoTime();
    }

    @After("LoggerAspectPointcut.isMethodAnnotation()")
    public void afterMethodLoggerAnnotation(JoinPoint point) {
        System.err.printf("Method %s done for %s ms\n", point.toShortString(), (System.nanoTime() - time)/1000000);
    }

//    @After("LoggerAspectPointcut.isMethodByName()")
//    public void getMethodInLogger1Annotation(JoinPoint point) {
//        log.warn("After method annotated {} ", point.toShortString());
//    }
}
