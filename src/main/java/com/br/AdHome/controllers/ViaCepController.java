package com.br.AdHome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.AdHome.configs.ViacepService;
import com.br.AdHome.dto.EnderecoDto;


@RestController
public class ViaCepController {
	
    @Autowired
    private ViacepService viacepService;

    @GetMapping(value="/cep")
    public ResponseEntity<EnderecoDto> getEnderecoByCep(@RequestParam(name="cep") String cep) {
        try {
            EnderecoDto enderecoDto = viacepService.getEndereco(cep);
            if (enderecoDto != null) {
                return new ResponseEntity<>(enderecoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Isso imprimirá detalhes do erro no console do servidor
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

