package com.br.Ad.Ad.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.br.Ad.Ad.configs.UserDetailsServiceImpl;
import com.br.Ad.Ad.dto.AduserDto;
import com.br.Ad.Ad.dto.ClienteDto;
import com.br.Ad.Ad.dto.EnderecoDto;
import com.br.Ad.Ad.dto.ItemPedidoDto;
import com.br.Ad.Ad.dto.PedidoDto;
import com.br.Ad.Ad.dto.ProdutoDto;
import com.br.Ad.Ad.models.AdUser;
import com.br.Ad.Ad.models.BandeiraCartao;
import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.models.ItemPedido;
import com.br.Ad.Ad.models.Pedido;
import com.br.Ad.Ad.models.PedidoEnumStatus;
import com.br.Ad.Ad.models.PedidoEnumTipoPagamento;
import com.br.Ad.Ad.models.Produto;
import com.br.Ad.Ad.services.ClienteService;
import com.br.Ad.Ad.services.EnderecoService;
import com.br.Ad.Ad.services.ItemPedidoService;
import com.br.Ad.Ad.services.PedidoService;
import com.br.Ad.Ad.services.ProdutoService;
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
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/pedido")
	public ModelAndView exibirPedido(
			ProdutoDto produtoDto, PedidoDto pedidoDto, 
			ClienteDto clienteDto, ItemPedidoDto itemPedidoDto,
			AduserDto aduserDto, EnderecoDto enderecoDto) {
		
		var mv = new ModelAndView("pedido/pedido");
		mv.addObject("listaStatus", PedidoEnumStatus.values());
		mv.addObject("listaPagamento", PedidoEnumTipoPagamento.values());
		mv.addObject("listaCartao", BandeiraCartao.values());
		List<AdUser> user = userDetailsServiceImpl.findAllUser();
		mv.addObject("listaAduserDto",aduserDto.listUser(user));
		return mv;
	}
	// Criando os metodos getPost onde irá receber as requisições
	// que serão persistidas no banco
	@PostMapping("")
	public ModelAndView savePedido(@Valid PedidoDto pedidoDto, BindingResult resultPedido) {

		ModelAndView mv = new ModelAndView("pedido/pedido");

		if ( resultPedido.hasErrors() ) {
			this.retornaErroPedido("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			Pedido pedido = pedidoDto.toPedido();
			Calendar cal = Calendar.getInstance();
			pedido.setAnoRef(cal.get(Calendar.YEAR));
			pedido.setDataAlteraPedido(LocalDateTime.now());
			pedido.setItens(pedido.getItens());
			pedidoService.savePedido(pedido);

			return new ModelAndView("redirect:/pedido/listarPed");
		}
	}
	@GetMapping("/listarPed")
	public ModelAndView findAllPedidos() {
		var mv = new ModelAndView("pedido/listarPed");
		List<Pedido> pedidos = pedidoService.findAll();
		mv.addObject("listaPedidos", pedidos);
		return mv;
	}

	@GetMapping(value = "buscarPorNomeCliente")
	@ResponseBody // Retorna os dados para o corpo da resposta.
	public ResponseEntity<List<Cliente>> buscarPorNomecliente(@RequestParam(name = "name") String name) {
		List<Cliente> cliente = clienteService.findByNameContaining(name);
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorIdCliente")
	@ResponseBody
	public ResponseEntity<List<Object[]>> buscarPorIdCliente(@RequestParam(name = "id") Long id) {
		List<Object[]> clientes = clienteService.findClienteEndereco(id);
		return new ResponseEntity<List<Object[]>>(clientes, HttpStatus.OK);
	}

	@GetMapping(value = "buscarProduto")
	@ResponseBody
	public ResponseEntity<List<Produto>> buscarProduto(@RequestParam(name = "descricao") String descricao) {
		List<Produto> produto = produtoService.findBydescricao(descricao);
		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
	}

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
