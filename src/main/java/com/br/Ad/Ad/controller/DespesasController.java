package com.br.Ad.Ad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.br.Ad.Ad.dto.DespesasDto;
import com.br.Ad.Ad.models.Despesas;
import com.br.Ad.Ad.models.PedidoEnumTipoPagamento;
import com.br.Ad.Ad.repositories.CategoriaRepository;
import com.br.Ad.Ad.repositories.DespesasRepository;
import jakarta.validation.Valid;

@Controller
public class DespesasController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DespesasRepository despesasRepository;
	
	@GetMapping("/controleCaixa/contas-a-pagar-e-receber")
	public ModelAndView exibirContasPagarReceber() {
		var mv = new ModelAndView("/controleCaixa/contas-a-pagar-e-receber");
		DespesasDto despesasDto = new DespesasDto();
		mv.addObject("listaCategoria", categoriaRepository.findAll());
		mv.addObject("listaPagamento", PedidoEnumTipoPagamento.values());
		mv.addObject("despesasDto",despesasDto);
		mv.addObject("listaDespesas", despesasRepository.findAll());
		return mv;
	}
	
	@GetMapping("/controleCaixa/listarDespesas")
	public ResponseEntity<List<Despesas>> listarDespesas(){
		List<Despesas> despesas = despesasRepository.findAll();
		return ResponseEntity.ok(despesas);
	}
	@PostMapping("/controleCaixa/salvarDespesas")
	public ModelAndView saveDespesas(@Valid DespesasDto despesasDto, BindingResult result) {
		
		var mv = new ModelAndView("redirect:/controleCaixa/contas-a-pagar-e-receber");
	
		if(result.hasErrors()) {
			System.out.println(result);
			return mv;
		}
		
		Despesas despesas = despesasDto.toDespesasDto();
		
		despesasRepository.save(despesas);
		
		return mv;
	}
}
