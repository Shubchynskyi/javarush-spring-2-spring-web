package com.example.javarushspring2springweb.lessons1_7.config.aspect;

import com.example.javarushspring2springweb.lessons1_7.config.SessionCreator;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
@Aspect
@AllArgsConstructor
public class TxAspectProcessor {

    private final SessionCreator sessionCreator;

    @Around("TxAspectPointcut.isMethodAnnotation()")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Transaction transaction = sessionCreator.getSession().getTransaction();
        try {
            transaction.begin();
            Object result = joinPoint.proceed();
            transaction.commit();
            return result;
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        }
    }
}
