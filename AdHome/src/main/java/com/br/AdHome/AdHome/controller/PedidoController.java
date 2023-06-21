package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.AdHome.configs.UserDetailsServiceImpl;
import com.br.AdHome.AdHome.dto.AduserDto;
import com.br.AdHome.AdHome.dto.ClienteDto;
import com.br.AdHome.AdHome.dto.EnderecoDto;
import com.br.AdHome.AdHome.dto.ItemPedidoDto;
import com.br.AdHome.AdHome.dto.PedidoDto;
import com.br.AdHome.AdHome.dto.ProdutoDto;
import com.br.AdHome.AdHome.models.AdUser;
import com.br.AdHome.AdHome.models.BandeiraCartao;
import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.models.ItemPedido;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.models.PedidoEnumStatus;
import com.br.AdHome.AdHome.models.PedidoEnumTipoPagamento;
import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.services.ClienteService;
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

	@GetMapping("")
	public String exibirPedido(Model mv) {
		ProdutoDto produtoDto = new ProdutoDto();
		PedidoDto pedidoDto = new PedidoDto();  
		ClienteDto clienteDto = new ClienteDto();
		AduserDto aduserDto = new AduserDto();
		EnderecoDto enderecoDto = new EnderecoDto();
		
		Set<ItemPedidoDto> itemPedidoDto = new HashSet<>();
		
		List<AdUser> user = userDetailsServiceImpl.findAllUser();
		
		mv.addAttribute("listaStatus", PedidoEnumStatus.values());
		mv.addAttribute("listaPagamento", PedidoEnumTipoPagamento.values());
		mv.addAttribute("listaCartao", BandeiraCartao.values());
		mv.addAttribute("listaAduserDto",aduserDto.listUser(user));
		mv.addAttribute("item", itemPedidoDto);
		mv.addAttribute("produtoDto", produtoDto);
		mv.addAttribute("pedidoDto", pedidoDto);
		mv.addAttribute("clienteDto", clienteDto);
		mv.addAttribute("enderecoDto", enderecoDto);
		mv.addAllAttributes(aduserDto.listUser(user));
		
		return "pedido/pedido";
	}
//	@GetMapping("")
//	public ModelAndView exibirPedido(
//			ProdutoDto produtoDto, PedidoDto pedidoDto, 
//			ClienteDto clienteDto,AduserDto aduserDto, EnderecoDto enderecoDto) {
//		List<ItemPedidoDto> itemPedidoDto = new ArrayList<>();
//		var mv = new ModelAndView("pedido/pedido");
//		mv.addObject("listaStatus", PedidoEnumStatus.values());
//		mv.addObject("listaPagamento", PedidoEnumTipoPagamento.values());
//		mv.addObject("listaCartao", BandeiraCartao.values());
//		List<AdUser> user = userDetailsServiceImpl.findAllUser();
//		mv.addObject("listaAduserDto",aduserDto.listUser(user));
//		mv.addObject("item", itemPedidoDto);
//		return mv;
//	}
//	// Criando os metodos getPost onde irá receber as requisições
	// que serão persistidas no banco
	@PostMapping("")
	@ResponseBody
	public ModelAndView savePedido(
	        @Valid ClienteDto clienteDto, BindingResult resultCliente,
	        @Valid PedidoDto pedidoDto, BindingResult resultPedido,
	        @Valid ProdutoDto produtoDto, BindingResult resultProduto,
	        @Valid EnderecoDto enderecoDto, BindingResult resultEnderecoDto,
	        @Valid AduserDto aduserDto, BindingResult resultAduserDto,
	        @Valid ItemPedidoDto itemPedidoDto, BindingResult resultItemPedidoDto) {

	    ModelAndView mv = new ModelAndView("pedido/pedido");

	    if (resultCliente.hasErrors() && resultPedido.hasErrors() &&
	            resultEnderecoDto.hasErrors() && resultProduto.hasErrors()) {

	        this.retornaErroPedido("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
	        return mv;
	    } else {
	        Pedido pedido = pedidoDto.toPedido();
	        Cliente cliente = clienteDto.toCliente();
	        Endereco endereco = enderecoDto.toEndereco();
	        ItemPedido item = itemPedidoDto.toItemPedido();
  
	        Set<ItemPedido> itens = new HashSet<>();
	        itens.add(item);
	        pedido.setCliente(cliente);
	        pedido.setEndereco(endereco);
	        pedido.setItens(itens);
	        pedido.setAnoRef(Calendar.getInstance().get(Calendar.YEAR));
	        pedido.setDataAlteraPedido(LocalDateTime.now());
	        pedidoService.savePedido(pedido);

	        return new ModelAndView("redirect:/pedido");
	    }
	}
	public ModelAndView findAllPedidos() {
		var mv = new ModelAndView("pedido/pedido");
		List<Pedido> pedidos = pedidoService.findAll();
		mv.addObject("listaPedido", pedidos);
		return mv;
	}

	@GetMapping(value = "buscarPorNomeCliente")
	@ResponseBody // Retorna os dados para o corpo da resposta.
	public ResponseEntity<List<Cliente>> buscarPorNomecliente(@RequestParam(name = "name") String name) {
		// List<Fornecedor> fornecedor =
		// fornecedorService.findByName(name.trim().toLowerCase());
		List<Cliente> cliente = clienteService.findByNameContaining(name);// Execeuta a consulta no banco de dados ao qual está sendo por nome
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);// Retorna uma lista de nomes em formato JSON
	}

	@GetMapping(value = "buscarPorIdCliente")
	@ResponseBody
	public ResponseEntity<List<Object[]>> buscarPorIdCliente(@RequestParam(name = "clienteId") Long clienteId) {
		List<Object[]> clientes = clienteService.findClienteEndereco(clienteId);
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
	public ResponseEntity<List<Produto>> buscarProdutoPorId(@RequestParam(name = "produtoId") Long produtoId) {
		Optional<Produto> produtoOptional = produtoService.findById(produtoId);
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
