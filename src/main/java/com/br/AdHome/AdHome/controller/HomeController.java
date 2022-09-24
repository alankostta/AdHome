package com.br.AdHome.AdHome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.AdHome.dto.FornecedorDto;
import com.br.AdHome.AdHome.dto.PedidoDto;
import com.br.AdHome.AdHome.dto.ProdutoDto;

@Controller
public class HomeController {
	@GetMapping("/index")
	public ModelAndView index() {
		var mv = new ModelAndView("/index");
		return mv;
	}
	@GetMapping("/login")
	public ModelAndView exibirLoguin(ProdutoDto produtoDto, PedidoDto pedidoDto, FornecedorDto fornecedorDto) {
		var mv = new ModelAndView("/login");
		return mv;
	}
	@GetMapping("/modal")
	public ModelAndView exibirModal() {
		var mv = new ModelAndView("/modal");
		return mv;
	}
}
