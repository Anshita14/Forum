package com.social.forum.config;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.social.forum.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends Session {

    @Bean
    public UserDetailsService customUserDetailService(){

        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception{

        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider();
        authenticate.setUserDetailsService(customUserDetailService());
        authenticate.setPasswordEncoder(passwordEncoder());

        return authenticate;
    }

  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)->csrf.disable()).cors((cors)->cors.disable())
                .authorizeHttpRequests((request)->request.requestMatchers("/social/login")
                		.permitAll().requestMatchers("/social/createUser").permitAll().requestMatchers("/css/**").permitAll()
                		.anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/social/login").permitAll()
                        .loginProcessingUrl("/perform_login").successForwardUrl("/social/forum")
                        .failureForwardUrl("/social/login").defaultSuccessUrl("/social/forum"))
                       
                .logout((logout)->logout.logoutUrl("/perform_logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/social/login"))
                .httpBasic();

        return http.build();
    }
}