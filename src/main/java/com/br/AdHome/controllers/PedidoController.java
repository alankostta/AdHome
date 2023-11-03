package com.br.AdHome.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.AdHome.configs.UserDetailsServiceImpl;
import com.br.AdHome.models.BandeiraCartaoEnum;
import com.br.AdHome.models.Cliente;
import com.br.AdHome.models.Fornecedor;
import com.br.AdHome.models.Item;
import com.br.AdHome.models.Pedido;
import com.br.AdHome.models.PedidoStatusEnum;
import com.br.AdHome.models.PedidoTipoPagamentoEnum;
import com.br.AdHome.models.Produto;
import com.br.AdHome.repositories.PedidoRepository;
import com.br.AdHome.services.ClienteService;
import com.br.AdHome.services.FornecedorService;
import com.br.AdHome.services.ItemService;
import com.br.AdHome.services.PedidoService;
import com.br.AdHome.services.ProdutoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	PedidoRepository pedidoRespository;

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
	
	@Autowired
	private ItemService itemService;

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
	
	@GetMapping("/exibirPedido/{id}")
	public ModelAndView exibirPedido(@PathVariable(value = "id") Long id) {
		
		var mv = new ModelAndView("pedido/exibirPedido");
		Optional<Pedido> pedidoOptional = pedidoService.findById(id);
		
		if (!pedidoOptional.isPresent()) {
			mv.addObject("listaStatus", PedidoStatusEnum.values());
			mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
			mv.addObject("listaCartao", BandeiraCartaoEnum.values());
			mv.addObject("users", userDetailsServiceImpl.findAllUser());
			mv.addObject("produtos", produtoService.findAll());
			return mv;
		} else {
			Pedido pedido = pedidoOptional.get();
			mv.addObject("pedido", pedido);
			mv.addObject("listaStatus", PedidoStatusEnum.values());
			mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
			mv.addObject("listaCartao", BandeiraCartaoEnum.values());
			mv.addObject("users", userDetailsServiceImpl.findAllUser());
			mv.addObject("produtos", produtoService.findAll());
			return mv;
		}
	}
	@GetMapping("/editarPedido/{id}/editar")
	public ModelAndView upPedido(@PathVariable(value = "id") Long id, Pedido pedido) {

		ModelAndView mv = new ModelAndView("pedido/editarPedido");
		mv.addObject("pedido", pedido);
		mv.addObject("listaStatus", PedidoStatusEnum.values());
		mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
		mv.addObject("listaCartao", BandeiraCartaoEnum.values());
		mv.addObject("users", userDetailsServiceImpl.findAllUser());
		mv.addObject("produtos", produtoService.findAll());

		Optional<Pedido> pedidoOptional = pedidoService.findById(id);
		

		if (pedidoOptional.isPresent()) {
			Pedido pedi = pedidoOptional.get();
			mv.addObject("id", pedi.getId());
			mv.addObject("pedido", pedi);
			mv.addObject("success", "PEDIDO ALTERADO [* " + id + " *] COM SUCESSO!");
			
			return mv;
		} else {
			mv.addObject("fail", "ERRO AO BUSCAR: PEDIDO COM INSCRIÇÃO [* " + id + " *]");
			return mv;
		}
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
	@GetMapping("/pedido/{id}/excluir")
	public ModelAndView deletePedido(@PathVariable(value = "id") Long id, RedirectAttributes attr) {
	    ModelAndView mv = new ModelAndView("redirect:/pedido/listarPed");
	    Optional<Pedido> pedidoOptional = pedidoService.findById(id);

	    if (!pedidoOptional.isPresent()) {
	        attr.addFlashAttribute("fail", "ERRO AO EXCLUIR: PEDIDO COM ID [" + id + "] NÃO FOI ENCONTRADO NO BANCO!");
	        return mv;
	    } else {
	        Pedido pedido = pedidoOptional.get();

	        // Verifique se existem itens de pedido associados a este pedido
	        List<Item> itensPedido = itemService.findByPedido(pedido);

	        if (!itensPedido.isEmpty()) {
	            // Exclua todos os itens de pedido associados a este pedido
	        	itemService.deleteItens(pedido);
	        	pedido.setUser(null);
	        }

	        // Agora, você pode excluir o pedido
	        pedidoService.deletePedido(pedido);

	        attr.addFlashAttribute("success", "PEDIDO COM ID [" + id + "] EXCLUÍDO COM SUCESSO!");

	        return mv;
	    }
	}


	@Transactional
	@PostMapping("/aplicar")
	public ModelAndView savePedido(@Valid Pedido pedido, BindingResult errors, RedirectAttributes attr) {
		
		ModelAndView mv = new ModelAndView("/pedido/pedido");

		if (errors.hasErrors()) {
			pedido = new Pedido();
			itens.clear();
			mv.addObject("listaStatus", PedidoStatusEnum.values());
			mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
			mv.addObject("listaCartao", BandeiraCartaoEnum.values());
			mv.addObject("listaItens", itens);
			mv.addObject("users", userDetailsServiceImpl.findAllUser());
			mv.addObject("produtos", produtoService.findAll());
			mv.addObject("errors", errors);
			mv.addObject("fail", "ERRO AO TENTAR SALVAR PEDIDO! POR FAVOR VERIFIQUE OS CAMPOS OBRIGATORIOS");
			return mv;
		} else {

			LocalDateTime dataHoraAtual = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dataHoraFormatada = dataHoraAtual.format(formatter);
			LocalDateTime dataHoraConvertida = LocalDateTime.parse(dataHoraFormatada, formatter);
			
			
			for (Item it : itens) {
				it.setPedido(pedido);
				pedido.getItens().add(it);
			}
			pedido.setValorPedido(this.pedido.getValorPedido());
			Calendar cal = Calendar.getInstance();
			pedido.setAnoRef(cal.get(Calendar.YEAR));
			pedido.setDataAlteraPedido(dataHoraConvertida);
			//pedido.setDescontoPedido(0.0);
			
			pedidoService.savePedido(pedido);
			itens.clear();
			pedido = new Pedido();
			attr.addFlashAttribute("success", "PEDIDO GERADO COM SUCESSO!");
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
			item.setValorTotal(item.getValorTotal() + item.getSubTotal());
			itens.add(item);

			mv.addObject("listaItens", itens);
		}

		return mv;
	}
	@GetMapping("/editarIten/{id}")
	public ModelAndView editarListaIten(@PathVariable(name = "id") Long id, Pedido pedido, BindingResult errors, RedirectAttributes attr) {
		
	    ModelAndView mv = new ModelAndView("redirect:/pedido/liestarPed");
	    List<Item> itensEditado = pedido.getItens();
	    
	    Optional<Produto> produtos = produtoService.findById(id);
	    Produto produto = new Produto();
	    
	    if(produtos.isPresent()) {	    	
	    	produto = produtos.get();
	    }else {
	    	mv.addObject("errors", errors);
			mv.addObject("fail", "ERRO AO TENTAR EDITAR PEDIDO! PRODUTO DE ID: "+id+" NÃO ENCONTRADO" );
	    	return mv;
	    }
	    
	    // Verifique se o item já existe na lista
	    boolean itemExistente = false;
	    
	    for (Item it : itensEditado) {
	        if (it.getProduto().getId().equals(produto.getId())) {
	            it.setQuantidade(it.getQuantidade() + 1);
	            it.setSubTotal(it.getQuantidade() * it.getPrecoIten());
	            itemExistente = true;
	            break;
	        }
	    }
	    
	    if (!itemExistente) {
	        // O item não existe na lista, adicione um novo item
	        Item newItem = new Item();
	        newItem.setProduto(produto);
	        newItem.setPrecoIten(produto.getValorSaida());
	        newItem.setQuantidade(1);
	        newItem.setSubTotal(newItem.getQuantidade() * newItem.getPrecoIten());
	        newItem.setValorTotal(newItem.getSubTotal());

	        itensEditado.add(newItem);
	    }
	    
	    mv.addObject("listaItensEditado", itensEditado);
	    
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
	//===============================================DATATABLES==================================================
	@GetMapping("/table")
	public String showTable() {	
		return "pedido/pedido-table";
	}
	//@GetMapping("/datatables")
	@GetMapping(value = "datatables")
	@ResponseBody
	public ResponseEntity<?> datatables(HttpServletRequest request){
		Map<String, Object> data = new PedidoService().execute(this.pedidoRespository, request);
		return ResponseEntity.ok(data);
	}
	@GetMapping(value = "todosPedidos")
	@ResponseBody
	public ResponseEntity<List<Pedido>> todosPedidos() {
		List<Pedido> pedidos = pedidoService.findAll();
		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}
}
