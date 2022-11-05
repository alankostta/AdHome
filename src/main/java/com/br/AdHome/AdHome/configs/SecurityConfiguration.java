package com.br.AdHome.AdHome.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/*
 * Novo tipo de configuração do WebSecurity ao qual não se usa mais os metodos de injeção de 
 * depenencia e nem os metodos de sobrecarga.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//Agora eu posso fazer a restrição direto no controller
public class SecurityConfiguration{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new 
	}
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.POST,"/cadastrar-use","/cadastrar-user/**")
			.permitAll()
			.antMatchers(HttpMethod.DELETE,"/cadastrar-use","/cadastrar-user/**")
			.permitAll()
			.antMatchers(HttpMethod.GET,"/cadastrar-use","/cadastrar-user/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable()
			.formLogin((form) -> form
			.loginPage("/login")
			.permitAll()
			
		)
		.logout((logout) -> logout.permitAll());

	return http.build();
	}
}
