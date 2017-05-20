package com.nikitaweb.configuration;

import net.sourceforge.pagesdialect.PagesDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {
    @Bean
    public PagesDialect pagesDialect(){
        return new PagesDialect();
    }
}
