package com.br.AdHome.AdHome.configs;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
/*
 * A classe de configuração e o local ao qual eu crio as classes de configuração
 * do sistema ex: email, formatação de data e horas, conexão com o banco entre outras
 */
@Configuration
public class DateConfig {
	public static final String DATETIME_FORMT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new 
			LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMT));
	@Bean
	@Primary
	public ObjectMapper objectMapping() {
		JavaTimeModule module = new JavaTimeModule();
		module.addSerializer(LOCAL_DATETIME_SERIALIZER);
		return new ObjectMapper().registerModule(module);
	}
}
