package com.decisionTree.wiki.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/", "/home").access("hasRole('USER')")
               // .antMatchers("/**").hasRole("ADMIN")
                .antMatchers("/h2-console/*").permitAll();//authorizeRequests();
                //.antMatchers("/h2-console").permitAll();
//               http.authorizeRequests()
//                .antMatchers("/h2-console").access("hasRole('USER')")
//                       .and().httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }




}


