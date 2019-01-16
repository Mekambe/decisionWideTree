package com.decisionTree.wiki;

import com.decisionTree.wiki.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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



}
