package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.br.AdHome.AdHome.dto.ContatoDto;
import com.br.AdHome.AdHome.dto.EnderecoDto;
import com.br.AdHome.AdHome.dto.FornecedorDto;
import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.models.ContatoEnum;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.models.EnderecoEnum;
import com.br.AdHome.AdHome.models.Fornecedor;
import com.br.AdHome.AdHome.services.ContatoService;
import com.br.AdHome.AdHome.services.EnderecoService;
import com.br.AdHome.AdHome.services.FornecedorService;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */

@Controller
@RestController("/fornecedor")
public class FornecedorController{

	final FornecedorService fornecedorService;
	final ContatoService contatoService;
	final EnderecoService enderecoService;

	public FornecedorController(FornecedorService fornecedorService, ContatoService contatoService,
			EnderecoService enderecoService) {
		this.fornecedorService = fornecedorService;
		this.contatoService = contatoService;
		this.enderecoService = enderecoService;
	}
	@GetMapping(value="/fornecedor")
	public ModelAndView exibirFornecedor(FornecedorDto fornecedorDto, ContatoDto contatoDto,
			EnderecoDto enderecoDto) {
		ModelAndView mv = new ModelAndView("fornecedor/fornecedor");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		return mv;
	}
	
	@PostMapping(value="/fornecedor")
	public ModelAndView saveFornecedor(@Valid FornecedorDto fornecedorDto, BindingResult resultFornecedor,
			@Valid ContatoDto contatoDto, BindingResult resultContato, @Valid EnderecoDto enderecoDto,
			BindingResult resultEndereco) {

		ModelAndView mv = new ModelAndView("fornecedor/fornecedor");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		if (resultFornecedor.hasErrors() && resultContato.hasErrors() && resultEndereco.hasErrors()) {
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			Calendar cal = Calendar.getInstance();
			Fornecedor fornecedo = fornecedorDto.toFornecedor();
			Contato contat = contatoDto.toContato();
			Endereco enderec = enderecoDto.toEndereco();
			fornecedo.setDataCadastroForne(LocalDateTime.now(ZoneId.of("UTC")));
			fornecedo.setDataAlteraForne(LocalDateTime.now(ZoneId.of("UTC")));
			fornecedo.setAnoRef(cal.get(Calendar.YEAR));
			
			Set<Contato> conta = new HashSet<Contato>();
			conta.add(contat);
			fornecedo.setContatos(conta);
			contatoService.saveContato(contat);
			
			Set<Endereco> endere = new HashSet<Endereco>();
			endere.add(enderec);
			enderecoService.saveEndereco(enderec);
			
			fornecedo.setEndereco(endere);
			fornecedorService.saveFornecedor(fornecedo);

			return new ModelAndView("redirect:/fornecedor");
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}
	@GetMapping("fornecedor/listar")
	public ModelAndView listarFornecedor() {

		var mv = new ModelAndView("cliente/listar");
		Iterable<Fornecedor> fornecedor = fornecedorService.findAll();
		mv.addObject("fornecedor", fornecedor);
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		mv.addObject("mensagem", "PESQUISA REALIZADA COM SUCESSO!");
		return mv;
	}
	@GetMapping("fornecedor/{fornecedorId}")
	public ModelAndView getOneFornecedor(@PathVariable(value = "fornecedorId") Long id) {

		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
		var mv = new ModelAndView("cliente/exibir");
		if (!fornecedorOptional.isPresent()) {
			return this.retornaErroFornecedor(
					"ERRO AO EXIBIR: a inscrição " + "(" + id + ") Motivo, não foi encontrado no banco esse cadastro!");

		} else {
			Fornecedor fornecedor = fornecedorOptional.get();
			mv.addObject("fornecedor", fornecedor);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("mensagem", "CLIENTE COM INSCRIÇÃO" + id + " ENCONTRADO COM SUCESSO!");
			mv.addObject("erro", false);
			return mv;
		}
	}
	// para testar no postman precisa usar o Id
	@GetMapping("fornecedor/{fornecedorId}/editar")
	public ModelAndView upFornecedor(@PathVariable(value = "fornecedorId") Long id, FornecedorDto fornecedorDto,
			ContatoDto contatoDto, EnderecoDto enderecoDto) {

		ModelAndView mv = new ModelAndView("fornecedor/editar");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);
		Optional<Contato> contatoOptional = this.contatoService.findById(id);
		Optional<Endereco> enderecoOptional = this.enderecoService.findByIdEndereco(id);

		if (fornecedorOptional.isPresent() && contatoOptional.isPresent() && enderecoOptional.isPresent()) {
			Fornecedor fornecedor = fornecedorOptional.get();
			Contato contato = contatoOptional.get();
			Endereco endereco = enderecoOptional.get();
			fornecedorDto.fromFornecedor(fornecedor);
			contatoDto.fromContato(contato);
			enderecoDto.fromEndereco(endereco);

			mv.addObject("fornecedorId", fornecedor.getFornecedorId());
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("mensagem", "CLIENTE ISCRIÇÃO COM " + id + " encontrado!");
			mv.addObject("erro", false);
			return mv;
		} else {
			return this.retornaErroFornecedor("ERRO AO EDITAR: Cliente com inscrição (" + id + ")");
		}
	}
	@PostMapping("fornecedor/{fornecedorId}")
	public ModelAndView updateFornecedor(@PathVariable(value = "fornecedorId") Long id,
			@Valid FornecedorDto fornecedorDto, BindingResult resultFornecedor, @Valid ContatoDto contatoDto,
			BindingResult resultContato, @Valid EnderecoDto enderecoDto, BindingResult resultEndereco) {

		ModelAndView mv = new ModelAndView("redirect:/fornecedor/listar");

		if (resultFornecedor.hasErrors()) {
			mv.addObject("fornecedorId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR!!! PREENCHA OS CAMPOS NOME, SEXO, DATA DE NASCIMENTO!");
			return mv;
		} else if (resultContato.hasErrors()) {
			mv.addObject("fornecedorId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR!! PREENCHA OS CAMPOS TELEFONE E EMAIL");
			return mv;
		} else if (resultEndereco.hasErrors()) {
			mv.addObject("fornecedorId", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR!! PREENCHA OS CAMPOS UF, CIDADE, BAIRRO, CEP, LOGRADOURO");
			return mv;
		} else {
			Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);
			Optional<Contato> contatoOptional = this.contatoService.findById(id);
			Optional<Endereco> enderecoOptional = this.enderecoService.findByIdEndereco(id);

			if (fornecedorOptional.isPresent() && contatoOptional.isPresent() && enderecoOptional.isPresent()) {
				Fornecedor fornecedor = fornecedorDto.toFornecedor(fornecedorOptional.get());
				Contato contato = contatoDto.toContato(contatoOptional.get());
				Endereco endereco = enderecoDto.toEndereco(enderecoOptional.get());

				fornecedor.setDataAlteraForne(LocalDateTime.now(ZoneId.of("UTC")));

				this.fornecedorService.saveFornecedor(fornecedor);
				this.contatoService.saveContato(contato);
				this.enderecoService.saveEndereco(endereco);
				mv.addObject("mensagem", "Cliente com inscrição " + id + " editado com sucesso!");
				mv.addObject("erro", false);
				return mv;
			} else {
				return this.retornaErroFornecedor("ERRO AO SALVAR: Cliente com inscrição (" + id + ")");
			}
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}
	@GetMapping("fornecedor/{fornecedorId}/excluir")
	public ModelAndView deleteFornecedor(@PathVariable(value = "fornecedorId") Long id) {

		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);

		if (!fornecedorOptional.isPresent()) {
			return this.retornaErroFornecedor(
					"ERRO AO EXCLUIR: Cliente com inscrição (" + id + ") não foi encontrado no banco!");
		} else {
			ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
			fornecedorService.deleteFornecedor(fornecedorOptional.get());
			mv.addObject("mensagem", "INSCRIÇÃO DO CLIENTE" + id + " EXCLUIDO COM SUCESSO!");
			mv.addObject("erro", false);
			return mv;
		}
	}
	private ModelAndView retornaErroFornecedor(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/fornecedor/fornecedor");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
