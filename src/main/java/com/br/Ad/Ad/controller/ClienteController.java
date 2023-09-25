package com.br.Ad.Ad.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.Ad.Ad.configs.ViacepService;
import com.br.Ad.Ad.dto.ClienteDto;
import com.br.Ad.Ad.dto.ContatoDto;
import com.br.Ad.Ad.dto.EnderecoDto;
import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.models.ContatoEnum;
import com.br.Ad.Ad.models.Endereco;
import com.br.Ad.Ad.models.EnderecoEnum;
import com.br.Ad.Ad.services.ClienteService;
import com.br.Ad.Ad.services.ContatoService;
import com.br.Ad.Ad.services.EnderecoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */
@Controller
@RestController("/cliente")
@Transactional
public class ClienteController {
	// insere a classe e ápos isso cria o metodo construtor
	final ClienteService clienteService;
	final EnderecoService enderecoService;
	final ContatoService contatoService;
	final ViacepService viacepService;

	// Metodo construtor da classe Cliente Controller
	public ClienteController(ClienteService clienteSerice, EnderecoService enderecoService,
			ContatoService contatoService, ViacepService viacepService) {

		this.clienteService = clienteSerice;
		this.enderecoService = enderecoService;
		this.contatoService = contatoService;
		this.viacepService = viacepService;
	}

	@GetMapping("/cliente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView exibirCliente() {
		ClienteDto clienteDto = new ClienteDto();
		
		clienteDto.setEndereco(new ArrayList<>());
		
		clienteDto.getEndereco().add(new EnderecoDto());
		
		var mv = new ModelAndView("cliente/cliente");
		
		mv.addObject("clienteDto", clienteDto);
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		return mv;
	}

