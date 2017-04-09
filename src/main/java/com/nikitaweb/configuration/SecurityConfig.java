package com.nikitaweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Никита on 05.04.2017.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }
}
