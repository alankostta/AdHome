package com.br.AdHome.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.AdHome.models.Categoria;
import com.br.AdHome.models.Fornecedor;
import com.br.AdHome.models.Produto;
import com.br.AdHome.services.CategoriaService;
import com.br.AdHome.services.FornecedorService;
import com.br.AdHome.services.ProdutoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;
	@Autowired
	FornecedorService fornecedorService;
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("")
	public ModelAndView exibiProdutos(Produto produto, Fornecedor fornecedor, 
			Categoria categoria) {
		var mv = new ModelAndView("produto/produto");
		return mv;
	}
	@PostMapping("")
	public ModelAndView saveProdutos(@Valid Produto produto, BindingResult errors, RedirectAttributes attr) {

		ModelAndView mv = new ModelAndView("produto/produto");

		if (errors.hasErrors()) {
			mv.addObject("errors",errors);
			mv.addObject("fail", "ERRO AO TENTAR CADASTRAR O PRODUTO VERIFIQUE OS CAMPOS COM ALETA!");
			return mv;
		} else {
			
			produto.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			produto.setDataAltera(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
			Calendar cal = Calendar.getInstance();
			produto.setAnoRef(cal.get(Calendar.YEAR));
		
			if (produto.getValorSaida() == null) {
				//produto.setValorSaida(0.0);
				produtoService.salvarProduto(produto);
			}
			else {
				produtoService.salvarProduto(produto);
				
			}
			attr.addFlashAttribute("success", "PRODUTO CADASTRADO COM SUCESSO!");
			return new ModelAndView("redirect:/produto/listarPro");
		}
	}
	@ModelAttribute("categorias")
	public List<Categoria> listaDepartamentos() {
		return categoriaService.findAll();
	}
	@GetMapping("/listarPro")
	public ModelAndView listarProdutos() {

		var mv = new ModelAndView("produto/listarPro");
		Iterable<Produto> produtos = produtoService.findAll();
		mv.addObject("produtos", produtos);
		mv.addObject("mensagem", "PESQUISA REALIZADA COM SUCESSO!");
		return mv;
	}
	@GetMapping("produto/{id}")
	public ModelAndView getOneProduto(@PathVariable(value = "id") Long id) {

		Optional<Produto> produtoOptional = produtoService.findById(id);
		var mv = new ModelAndView("produto/exibirProdu");
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
	@GetMapping("produto/{id}/editar")
	public ModelAndView upProduto(@PathVariable(value = "id") Long id, Produto produto) {

		ModelAndView mv = new ModelAndView("produto/editarProduto");
		Optional<Produto> produtoOptional = this.produtoService.findById(id);
		
		if (produtoOptional.isPresent()) {
			Produto produt = produtoOptional.get();
			mv.addObject("id", produto.getId());
			mv.addObject("mensagem", "CLIENTE ISCRIÇÃO COM " + id + " encontrado!");
			mv.addObject("produto", produt);
			mv.addObject("erro", false);
			
			return mv;
		} else {
			
			return this.retornaErroProduto("ERRO AO EDITAR: Cliente com inscrição (" + id + ")");
		}
	}
	@PostMapping("produto/{id}")
	public ModelAndView updateProduto(@PathVariable(value = "id") Long id, @Valid Produto produto,
			BindingResult resultProduto) {
		
		ModelAndView mv = new ModelAndView("redirect:/produto/produto/{id}");
		
		if (resultProduto.hasErrors()) {
			mv.addObject("id", id);
			this.retornaErroProduto("ERRO AO SALVAR!!! PREENCHA OS CAMPOS NOME, SEXO, DATA DE NASCIMENTO!");
			return mv;
		} 
		 else {
				Optional<Produto> produtoOptional = this.produtoService.findById(id);
				
				if (produtoOptional.isPresent()) {
					Produto prod = produtoOptional.get();
					produto.setDataAltera(LocalDateTime.now(ZoneId.of("UTC")));
					produto.setAnoRef(prod.getAnoRef());
					produto.setDataCadastro(prod.getDataCadastro());
					this.produtoService.salvarProduto(produto);
					mv.addObject("mensagem", "Cliente com inscrição " + id + " editado com sucesso!");
					mv.addObject("erro", false);
					return mv;
			} else {
					return this.retornaErroProduto("ERRO AO SALVAR: Cliente com inscrição ("+id+")");
			}
		}
	}
	@GetMapping("produto/{produtoId}/excluir")
	public ModelAndView deleteProduto(@PathVariable(value = "produtoId") Long id) {
		
		Optional<Produto> produtoOptional = produtoService.findById(id);
		
		if (!produtoOptional.isPresent()) {
			return this.retornaErroProduto("ERRO AO EXCLUIR: Cliente com inscrição ("+id+") não foi encontrado no banco!");
		} else {
				ModelAndView mv = new ModelAndView("redirect:/produto/listarPro");
				produtoService.deleteProduto(produtoOptional.get());
				mv.addObject("mensagem", "INSCRIÇÃO DO CLIENTE" + id + " EXCLUIDO COM SUCESSO!");
				mv.addObject("erro", false);
				return mv;
		}
	}
	@GetMapping(value="buscarPorNomeFornecedor")
	@ResponseBody//Retorna os dados para o corpo da resposta.
	public ResponseEntity<List<Fornecedor>> buscarPorNome(@RequestParam(name="name") String name) {
		List<Fornecedor> fornecedor = fornecedorService.findByName(name);//Execeuta a consulta no banco de dados ao qual está sendo por nome
	
		return new ResponseEntity<List<Fornecedor>>(fornecedor,HttpStatus.OK);//Retorna uma lista de nomes em formato JSON
	}
	//criar um and point para pesquisar o fornecedor por Id
	@GetMapping(value="buscarPorIdFornecedor")
	@ResponseBody
	public ResponseEntity<Fornecedor> buscarPorIdFornecedor(@RequestParam(name="id") Long fornecedorId){//Recebe os dados para consultar
		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(fornecedorId);
		if(fornecedorOptional.isPresent()) {
			Fornecedor fornecedor = fornecedorOptional.get();
			return new ResponseEntity<Fornecedor>(fornecedor,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Fornecedor>(HttpStatus.BAD_REQUEST);
		}
	}
	private ModelAndView retornaErroProduto(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/fornecedor/fornecedor");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
