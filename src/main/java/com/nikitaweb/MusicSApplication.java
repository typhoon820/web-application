package com.nikitaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.validation.OverridesAttribute;

@SpringBootApplication
public class MusicSApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
		return application.sources(MusicSApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicSApplication.class, args);
	}
}
