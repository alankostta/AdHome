package com.br.AdHome.AdHome;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.br.AdHome.AdHome.configs.ViacepService;
import com.br.AdHome.AdHome.models.Endereco;

@SpringBootApplication
@EnableJpaAuditing
public class AdHomeApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(AdHomeApplication.class, args);
		ViacepService viacepService = new ViacepService();

		try {
			Endereco endereco = viacepService.getEndereco("74946090");
			System.out.println(endereco);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
