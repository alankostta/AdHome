package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.AdHome.AdHome.dto.PagamentoDto;
import com.br.AdHome.AdHome.models.Pagamento;
import com.br.AdHome.AdHome.services.PagamentoService;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/adhome/pagamento")
public class PagamentoController {
	final PagamentoService pagamentoService;
	public PagamentoController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	@PostMapping
	public ResponseEntity <Object> salvarPagamentos(@RequestBody @Valid PagamentoDto pagamentoDto){
		var pagamento = new Pagamento();
		pagamento.setDataPagamanto(LocalDateTime.now(ZoneId.of("UTC")));
		BeanUtils.copyProperties(pagamentoDto, pagamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.salvePagamanto(pagamento));
	}
	@GetMapping
	public ResponseEntity <Page<Pagamento>> getAllPagamentos(@PageableDefault(page = 0,
	size = 10, direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.findAll(pageable));
	}
	@GetMapping("/{pagamentoId}")
	public ResponseEntity<Object> getOnePagamentos(@PathVariable(value = "pagamentoId")Long id){
		Optional<Pagamento> pagamentoOptional = pagamentoService.findById(id);
		if(!pagamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(pagamentoOptional.get());
	}
	@DeleteMapping("/{pagamentoId}")
	public ResponseEntity<Object> deletePagamentos(@PathVariable(value = "pagamantoId")Long id){
		Optional<Pagamento> pagamentoOptional = pagamentoService.findById(id);
		if(!pagamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado para apagar!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoOptional.get());
	}
	@PutMapping("/{pagamentoId}")
	public ResponseEntity<Object> updatePagamentos(@PathVariable(value = "pagamentoId")Long id,
			@RequestBody @Valid PagamentoDto pagamentoDto){
		Optional<Pagamento> pagamentoOptional = pagamentoService.findById(id);
		if(!pagamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado para atualização!");
		}
		var pagamento = new Pagamento();
		BeanUtils.copyProperties(pagamentoDto, pagamento);
		pagamento.setDataPagamanto(pagamentoOptional.get().getDataPagamanto());
		pagamento.setPagamentoId(pagamentoOptional.get().getPagamentoId());
		return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.salvePagamanto(pagamento));
	}
}
