package com.br.AdHome.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.AdHome.models.Despesa;
import com.br.AdHome.models.PedidoTipoPagamentoEnum;
import com.br.AdHome.repositories.CategoriaRepository;
import com.br.AdHome.repositories.DespesasRepository;
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
		Despesa despesas = new Despesa();
		mv.addObject("listaCategoria", categoriaRepository.findAll());
		mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
		mv.addObject("despesas", despesas);
		mv.addObject("listaDespesas", despesasRepository.findAll());
		return mv;
	}

	@GetMapping("/controleCaixa/listarDespesas")
	public ResponseEntity<List<Despesa>> listarDespesas() {
		List<Despesa> despesas = despesasRepository.findAll();
		return ResponseEntity.ok(despesas);
	}

	@PostMapping("/controleCaixa/salvarDespesas")
	public ModelAndView saveDespesas(@Valid Despesa despesa, BindingResult errors, RedirectAttributes attr) {

		if (errors.hasErrors()) {
			var mv = new ModelAndView("/controleCaixa/contas-a-pagar-e-receber");
			Despesa despesas = new Despesa();
			mv.addObject("listaCategoria", categoriaRepository.findAll());
			mv.addObject("listaPagamento", PedidoTipoPagamentoEnum.values());
			mv.addObject("despesas", despesas);
			mv.addObject("errors", errors);
			mv.addObject("listaDespesas", despesasRepository.findAll());
			mv.addObject("fail", "ERRO AO TENTAR SALVAR DÉBITO!");
			return mv;
			
		} else {
			var mv = new ModelAndView("redirect:/controleCaixa/contas-a-pagar-e-receber");
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dataFormatada = despesa.getDtCadastro().format(formato);
			despesa.setDtCadastro(LocalDate.parse(dataFormatada, formato));
			dataFormatada = despesa.getDtVencimento().format(formato);
			despesa.setDtVencimento(LocalDate.parse(dataFormatada, formato));
			despesasRepository.save(despesa);
			mv.addObject("success", "DÉBITO SALVO COM SUCESSO!");
			return mv;
			
		}
	}
}
