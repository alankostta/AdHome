package com.br.AdHome.AdHome.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.AdHome.configs.UserDetailsServiceImpl;
import com.br.AdHome.AdHome.dto.UserDto;
import com.br.AdHome.AdHome.models.AdUser;
import com.br.AdHome.AdHome.models.RoleModel;
import com.br.AdHome.AdHome.models.RoleName;

@Controller
@RestController
public class UserAdController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@GetMapping("/cadastrar-user")
	public ModelAndView exibirCadastrarUser(UserDto nome,UserDto nomeUser, UserDto emailUser, UserDto password) {
		var mv = new ModelAndView("/cadastrar-user");
		return mv;
	}
	@PostMapping("/cadastrar-user")
	public ModelAndView saveUser(@Valid UserDto nome,BindingResult nomeResult,UserDto userDto,
			BindingResult userResult, UserDto emailUser,BindingResult emailResult,
			UserDto password, BindingResult passwordResult) {
		
		ModelAndView mv = new ModelAndView("/cadastrar-user");
		
		if(nomeResult.hasErrors() && userResult.hasErrors() && emailResult.hasErrors()&& passwordResult.hasErrors()) {
			this.retornaErroUser("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		}
		
		AdUser user = userDto.toAdUser();
		RoleModel role = new RoleModel();
		role.setRoleName(RoleName.ROLE_ADMIN);
		List<RoleModel> listRole = new ArrayList<>();
		listRole.add(role);
		user.setRoles(listRole);
		userDetailsServiceImpl.saveUser(user);
		return new ModelAndView("redirect:/cadastrar-user");
		
	}
	private ModelAndView retornaErroUser(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/cadastrar-user");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
