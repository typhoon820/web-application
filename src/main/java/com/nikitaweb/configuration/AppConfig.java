package com.nikitaweb.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import javax.validation.Valid;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @Value("${spring.queries.user-query}")
    private String usersQuery;

    @Value("${spring.queries.status-query}")
    private String rolesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(rolesQuery)
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/home/adduser").hasAuthority("Admin")
                .antMatchers("/home/addsong").hasAuthority("Admin").anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }
}
