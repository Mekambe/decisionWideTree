package com.decisionTree.wiki;

import com.decisionTree.wiki.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableConfigurationProperties  ({FileStorageProperties.class})//enabling ConfigurationProperties
public class WikiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate (){


		return new RestTemplateBuilder().build();

	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("file:///C:/Users/j.misiorny/Desktop/Kinguinator/Front/Wersja%203/Kinguinator/dashboard.html")
//						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
//
//				;
//			}
//		};
//	}

}