	// Criando os metodos getPost onde irá receber as requisições
	// que serão persistidas no banco
	@PostMapping("/cliente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView saveCliente(@Valid ClienteDto clienDto, BindingResult resultCliente) {

		ModelAndView mv = new ModelAndView("cliente/cliente");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		if (resultCliente.hasErrors()) {
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroCliente("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			Calendar cal = Calendar.getInstance();
			// int day = cal.get(Calendar.DATE);
			// int month = cal.get(Calendar.MONTH) + 1;
			// int year = cal.get(Calendar.YEAR);
			// int dow = cal.get(Calendar.DAY_OF_WEEK);
			// int dom = cal.get(Calendar.DAY_OF_MONTH);
			// int doy = cal.get(Calendar.DAY_OF_YEAR);
			Cliente cliente = clienDto.toCliente();
			cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
			cliente.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
			cliente.setAnoRef(cal.get(Calendar.YEAR));
			// Salva o contato no banco de dados
			
			clienteService.saveCliente(cliente);
			
			return new ModelAndView("redirect:/cliente/listar");
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}

	@GetMapping("cliente/listar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView listarClientes() {

		var mv = new ModelAndView("cliente/listar");
		Iterable<Cliente> cliente = clienteService.clienteProjecao();
		mv.addObject("cliente", cliente);
	
		mv.addObject("mensagem", "PESQUISA REALIZADA COM SUCESSO!");
		return mv;
	}

	@GetMapping("cliente/{clienteId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView getOneCliente(@PathVariable(value = "clienteId") Long id) {

		Optional<Cliente> clienteOptional = clienteService.findById(id);
		var mv = new ModelAndView("cliente/exibir");
		if (!clienteOptional.isPresent()) {
			return this.retornaErroCliente(
					"ERRO AO EXIBIR: a inscrição " + "(" + id + ") Motivo, não foi encontrado no banco esse cadastro!");

		} else {
			Cliente cliente = clienteOptional.get();
			mv.addObject("cliente", cliente);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("mensagem", "CLIENTE COM INSCRIÇÃO" + id + " ENCONTRADO COM SUCESSO!");
			mv.addObject("erro", false);
			return mv;
		}
	}

	@GetMapping("cliente/{clienteId}/excluir")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView deleteCliente(@PathVariable(value = "clienteId") Long id) {

		Optional<Cliente> clienteOptional = clienteService.findById(id);

		if (!clienteOptional.isPresent()) {
			return this.retornaErroCliente(
					"ERRO AO EXCLUIR: Cliente com inscrição (" + id + ") não foi encontrado no banco!");
		} else {
			 Cliente cliente = clienteOptional.get();

		     // Remove o cliente de cada endereço associado
		        List<Endereco> enderecos = cliente.getEndereco();
		        cliente.setEndereco(null);
		        clienteService.saveCliente(cliente); // Salva a alteração

		        for (Endereco endereco : enderecos) {
		            endereco.getCliente().remove(cliente);
		            enderecoService.saveEndereco(endereco); // Salva a alteração
		        }

		        cliente.setContato(null);
		        cliente.setEndereco(null);
		        clienteService.saveCliente(cliente);
		        clienteService.deleteCliente(cliente);

		        ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
		        mv.addObject("mensagem", "INSCRIÇÃO DO CLIENTE " + id + " EXCLUIDO COM SUCESSO!");
		        mv.addObject("erro", false);
		        return mv;
		}
	}
	// para testar no postman precisa usar o Id
	@GetMapping("cliente/{clienteId}/editar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView upClinte(@PathVariable(value = "clienteId") Long id, ClienteDto clienteDto,
			ContatoDto contatoDto, EnderecoDto enderecoDto) {

		ModelAndView mv = new ModelAndView("cliente/editar");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		Optional<Cliente> clienteOptional = this.clienteService.findById(id);
		Optional<Contato> contatoOptional = this.contatoService.findById(id);
		Optional<Endereco> enderecoOptional = this.enderecoService.findByIdEndereco(id);

		if (clienteOptional.isPresent() && contatoOptional.isPresent() && enderecoOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			
			mv.addObject("clienteId", cliente.getId());
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("mensagem", "CLIENTE ISCRIÇÃO COM " + id + " encontrado!");
			mv.addObject("erro", false);
			return mv;
		} else {
			return this.retornaErroCliente("ERRO AO EDITAR: Cliente com inscrição (" + id + ")");
		}
	}

	@PostMapping("cliente/{clienteId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView updateCliente(@PathVariable(value = "clienteId") Long id, @Valid ClienteDto clienteDto,
			BindingResult resultCliente, @Valid ContatoDto contatoDto, BindingResult resultContato,
			@Valid EnderecoDto enderecoDto, BindingResult resultEndereco) {

		ModelAndView mv = new ModelAndView("redirect:/cliente/listar");

		if (resultCliente.hasErrors()) {
			mv.addObject("clienteId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroCliente("ERRO AO SALVAR!!! PREENCHA OS CAMPOS NOME, SEXO, DATA DE NASCIMENTO!");
			return mv;
		} else if (resultContato.hasErrors()) {
			mv.addObject("clienteId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroCliente("ERRO AO SALVAR!! PREENCHA OS CAMPOS TELEFONE E EMAIL");
			return mv;
		} else if (resultEndereco.hasErrors()) {
			mv.addObject("clienteId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroCliente("ERRO AO SALVAR!! PREENCHA OS CAMPOS UF, CIDADE, BAIRRO, CEP, LOGRADOURO");
			return mv;
		} else {
			Optional<Cliente> clienteOptional = this.clienteService.findById(id);
			

			if (clienteOptional.isPresent()) {
				Cliente cliente = clienteDto.toCliente(clienteOptional.get());
			

				cliente.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));

			
				this.clienteService.saveCliente(cliente);
				mv.addObject("mensagem", "Cliente com inscrição " + id + " editado com sucesso!");
				mv.addObject("erro", false);
				return mv;
			} else {
				return this.retornaErroCliente("ERRO AO SALVAR: Cliente com inscrição (" + id + ")");
			}
		}
	}

	private ModelAndView retornaErroCliente(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
