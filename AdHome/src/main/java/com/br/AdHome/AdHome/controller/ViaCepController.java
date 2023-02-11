package com.br.AdHome.AdHome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.AdHome.AdHome.configs.ViacepService;
import com.br.AdHome.AdHome.dto.EnderecoDto;
@Controller
@RestController("/cep")
public class ViaCepController {
	
	@Autowired
    private ViacepService viacepService;

    @GetMapping(value="cep")
    public ResponseEntity<EnderecoDto> getEnderecoByCep(@RequestParam(name="cep") String cep) {
        try {
            EnderecoDto enderecoDto = viacepService.getEndereco(cep);
            return new ResponseEntity<>(enderecoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
