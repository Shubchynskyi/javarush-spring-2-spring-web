package com.example.javarushspring2springweb.lesson8_controllers.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration сообщает Spring, что этот класс является конфигурационным и содержит определения и зависимости bean'ов
//@Configuration
// @EnableWebSecurity включает Spring Security
//@EnableWebSecurity
// @RequiredArgsConstructor генерирует конструктор, который принимает все конечные и неинициализированные поля в классе
@RequiredArgsConstructor
public class SpringSecurity{

    // Объявление службы UserDetailsService, которую Spring будет использовать для работы с данными пользователя
    private final UserDetailsService userDetailsService;

    // Basic-аутентификация (конфигурация, при которой любой пользователь должен быть зарегистрирован)
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic().and()
//                .authorizeHttpRequests()
//                .anyRequest().authenticated().and()
//                .build()
//    }


    // Объявление конфигурации фильтра безопасности
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Отключение CSRF-защиты (Cross Site Request Forgery)
//                .csrf().disable() // deprecated
//                .csrf(csrf -> csrf.disable())
                .csrf(AbstractHttpConfigurer::disable)
                // Управление доступом к различным URL-адресам
                .authorizeHttpRequests((authorize) ->
                        authorize
                                // Доступ к URL "/register" запрещен для всех
                                .requestMatchers("/register").denyAll()
                                // Доступ к URL "/index" разрешен для всех
                                .requestMatchers("/index").permitAll()
                                // Доступ к URL "/users" разрешен только для пользователей с ролью "ADMIN"
                                .requestMatchers("/users").hasRole("ADMIN")
                                // Доступ к URL "/info" разрешен только для пользователей с ролью "USER"
                                .requestMatchers("/info").hasRole("USER")
                )
                // Настройка входа в систему
                .formLogin(
                        login ->
                                login
                                        // Страница входа в систему
                                        .loginPage("/login")
                                        // URL, на который будет выполнен редирект после успешного входа в систему
                                        .defaultSuccessUrl("/info")
                                        // Разрешить доступ ко всем к форме входа
                                        .permitAll()
                )
                // Настройка выхода из системы
                .logout(
                        logout ->
                                logout
                                        // Совпадение запроса на выход из системы
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        // Разрешить доступ ко всем к форме выхода
                                        .permitAll()
                );

        // Возврат сконфигурированного экземпляра http
        return http.build();
    }

    // Объявление bean'а для кодировщика паролей, который используется для шифрования и сравнения паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Конфигурация сервиса аутентификации
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // Установка службы для получения деталей пользователя
                .userDetailsService(userDetailsService)
                // Установка кодировщика паролей
                .passwordEncoder(passwordEncoder());
    }
}
