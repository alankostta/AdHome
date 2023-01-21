package com.br.AdHome.AdHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdHomeApplication.class, args);
	}
}
