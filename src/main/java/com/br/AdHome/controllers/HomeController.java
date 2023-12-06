package com.br.AdHome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/index")
	public ModelAndView index() {
		var mv = new ModelAndView("/index");
		
		return mv;
	}

	@GetMapping("/modal")
	public ModelAndView exibirModal() {
		var mv = new ModelAndView("/modal");
		return mv;
	}
	@GetMapping("/dashboard")
	public ModelAndView contato() {
		ModelAndView mv = new ModelAndView("/dashboard");
		return mv;
	}
	@GetMapping("/teste")
	public ModelAndView teste() {
		ModelAndView mv = new ModelAndView("/teste");
		return mv;
	}
}
