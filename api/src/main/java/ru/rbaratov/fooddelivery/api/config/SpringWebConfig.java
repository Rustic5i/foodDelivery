package ru.rbaratov.fooddelivery.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Конфигурация web
 *
 * @author rbaratov
 */
@Configuration
@EnableWebSecurity
public class SpringWebConfig {

    /**
     * Описываем куда пользователю есть доступ,
     * Куда пользователю нет доступа тд.
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers(new AntPathRequestMatcher("**" + "admin" + "/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("**" + "admin" + "/**"))
                                .authenticated()
                )
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers(new AntPathRequestMatcher("**" + "admin" + "/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

//        http.csrf().disable()
//                .authorizeRequests().anyRequest().permitAll();
        return http.build();
    }

}