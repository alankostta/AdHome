package com.br.AdHome.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.models.Contato;
import com.br.AdHome.models.ContatoEnum;
import com.br.AdHome.models.Endereco;
import com.br.AdHome.models.EnderecoEnum;
import com.br.AdHome.models.Fornecedor;
import com.br.AdHome.services.FornecedorService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RestController("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "/fornecedor")
	public ModelAndView exibirFornecedor() {
		Fornecedor fornecedor = new Fornecedor();
		Contato contato = new Contato();
		fornecedor.setEndereco(new ArrayList<>());
		fornecedor.getEndereco().add(new Endereco());
		fornecedor.setContato(contato);
		ModelAndView mv = new ModelAndView("fornecedor/fornecedor");

		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		mv.addObject("fornecedor", fornecedor);
		return mv;
	}

	@PostMapping(value = "/fornecedor")
	public ModelAndView saveFornecedor(@Valid Fornecedor fornecedor, BindingResult resultFornecedor) {

		ModelAndView mv = new ModelAndView("fornecedor/fornecedor");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		if (resultFornecedor.hasErrors()) {
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			Calendar cal = Calendar.getInstance();

			fornecedor.setDataCadastroForne(LocalDateTime.now(ZoneId.of("UTC")));
			fornecedor.setDataAlteraForne(LocalDateTime.now(ZoneId.of("UTC")));
			fornecedor.setAnoRef(cal.get(Calendar.YEAR));

			fornecedorService.saveFornecedor(fornecedor);

			return new ModelAndView("redirect:/fornecedor/listarFor");
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}

	@GetMapping("fornecedor/listarFor")
	public ModelAndView listarFornecedor() {

		ModelAndView mv = new ModelAndView("fornecedor/listarFor");
		Iterable<Fornecedor> fornecedor = fornecedorService.projecaoFornecedor();
		mv.addObject("fornecedor", fornecedor);
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());
		mv.addObject("mensagem", "PESQUISA REALIZADA COM SUCESSO!");
		return mv;
	}

	@GetMapping("exibirFornecedor/{id}")
	public ModelAndView getOneFornecedor(@PathVariable(value = "id") Long id) {

		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
		var mv = new ModelAndView("fornecedor/exibirFornecedor");
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
	@GetMapping("fornecedorEditar/{id}/editar")
	public ModelAndView upFornecedor(@PathVariable(value = "id") Long id, Fornecedor fornecedor, Contato contato,
			Endereco endereco) {

		ModelAndView mv = new ModelAndView("fornecedor/editarFornecedor");
		mv.addObject("listaContato", ContatoEnum.values());
		mv.addObject("listaEndereco", EnderecoEnum.values());

		Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);

		if (fornecedorOptional.isPresent()) {
			fornecedor = fornecedorOptional.get();

			mv.addObject("id", fornecedor.getId());
			mv.addObject("fornecedor", fornecedor);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			mv.addObject("mensagem", "CLIENTE ISCRIÇÃO COM " + id + " encontrado!");
			mv.addObject("erro", false);
			return mv;
		} else {
			return this.retornaErroFornecedor("ERRO AO EDITAR: Cliente com inscrição (" + id + ")");
		}
	}

	@Transactional
	@PostMapping(value = "fornecedor/fornecedor/{id}")
	public ModelAndView updateFornecedor(@PathVariable(value = "id") Long id, @Valid Fornecedor fornecedor,
			BindingResult resultFornecedor) {

		ModelAndView mv = new ModelAndView("redirect:/exibirFornecedor/" + id);

		if (resultFornecedor.hasErrors()) {
			mv.addObject("id ", id);
			mv.addObject("listaContato", ContatoEnum.values());
			mv.addObject("listaEndereco", EnderecoEnum.values());
			this.retornaErroFornecedor("ERRO AO SALVAR!!! PREENCHA OS CAMPOS NOME, SEXO, DATA DE NASCIMENTO!");
			return mv;
		} else {
			Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);
			
			if (fornecedorOptional.isPresent()) {
				Fornecedor forne = fornecedorOptional.get();

				fornecedor.setDataAlteraForne(LocalDateTime.now(ZoneId.of("UTC")));
				
				fornecedor.setAnoRef(forne.getAnoRef());
				fornecedor.setDataCadastroForne(forne.getDataCadastroForne());

				this.fornecedorService.saveFornecedor(fornecedor);
				
				mv.addObject("id ", id);
				mv.addObject("listaContato", ContatoEnum.values());
				mv.addObject("listaEndereco", EnderecoEnum.values());
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

	@GetMapping("fornecedor/{id}/excluir")
	public ModelAndView deleteFornecedor(@PathVariable(value = "id") Long id) {

		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);

		if (!fornecedorOptional.isPresent()) {
			return this.retornaErroFornecedor(
					"ERRO AO EXCLUIR: Cliente com inscrição (" + id + ") não foi encontrado no banco!");
		} else {
			ModelAndView mv = new ModelAndView("redirect:/fornecedor/listarFor");
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
