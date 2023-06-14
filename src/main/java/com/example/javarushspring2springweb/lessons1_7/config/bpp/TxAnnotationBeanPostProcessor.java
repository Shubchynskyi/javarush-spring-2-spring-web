package com.example.javarushspring2springweb.config.bpp;

import com.example.javarushspring2springweb.config.JavaConfigApplication;
import com.example.javarushspring2springweb.config.SessionCreator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TxAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Tx.class) || methodIsAnnotationPresent(aClass)) {
            map.put(beanName, aClass);
        }
        return bean;
    }

    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return Arrays.stream(aClass.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Tx.class));
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = map.get(beanName);
        if (aClass != null) {
            bean = proxy(bean, aClass);
            System.out.printf("bean in Tx proxy! (%s)\n", beanName);
        }
        return bean;
    }

    private Object proxy(Object beanOrProxy, Class<?> beanRealClass) {
        MethodInterceptor handler = (obj, method, args, proxy) -> {
            Object result;
            if (beanRealClass.isAnnotationPresent(Tx.class)
                || method.isAnnotationPresent(Tx.class)
            ) {
                SessionCreator sessionCreator = JavaConfigApplication.getBean(SessionCreator.class);
                Session session = sessionCreator.getSession();
                Transaction transaction = session.beginTransaction();
                try(session) {
                    result = proxy.invoke(beanOrProxy, args);
                    transaction.commit();
                } catch (Exception e){
                    transaction.rollback();
                    throw e;
                }

            } else {
                result = proxy.invoke(beanOrProxy, args);
            }
            return result;
        };
        return Enhancer.create(beanRealClass, handler);
    }
}
