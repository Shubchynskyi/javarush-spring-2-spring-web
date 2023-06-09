package com.example.javarushspring2springweb.lessons1_7.config;

import com.example.javarushspring2springweb.lessons1_7.controller.comands.CommandResolver;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@UtilityClass
public class JavaConfigApplication {
    private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static void init() {
        CommandResolver commandResolver = applicationContext.getBean(CommandResolver.class);
        commandResolver.fillContextMap(applicationContext);
        String[] names = applicationContext.getBeanDefinitionNames();
        System.out.println("============= context =============");
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("============= context =============");
    }


}
