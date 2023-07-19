package com.br.Ad.Ad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.Ad.Ad.dto.AduserDto;


@Controller
public class HomeController {
	@GetMapping("/index")
	public ModelAndView index() {
		var mv = new ModelAndView("/index");
		
		return mv;
	}
	@GetMapping("/login")
	public ModelAndView exibirLoguin(AduserDto nomeUser, AduserDto emailUser, AduserDto password) {
		var mv = new ModelAndView("/login");
		return mv;
	}
	@GetMapping("/modal")
	public ModelAndView exibirModal() {
		var mv = new ModelAndView("/modal");
		return mv;
	}
	
	@GetMapping("/controleCaixa/contas-a-pagar-e-receber")
	public ModelAndView exibirContasPagarReceber() {
		var mv = new ModelAndView("/controleCaixa/contas-a-pagar-e-receber");
		return mv;
	}
}
