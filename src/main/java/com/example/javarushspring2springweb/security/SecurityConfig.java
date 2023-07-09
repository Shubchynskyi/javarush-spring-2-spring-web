package com.example.javarushspring2springweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.builder()
                .username("user")
                //encoder().encode чтобы считался пароль нормально
                .password(encoder().encode("7777")) // если без него, то пароль должен быть в формате энкодера(BCrypt)
                .roles("USER")
                .authorities("read")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder().encode("5555"))
                .roles("ADMIN")
                .authorities("write","read")
                .build();


        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // отключает защиту от атак, позволяет выполнять post методы
                //иначе требуется csrf токен
                .authorizeHttpRequests(s -> s

//                                .requestMatchers(HttpMethod.GET).hasAuthority("read")
//                                .requestMatchers(HttpMethod.POST).hasAuthority("write")
//                                .anyRequest().denyAll()

                                //в order может попасть только роль USER
                                .requestMatchers("/order").hasRole("USER")

                                //в product может попасть только роль ADMIN
                                .requestMatchers("/product").hasRole("ADMIN")

//                                в POST запросы может только роль ADMIN
                                .requestMatchers(HttpMethod.POST).hasRole("ADMIN")

                                //в POST запросы может только роль ADMIN
                                .requestMatchers(HttpMethod.POST).hasAuthority("write")

                                .requestMatchers(HttpMethod.GET).hasAnyAuthority("read", "write")

                                //в любой запрос кроме тех что выше неавторизованный пользователь
                                //.anyRequest().anonymous()

                                //в любой запрос может любой авторизованный
                                .anyRequest().authenticated()

                ).formLogin(o -> o      // даже со значением по умолчанию добавить нормально окно логина
//                        .loginPage("/login")    // страница для логина
//                        .loginProcessingUrl("/login")       // Указывает URL-адрес для проверки учетных данных
                        .defaultSuccessUrl("/")
                        .permitAll()
                ).logout(o -> o
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );

        return http.build();
    }

}
