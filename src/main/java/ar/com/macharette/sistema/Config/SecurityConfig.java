package ar.com.macharette.sistema.Config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //trabajar los metodos security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {



        return  http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                //lambda
                authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                        )
                //llamar al login por defecto de spring security
                .formLogin(withDefaults())
                .build();



    }





}
