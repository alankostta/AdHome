package com.br.AdHome.AdHome;

import org.apache.hc.core5.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdHomeApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(AdHomeApplication.class, args);
		
	}
}
