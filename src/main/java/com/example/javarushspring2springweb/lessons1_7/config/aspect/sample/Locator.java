package com.example.javarushspring2springweb.config.aspect.sample;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Locator {

    // будет возвращать true, если класс отмечен аннотацией Service
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceAnnotation() {
    }

    // будет возвращать true, если класс в пакете com,
    // в любом подпакете (..)
    // имя класса заканчивается на Service
    @Pointcut("within(com..*Service)")
    public void isServiceByName() {
    }

    // класс в пакете com..repository
    // у него подпакет impl, в котором лежат какие-то классы
    // все методы этих классов подпадут под этот срез
    @Pointcut("within(com..repository.impl.*)")
    public void isRepoByPackage(){
    }

    // можно комбинировать срезы (PoincutЫ)
    @Pointcut("isServiceAnnotation() || isServiceByName()")
    public void isService(){
    }

    // сортирует методы в классах
    // найдет все публичные методы, которые неизвестно что возвращают
    // которые находятся в пакете com и подпакетах
    // которые заканчиваются на слово Service
    // найдет методы которые называются get (или начинаются на get - "get*" / заканчиваются - "*get"
    // get(*) - один параметр, get(*, *) - два параметра / get(*, ..) - один или больше параметров
    // get(*, Long, *) - первый неизвестный, второй Long, третий неизвестный, всего их точно три и т.д.
    @Pointcut("execution(public * com..*Service.get(*))")
    public void isMethodByName(){
    }

}
