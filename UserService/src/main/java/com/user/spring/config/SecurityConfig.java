package com.user.spring.config;

import com.user.spring.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailService detailService;

    public SecurityConfig(UserDetailService detailService) {
        this.detailService = detailService;
    }


    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager manager(HttpSecurity security) throws Exception{
        AuthenticationManagerBuilder builder=security.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(detailService);
        return builder.build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests().requestMatchers("/register","/login").permitAll()
                .and()
                .authenticationManager(manager(httpSecurity));

        return httpSecurity.build();
    }


}
