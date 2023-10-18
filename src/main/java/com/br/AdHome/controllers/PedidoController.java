package com.br.AdHome.controllers;

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
import com.br.AdHome.configs.UserDetailsServiceImpl;
import com.br.AdHome.models.BandeiraCartaoEnum;
import com.br.AdHome.models.Cliente;
import com.br.AdHome.models.Fornecedor;
import com.br.AdHome.models.Item;
import com.br.AdHome.models.Pedido;
import com.br.AdHome.models.PedidoStatusEnum;
import com.br.AdHome.models.PedidoTipoPagamentoEnum;
import com.br.AdHome.models.Produto;
import com.br.AdHome.services.ClienteService;
import com.br.AdHome.services.FornecedorService;
import com.br.AdHome.services.PedidoService;
import com.br.AdHome.services.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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

	private List<Item> itens = new ArrayList<Item>();
	private Pedido pedido = new Pedido();
	//private Cliente cliente = new Cliente();
	
	private void calcularTotal() {
		this.pedido.setValorPedido(0.0);
		for (Item i : itens) {
			pedido.setValorPedido(pedido.getValorPedido() + i.getSubTotal());
		}	
	}

	@GetMapping("/pedido")
	public ModelAndView exibirPedido() {

		ModelAndView mv = new ModelAndView("pedido/pedido");
		calcularTotal();
		mv.addObject("listaStatus", PedidoStatusEnum.values());
		mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
		mv.addObject("listaCartao", BandeiraCartaoEnum.values());
		mv.addObject("pedido", this.pedido);
		mv.addObject("listaItens", itens);
		mv.addObject("users", userDetailsServiceImpl.findAllUser());
		mv.addObject("produtos", produtoService.findAll());

		return mv;
	}

	@GetMapping("/cancelar")
	public ModelAndView cancelarPedido() {

		ModelAndView mv = new ModelAndView("redirect:/pedido/pedido");
		pedido = new Pedido();
		itens.clear();
		mv.addObject("listaStatus", PedidoStatusEnum.values());
		mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
		mv.addObject("listaCartao", BandeiraCartaoEnum.values());
		mv.addObject("pedido", pedido);
		mv.addObject("listaItens", itens);
		mv.addObject("users", userDetailsServiceImpl.findAllUser());
		mv.addObject("produtos", produtoService.findAll());

		return mv;
	}
	@Transactional
	@PostMapping("/aplicar")
	public ModelAndView savePedido(@Valid Pedido pedido, BindingResult resultPedido) {
		
		ModelAndView mv = new ModelAndView("/pedido/pedido");

		if (resultPedido.hasErrors()) {
			this.retornaErroPedido("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		} else {
			
			LocalDateTime dataHoraAtual = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dataHoraFormatada = dataHoraAtual.format(formatter);
			LocalDateTime dataHoraConvertida = LocalDateTime.parse(dataHoraFormatada, formatter);
			System.out.println(pedido.getValorPedido());
			this.pedido.setItens(itens);
			
			
			for (Item it : itens) {
				it.setPedido(pedido);
			}
			pedido.setValorPedido(this.pedido.getValorPedido());
			Calendar cal = Calendar.getInstance();
			pedido.setAnoRef(cal.get(Calendar.YEAR));
			pedido.setDataAlteraPedido(dataHoraConvertida);
			pedido.setDescontoPedido(0.0);
			
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
		for (Item it : itens) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				controle = 1;
				break;
			}
		}
		if (controle == 0) {
			Item item = new Item();
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
		for (Item it : itens) {
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
		for (Item item : itens) {
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
