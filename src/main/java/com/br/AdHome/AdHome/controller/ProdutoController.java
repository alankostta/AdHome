package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.br.AdHome.AdHome.dto.FornecedorDto;
import com.br.AdHome.AdHome.dto.PedidoDto;
import com.br.AdHome.AdHome.dto.ProdutoDto;
import com.br.AdHome.AdHome.models.Fornecedor;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.services.FornecedorService;
import com.br.AdHome.AdHome.services.PedidoService;
import com.br.AdHome.AdHome.services.ProdutoService;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */
@Controller
@RequestMapping("/produto")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	@Autowired
	FornecedorService fornecedorService;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	ModelMapper modelMapper;

	public ProdutoController(ProdutoService produtoService, FornecedorService fornecedorService,
			PedidoService pedidoService) {
 
		this.produtoService = produtoService;
		this.fornecedorService = fornecedorService;
		this.pedidoService = pedidoService;
	}
	@GetMapping("")
	public ModelAndView exibiProdutos(ProdutoDto produtoDto, PedidoDto pedidoDto, FornecedorDto fornecedorDto) {
		var mv = new ModelAndView("produto/produto");
		return mv;
	}
	@PostMapping("")
	public ModelAndView saveProdutos(@Valid ProdutoDto produtoDto, BindingResult resultProduto,
			@Valid PedidoDto pedidoDto, BindingResult resultPedido, @Valid FornecedorDto fornecedorDto,
			BindingResult resultFornecedor) {

		ModelAndView mv = new ModelAndView("produto/produto");

		if (resultFornecedor.hasErrors() && resultProduto.hasErrors() && resultPedido.hasErrors()) {
			this.retornaErroProduto("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			
			Fornecedor fornecedor = fornecedorDto.toFornecedor();
			Pedido pedido = pedidoDto.toPedido();
			Produto produto = produtoDto.toProduto();
			produto.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
			produto.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
			produto.setFeronecedor(fornecedor);
			produto.setPedido(pedido);
			
			if(produto.getValorSaida() == null) {
				produto.setValorSaida(0.0);
				produtoService.salvarProduto(produto);
			}
			else {
				produtoService.salvarProduto(produto);
				
			}
			return new ModelAndView("redirect:/produto");
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}
	@GetMapping("produto/listar")
	public ModelAndView listarProdutos() {

		var mv = new ModelAndView("produto/listar");
		Iterable<Produto> produto = produtoService.findAll();
		mv.addObject("produto", produto);
		mv.addObject("mensagem", "PESQUISA REALIZADA COM SUCESSO!");
		return mv;
	}
	@GetMapping("produto/{produtoId}")
	public ModelAndView getOneProduto(@PathVariable(value = "produtoId") Long id) {

		Optional<Produto> produtoOptional = produtoService.findById(id);
		var mv = new ModelAndView("produto/exibir");
		if (!produtoOptional.isPresent()) {
			return this.retornaErroProduto(
					"ERRO AO EXIBIR: a inscrição " + "(" + id + ") Motivo, não foi encontrado no banco esse cadastro!");

		} else {
			Produto produto = produtoOptional.get();
			mv.addObject("produto", produto);
			mv.addObject("mensagem", "CLIENTE COM INSCRIÇÃO" + id + " ENCONTRADO COM SUCESSO!");
			mv.addObject("erro", false);
			return mv;
		}
	}
	// para testar no postman precisa usar o Id
	@GetMapping("produto/{produtoId}/editar")
	public ModelAndView upProduto(@PathVariable(value = "produtoId") Long id, ProdutoDto produtoDto,
			PedidoDto pedidoDto, FornecedorDto fornecedorDto) {

		ModelAndView mv = new ModelAndView("produto/editar");

		Optional<Produto> produtoOptional = this.produtoService.findById(id);
		Optional<Pedido> pedidoOptional = this.pedidoService.findById(id);
		Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);

		if (produtoOptional.isPresent() && pedidoOptional.isPresent() && fornecedorOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			Pedido pedido = pedidoOptional.get();
			Fornecedor fornecedor = fornecedorOptional.get();
			produtoDto.fromProduto(produto);
			pedidoDto.fromPedido(pedido);
			fornecedorDto.fromFornecedor(fornecedor);

			mv.addObject("produtoId", produto.getProdutoId());
			mv.addObject("mensagem", "CLIENTE ISCRIÇÃO COM " + id + " encontrado!");
			mv.addObject("erro", false);
			return mv;
		} else {
			return this.retornaErroProduto("ERRO AO EDITAR: Cliente com inscrição (" + id + ")");
		}
	}
	@PostMapping("produto/{produtoId}")
	public ModelAndView updateProduto(@PathVariable(value = "produtoId") Long id, @Valid ProdutoDto produtoDto,
			BindingResult resultProduto, @Valid PedidoDto pedidoDto, BindingResult resultPedido, 
			@Valid FornecedorDto fornecedorDto, BindingResult resultFornecedor) {
		
		ModelAndView mv = new ModelAndView("redirect:/produto/listar");
		
		if (resultProduto.hasErrors()) {
			mv.addObject("produtoId", id);
			this.retornaErroProduto("ERRO AO SALVAR!!! PREENCHA OS CAMPOS NOME, SEXO, DATA DE NASCIMENTO!");
			return mv;
		} 
		else if(resultPedido.hasErrors()) {
			mv.addObject("pedidoId", id);
			this.retornaErroProduto("ERRO AO SALVAR!! PREENCHA OS CAMPOS TELEFONE E EMAIL");
			return mv;
		}
		else if(resultFornecedor.hasErrors()) {
			mv.addObject("fornecedorId", id);
			this.retornaErroProduto("ERRO AO SALVAR!! PREENCHA OS CAMPOS UF, CIDADE, BAIRRO, CEP, LOGRADOURO");
			return mv;
		}
		else {
				Optional<Produto> produtoOptional = this.produtoService.findById(id);
				Optional<Pedido> pedidoOptional = this.pedidoService.findById(id);
				Optional<Fornecedor> fornecedorOptional = this.fornecedorService.findById(id);
				
				if (produtoOptional.isPresent()&& pedidoOptional.isPresent()&& fornecedorOptional.isPresent()) {
					Produto produto = produtoDto.toProduto(produtoOptional.get());
					Pedido pedido = pedidoDto.toPedido(pedidoOptional.get());
					Fornecedor fornecedor = fornecedorDto.toFornecedor(fornecedorOptional.get());
					
					produto.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
					
					this.produtoService.salvarProduto(produto);
					this.pedidoService.savePedido(pedido);
					this.fornecedorService.saveFornecedor(fornecedor);
					mv.addObject("mensagem", "Cliente com inscrição " + id + " editado com sucesso!");
					mv.addObject("erro", false);
					return mv;
			} else {
					return this.retornaErroProduto("ERRO AO SALVAR: Cliente com inscrição ("+id+")");
			}
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}
	@GetMapping("produto/{produtoId}/excluir")
	public ModelAndView deleteProduto(@PathVariable(value = "produtoId") Long id) {
		
		Optional<Produto> produtoOptional = produtoService.findById(id);
		
		if (!produtoOptional.isPresent()) {
			return this.retornaErroProduto("ERRO AO EXCLUIR: Cliente com inscrição ("+id+") não foi encontrado no banco!");
		} else {
				ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
				produtoService.deleteProduto(produtoOptional.get());
				mv.addObject("mensagem", "INSCRIÇÃO DO CLIENTE" + id + " EXCLUIDO COM SUCESSO!");
				mv.addObject("erro", false);
				return mv;
		}
	}
	@GetMapping(value="buscarPorNome")
	@ResponseBody 
	public ResponseEntity<List<Fornecedor>> buscarPorNome(@RequestParam(name="name") String name) {
		//List<Fornecedor> fornecedor = fornecedorService.findByName(name.trim().toLowerCase());
		List<Fornecedor> fornecedor = fornecedorService.findByName(name);
	
		return new ResponseEntity<List<Fornecedor>>(fornecedor,HttpStatus.OK);
	}
	private ModelAndView retornaErroProduto(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/fornecedor/fornecedor");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
