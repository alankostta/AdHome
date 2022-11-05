package com.br.AdHome.AdHome.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
/*
 * var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(Fornecedor.class, FornecedorDto.class)
			.<Long>addMapping(src -> src.getFornecedorId(), 
				(dest, value) -> dest.setFornecedorId(value));
		modelMapper.createTypeMap(Fornecedor.class, FornecedorDto.class)
		.<String>addMapping(src -> src.getNome(), 
			(dest, value) -> dest.setNome(value));
		return modelMapper;
 */
