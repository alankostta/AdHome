package com.br.Ad.Ad.repositories;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.br.Ad.Ad.dto.ClienteDto;
import com.br.Ad.Ad.models.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteDtoMappers {
    ClienteDtoMappers INSTANCE = Mappers.getMapper(ClienteDtoMappers.class);

    @Mapping(target = "contato", source = "contato.toContatoDto")
    @Mapping(target = "endereco", source = "endereco")
    ClienteDto fromCliente(Cliente cliente);

    @Mapping(target = "contato", source = "contato.toContato")
    @Mapping(target = "endereco", source = "endereco")
    Cliente toCliente(ClienteDto clienteDto);

    void updateClienteFromDto(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}

