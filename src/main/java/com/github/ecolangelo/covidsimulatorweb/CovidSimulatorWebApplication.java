package com.github.ecolangelo.covidsimulatorweb;

import com.github.ecolangelo.ingestion.CovidDataService;
import com.github.ecolangelo.ingestion.HttpGithubService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@SpringBootApplication
public class CovidSimulatorWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidSimulatorWebApplication.class, args);
	}


	@Bean
	public CovidDataService covidDataService() {
	    Properties p = new Properties();
		try {
			p.load(new FileInputStream("credentials"));
			return new HttpGithubService(p.getProperty("user"),p.getProperty("password"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/national").allowedOrigins("*");
			}
		};
	}

}
