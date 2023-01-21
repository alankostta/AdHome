package com.br.AdHome.AdHome.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/*
 * Novo tipo de configuração do WebSecurity ao qual não se usa mais os metodos de injeção de 
 * depenencia e nem os metodos de sobrecarga.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//Agora eu posso fazer a restrição direto no controller
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	LoginSucesso loginSucesso;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.
			userDetailsService(userDetailsServiceImpl)//O objeto "userDetailsServiceImpl" que vai retornar do banco os dados do usuário.
			.passwordEncoder(passwordEncoder());//Método que irá criptografar a senha do usuário
	}
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests()
			.antMatchers("/")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/acesso-negado")
			.and()
			.formLogin().successHandler(loginSucesso)
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").permitAll();
			
	}
}
