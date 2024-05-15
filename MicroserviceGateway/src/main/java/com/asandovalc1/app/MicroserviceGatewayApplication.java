package com.asandovalc1.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
//@EnableFeignClients
@EnableDiscoveryClient
public class MicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			WebClient.Builder webClientBuilder = (WebClient.Builder) ctx.getBean(WebClient.Builder.class);
			if (webClientBuilder != null) {
				System.out.println("WebClient.Builder bean has been created successfully.");
			} else {
				System.out.println("Failed to create WebClient.Builder bean.");
			}
		};
	}

}
