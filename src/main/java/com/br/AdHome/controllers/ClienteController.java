package com.br.AdHome.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.AdHome.models.Cliente;
import com.br.AdHome.models.Contato;
import com.br.AdHome.models.ContatoEnum;
import com.br.AdHome.models.Endereco;
import com.br.AdHome.models.EnderecoEnum;
import com.br.AdHome.services.ClienteService;
import com.br.AdHome.services.EnderecoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RestController(value = "/cliente")
public class ClienteController {
	// insere a classe e ápos isso cria o metodo construtor
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/cliente")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView exibirCliente() {
		Cliente cliente = new Cliente();
		Contato contato = new Contato();
		cliente.setEndereco(new ArrayList<>());
		cliente.getEndereco().add(new Endereco());
		cliente.setContato(contato);
		var mv = new ModelAndView("cliente/cliente");

		mv.addObject("cliente", cliente);
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		return mv;
	}

	// Criando os metodos getPost onde irá receber as requisições
	// que serão persistidas no banco
	@Transactional
	@PostMapping(value = "cliente/aplicar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView saveCliente(@Valid Cliente cliente, BindingResult errors, RedirectAttributes attr) {

		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("cliente/cliente");
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("errors", errors);
			mv.addObject("fail", "ERRO AO TENTAR SALVAR CLIENTE!");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
			Calendar cal = Calendar.getInstance();
			cliente.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
			cliente.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
			cliente.setAnoRef(cal.get(Calendar.YEAR));
			clienteService.saveCliente(cliente);
			attr.addFlashAttribute("success", "CLIENTE SALVO COM SUCESSO!");
			return mv;
		}
	}

	@GetMapping("cliente/listar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView listarClientes() {

		var mv = new ModelAndView("cliente/listar");
		Iterable<Cliente> cliente = clienteService.clienteProjecao();
		mv.addObject("cliente", cliente);
		
		return mv;
	}

	@GetMapping("exibir/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
	public ModelAndView getOneCliente(@PathVariable(value = "id") Long id) {
		
		var mv = new ModelAndView("cliente/exibir");
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if (!clienteOptional.isPresent()) {
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("fail", "ERRO AO EXIBIR: a inscrição [*" + id + "*] Motivo, não foi encontrado no banco esse cadastro!");
			return mv;
		} else {
			Cliente cliente = clienteOptional.get();
			mv.addObject("cliente", cliente);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("success", "CLIENTE COM INSCRIÇÃO [* " + id + " *] ENCONTRADO COM SUCESSO!");
			return mv;
		}
	}

	@Transactional
	@GetMapping("/cliente/{id}/excluir")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView deleteCliente(@PathVariable(value = "id") Long id, RedirectAttributes attr ) {
		ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
		Optional<Cliente> clienteOptional = clienteService.findById(id);

		if (!clienteOptional.isPresent()) {
			attr.addFlashAttribute("fail", "ERRO AO EXCLUIR: CLIENTE COM INSCRIÇÃO [* " + id + " *] NÃO FOI ENCONTRADO NO BANCO!");
			
			return mv;
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
			attr.addFlashAttribute("success", "INSCRIÇÃO DO CLIENTE [* " + id + " *] EXCLUIDO COM SUCESSO!");
			
			return mv;
		}
	}

	// para testar no postman precisa usar o Id
	@GetMapping("/cliente/{id}/editar")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView upClinte(@PathVariable(value = "id") Long id, Cliente cliente) {

		ModelAndView mv = new ModelAndView("cliente/editar");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		Optional<Cliente> clienteOptional = this.clienteService.findById(id);

		if (clienteOptional.isPresent()) {
			Cliente clien = clienteOptional.get();
			mv.addObject("id", clien.getId());
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("cliente", clien);
			mv.addObject("success", "CLIENTE COM ISCRIÇÃO [* " + id + " *] ENCONTRADO!");
			
			return mv;
		} else {
			mv.addObject("fail", "ERRO AO BUSCAR: CLIENTE COM INSCRIÇÃO [* " + id + " *]");
			return mv;
		}
	}

	@Transactional
	@PostMapping(value = "cliente/aplicar/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ModelAndView updateCliente(@PathVariable(value = "id") Long id, @Valid Cliente client,
			BindingResult errors, RedirectAttributes attr ) {

		if (errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("redirect:/cliente/{id}/editar");
			mv.addObject("id", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			
			attr.addFlashAttribute("fail", "ERRO AO SALVAR CLIENTE!");
			return mv;
		} else {
			// Busque o cliente existente no repositório
			Cliente cliente = clienteService.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("CLIENTE COM ID: [* " +id+" *] NÃO LOCALIZADO"));

			// Atualize a data de alteração
			client.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
			client.setAnoRef(cliente.getAnoRef());
			client.setDataCadastro(cliente.getDataCadastro());
			// Salve o cliente atualizado no repositório
			clienteService.saveCliente(client);
			ModelAndView mv = new ModelAndView("redirect: exibir/{id}");
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			attr.addFlashAttribute("success", "CLIENTE COM INSCRIÇÃO [* "+id+" *] EDITADO COM SUCESSO!");
			
			return mv;
		}
	}
}
