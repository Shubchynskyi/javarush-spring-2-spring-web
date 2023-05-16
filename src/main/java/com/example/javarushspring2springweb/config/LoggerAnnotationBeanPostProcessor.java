package com.example.javarushspring2springweb.config;

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
public class LoggerAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Logger.class) || methodIsAnnotationPresent(aClass)) {
            map.put(beanName, aClass);
        }
        return bean;
    }

    private boolean methodIsAnnotationPresent(Class<?> aClass) {
        return Arrays.stream(aClass.getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Logger.class));
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        Class<?> aClass = map.get(beanName);
        if (aClass != null) {
            bean = proxy(bean, aClass);
            System.out.printf("bean in logger proxy! (%s)\n", beanName);
        }
        return bean;
    }

    private Object proxy(Object beanOrProxy, Class<?> beanRealClass) {
        MethodInterceptor handler = (obj, method, args, proxy) -> {
            Object result;
            if (beanRealClass.isAnnotationPresent(Logger.class)
                || method.isAnnotationPresent(Logger.class)
            ) {
                System.out.printf("Proxy logger started in method %s\n", method.getName());
                long time = System.nanoTime();
                result = proxy.invoke(beanOrProxy, args);
                time = System.nanoTime() - time;
                System.out.printf("Proxy logger finished in method %s with %d ms\n", method.getName(), time / 1000000);
            } else {
                result = proxy.invoke(beanOrProxy, args);
            }
            return result;
        };
        return Enhancer.create(beanRealClass, handler);
    }


}
