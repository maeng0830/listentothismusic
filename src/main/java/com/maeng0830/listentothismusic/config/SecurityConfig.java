package com.maeng0830.listentothismusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화 -> 특정 요청 메소드에 대해서 필요 권한을 부여할 수 있음.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodingPassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/member/**").authenticated()
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin().loginPage("/login-form")
            .usernameParameter("email")
            .loginProcessingUrl("/login").defaultSuccessUrl("/"); // "/login-form"을 요청해서 로그인을 하면 "/"로 이동됨. 다른 주소로 요청("/member" 등)해서 로그인을 하면 그 페이지로 이동된다.
    }
}
