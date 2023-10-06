package com.br.Ad.Ad.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.Ad.Ad.configs.UserDetailsServiceImpl;
import com.br.Ad.Ad.dto.ClienteDto;
import com.br.Ad.Ad.dto.PedidoDto;
import com.br.Ad.Ad.models.BandeiraCartao;
import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.models.Fornecedor;
import com.br.Ad.Ad.models.ItemPedido;
import com.br.Ad.Ad.models.Pedido;
import com.br.Ad.Ad.models.PedidoEnumStatus;
import com.br.Ad.Ad.models.PedidoEnumTipoPagamento;
import com.br.Ad.Ad.models.Produto;
import com.br.Ad.Ad.services.ClienteService;
import com.br.Ad.Ad.services.FornecedorService;
import com.br.Ad.Ad.services.PedidoService;
import com.br.Ad.Ad.services.ProdutoService;

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
@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private FornecedorService fornecedorService;

	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private PedidoDto pedidoDto = new PedidoDto();
	private ClienteDto clienteDto = new ClienteDto();
	
	private void calcularTotal() {
		this.pedidoDto.setValorPedido(0.0);
		for (ItemPedido i : itens) {
			pedidoDto.setValorPedido(pedidoDto.getValorPedido() + i.getSubTotal());
		}	
	}

	@GetMapping("/pedido")
	public ModelAndView exibirPedido() {

		ModelAndView mv = new ModelAndView("/pedido/pedido");
		calcularTotal();
		mv.addObject("listaStatus", PedidoEnumStatus.values());
		mv.addObject("listaPagamento", PedidoEnumTipoPagamento.values());
		mv.addObject("listaCartao", BandeiraCartao.values());
		mv.addObject("pedidoDto", pedidoDto);
		mv.addObject("listaItens", itens);
		mv.addObject("users", userDetailsServiceImpl.findAllUser());
		mv.addObject("produtos", produtoService.findAll());

		return mv;
	}

	@GetMapping("/cancelar")
	public ModelAndView cancelarPedido() {

		ModelAndView mv = new ModelAndView("redirect:/pedido/pedido");
		pedidoDto = new PedidoDto();
		itens.clear();
		mv.addObject("listaStatus", PedidoEnumStatus.values());
		mv.addObject("listaPagamento", PedidoEnumTipoPagamento.values());
		mv.addObject("listaCartao", BandeiraCartao.values());
		mv.addObject("pedidoDto", pedidoDto);
		mv.addObject("listaItens", itens);
		mv.addObject("users", userDetailsServiceImpl.findAllUser());
		mv.addObject("produtos", produtoService.findAll());

		return mv;
	}
	@Transactional
	@PostMapping("/aplicar")
	public ModelAndView savePedido(@Valid PedidoDto pedidoDto, BindingResult resultPedido) {
		
		ModelAndView mv = new ModelAndView("/pedido/pedido");

		if (resultPedido.hasErrors()) {
			this.retornaErroPedido("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			this.clienteDto.setDataAltera(this.clienteDto.getDataAltera());
			this.clienteDto.setDataCadastro(this.clienteDto.getDataCadastro());
			this.clienteDto.setContato(this.clienteDto.getContato());
			this.pedidoDto.setDataCadastro(pedidoDto.getDataCadastro());
			this.pedidoDto.setAduser(pedidoDto.getAduser());
			this.pedidoDto.setEnumCartao(pedidoDto.getEnumCartao());
			this.pedidoDto.setEnumPagamento(pedidoDto.getEnumPagamento());
			this.pedidoDto.setEnumStatus(pedidoDto.getEnumStatus());
			this.pedidoDto.setObservacaoPedido(pedidoDto.getObservacaoPedido());
			//this.pedidoDto.setValorPedido(pedidoDto.getValorPedido());
			
			LocalDateTime dataHoraAtual = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dataHoraFormatada = dataHoraAtual.format(formatter);
			LocalDateTime dataHoraConvertida = LocalDateTime.parse(dataHoraFormatada, formatter);
			System.out.println(pedidoDto.getValorPedido());
			this.pedidoDto.setItens(itens);
			Pedido pedido = new Pedido();
			pedido = this.pedidoDto.toPedido();
			
			for (ItemPedido it : itens) {
				it.setPedido(pedido);
			}
			pedido.setValorPedido(this.pedidoDto.getValorPedido());
			Calendar cal = Calendar.getInstance();
			pedido.setAnoRef(cal.get(Calendar.YEAR));
			pedido.setDataAlteraPedido(dataHoraConvertida);
			pedido.setDescontoPedido(0.0);
			System.out.println(pedidoDto.getValorPedido());
			pedidoService.savePedido(pedido);
			itens.clear();
			pedido = new Pedido();

			return new ModelAndView("redirect:/pedido/listarPed");
		}
	}

	@GetMapping("/listarPed")
	public ModelAndView findAllPedidos() {
		
		ModelAndView mv = new ModelAndView("pedido/listarPed");
		List<Pedido> pedidos = pedidoService.findAll();
		mv.addObject("listarPedidos", pedidos);
		return mv;
	}

	@GetMapping(value = "/listarProdutos")
	public ResponseEntity<List<Produto>> findTodosProdutos() {
		List<Produto> produtos = produtoService.findAll();
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@GetMapping(value = "/listarFornecedores")
	public ResponseEntity<List<Fornecedor>> findTodosFornecedores() {
		List<Fornecedor> fornecedores = fornecedorService.findAll();
		return new ResponseEntity<List<Fornecedor>>(fornecedores, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNomeCliente")
	@ResponseBody // Retorna os dados para o corpo da resposta.
	public ResponseEntity<List<Cliente>> buscarPorNomecliente(@RequestParam(name = "name") String name) {
		List<Cliente> cliente = clienteService.findByNameContaining(name);
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}

	@GetMapping(value = "buscarTodosClientes")
	public ResponseEntity<List<Cliente>> buscarTodosclientes() {
		List<Cliente> cliente = clienteService.findAll();
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorIdCliente")
	public ResponseEntity<Cliente> buscarPorIdCliente(@RequestParam(name = "id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.findClienteEndereco(id);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			clienteDto.fromClienteDto(cliente);
			this.pedidoDto.setClienteDto(clienteDto);
			
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} else {
			// Retorna uma lista vazia se o Optional estiver vazio
			return new ResponseEntity<Cliente>(new Cliente(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "buscarProduto")
	@ResponseBody
	public ResponseEntity<List<Produto>> buscarProduto(@RequestParam(name = "descricao") String descricao) {
		List<Produto> produto = produtoService.findBydescricao(descricao);
		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
	}

	// ======================Add produto na lista de
	// intens===================================================
	@GetMapping("/addListaIten/{id}")
	public ModelAndView addListaIten(@PathVariable(name = "id") Long id) {

		ModelAndView mv = new ModelAndView("redirect:/pedido/pedido");

		Optional<Produto> produtos = produtoService.findById(id);
		Produto produto = produtos.get();

		int controle = 0;
		for (ItemPedido it : itens) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setPrecoIten(produto.getValorSaida());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setSubTotal(item.getQuantidade() * item.getPrecoIten());
			item.setValorTotal(item.getValorTotal() * item.getPrecoIten());
			itens.add(item);

			mv.addObject("listaItens", itens);
		}

		return mv;
	}

	// =========================== Add incrementar quantidade de itens da
	// lista==================================
	@GetMapping("/alterarQuantidadeItens/{id}/{acao}")
	public ModelAndView alterarQuantidadeItens(@PathVariable Long id, @PathVariable Integer acao) {
		ModelAndView mv = new ModelAndView("redirect:/pedido/pedido");
		for (ItemPedido it : itens) {
			if (it.getProduto().getId().equals(id)) {
				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setSubTotal(it.getQuantidade() * it.getPrecoIten());
				} else if (acao == 0) {
					it.setQuantidade(it.getQuantidade() - 1);
					if (it.getQuantidade() <= 1) {
						it.setQuantidade(1);
					}
					it.setSubTotal(it.getQuantidade() * it.getPrecoIten());
				}
				break;
			}
		}
		mv.addObject("listaItens", itens);
		return mv;
	}

	// ====================================Remover item da
	// lista======================
	@GetMapping("/removerItem/{id}")
	public ModelAndView removerItem(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/pedido/pedido");
		for (ItemPedido item : itens) {
			if (item.getProduto().getId().equals(id)) {
				itens.remove(item);
				break;
			}
		}
		mv.addObject("listaItens", itens);
		return mv;
	}

	// =======================================================================================================
	@GetMapping(value = "buscarProdutoId")
	@ResponseBody
	public ResponseEntity<List<Produto>> buscarProdutoPorId(@RequestParam(name = "id") Long id) {
		Optional<Produto> produtoOptional = produtoService.findById(id);
		if (produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			List<Produto> produtoList = new ArrayList<Produto>();
			produtoList.add(produto);
			return new ResponseEntity<List<Produto>>(produtoList, HttpStatus.OK);
		}
		return new ResponseEntity<List<Produto>>(HttpStatus.BAD_REQUEST);
	}

	public ModelAndView retornaErroPedido(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pedido/listar");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
