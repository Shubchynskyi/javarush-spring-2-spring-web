package com.example.javarushspring2springweb.config.aspect.sample;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1) // описывает порядок применения (на один прокси может быть 20 штук интерцептеров)
public class LogAspect {
    @Before("com.example.javarushspring2springweb.config.aspect.sample.Locator.isService()")
    public void getMethodInAnnotatedService(JoinPoint point) {
        log.warn("Before Service annotated {} ", point.toShortString());
    }

    @After("com.example.javarushspring2springweb.config.aspect.sample.Locator.isServiceByName()")
    public void getMethodInServiceByName(JoinPoint point) {
        log.warn("After Service by name {} ", point.toShortString());
    }

    //    @Around("Locator.isService() && args(id) && target(o)")
    // Locator.isService() - Pointcut метод с условием
    // args(id) - можно вытащить id (к примеру)
    // target(o) - можно получить сам объект на котором сработал аспект
    // в общем можно передать какие-то штуки в перехватываемый метод
    public Object serviceMethod(
            ProceedingJoinPoint joinPoint, Object id, Object o
    ) {  // before - делаем что-то до вызова метода
        try {
            Object result = joinPoint; // вызываем сам метод
            //after returning = делаем что-то после вызова метода
            return result; // - возвращаем результат
        } catch (Throwable e) { // исключение пробрасывается не меняя его тип
            //afterThrowing - можно к примеру записать ошибку и пробросить дальше
            // НЕЛЬЗЯ изменять ошибку, логика должна быть неизменной как с аспектами так и без
            throw e;
        } finally {
            // after
            //тут логика по завершению метода, как бы он не завершился (с ошибкой или без)
        }

    }
}
