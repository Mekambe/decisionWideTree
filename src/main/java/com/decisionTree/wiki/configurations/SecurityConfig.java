package com.decisionTree.wiki.configurations;

//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("kinguin").password("{noop}kinguinator").roles("USER");

    }

    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
                //.antMatchers("/", "/home").access("hasRole('USER')")
               // .antMatchers("/**").hasRole("ADMIN")
//                .antMatchers("/**").permitAll();//authorizeRequests();
                //.antMatchers("/h2-console").permitAll();
               http.authorizeRequests()
                .antMatchers("/**").access("hasRole('USER')")
                .and().httpBasic();
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());



        http.csrf().disable();
        http.headers().frameOptions().disable();
    }











}


