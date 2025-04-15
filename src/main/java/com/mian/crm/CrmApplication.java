package com.mian.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
		System.out.println("---------------------------------");
		System.out.println("|   API REST Miacorp RUNNING    |");
		System.out.println("---------------------------------");
		System.out.println("Port: 8080");
		System.out.println("Swagger UI: http://localhost:8080/swagger-ui/index.html");
		// enlace a github
		System.out.println("GitHub: https://github.com/mianlol01/corsscorp");
	}
}