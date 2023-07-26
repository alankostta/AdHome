package com.br.Ad.Ad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.Ad.Ad.configs.UserDetailsServiceImpl;
import com.br.Ad.Ad.dto.AduserDto;
import com.br.Ad.Ad.models.AdUser;
import com.br.Ad.Ad.models.RoleModel;
import com.br.Ad.Ad.models.RoleName;
import com.br.Ad.Ad.services.RolesModelService;

import jakarta.validation.Valid;

@Controller
public class UserAdController {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	RolesModelService roleService;
	
	
	@GetMapping("/usuario/login")
	public ModelAndView exibirLoguin() {
		var mv = new ModelAndView("usuario/login");
		return mv;
	}
	
	@GetMapping("/usuario/cadastrar-user")
	public ModelAndView exibirCadastrarUser(AduserDto aduserDto) {
		var mv = new ModelAndView("usuario/cadastrar-user");
		return mv;
	}
	@ModelAttribute("roles")
	public List<RoleModel> listaDepartamentos() {
		List<RoleModel> roles = roleService.findAll();
		return roles;
	}
	@GetMapping("/usuario/listarUser")
	public ModelAndView listarUsuarios() {
		var mv = new ModelAndView();
		
	    List<AdUser> usuarios = userDetailsServiceImpl.findAllUser();
	    mv.addObject("usuarios", usuarios);
	    
	    return mv;
	}
	@PostMapping("/usuario/cadastrar-user")
	public ModelAndView saveUser(@Valid AduserDto aduserDto, BindingResult aduserDtoResult) {
		ModelAndView mv = new ModelAndView("usuario/cadastrar-user");

		if (aduserDtoResult.hasErrors()) {
			this.retornaErroUser("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		}

		AdUser user = aduserDto.toAdUser();
		//String encodedPassword = passwordEncoder.encode(aduserDto.getPassword());
	   // user.setPassword(encodedPassword);
		userDetailsServiceImpl.saveUser(user);

		return new ModelAndView("redirect:usuario/listarUser");
	}

	public ModelAndView saveMultipleUsers(int numberOfUsers) {
		
		// Criando a lista de roles
		List<RoleModel> roles = new ArrayList<>();
		RoleModel role = new RoleModel();
		role.setRoleName(RoleName.ROLE_ADMIN);
		roles.add(role);

		// Criando e salvando vários usuários
		for (int i = 0; i < numberOfUsers; i++) {
			AdUser user = new AdUser();
			user.setNome("Nome do usuário " + i);
			user.setEmailUser("emaildoUsuario" + i + "@example.com");
			user.setPassword("senhaDoUsuario" + i);
			user.setRoles(roles);
			userDetailsServiceImpl.saveUser(user);
		}

		return new ModelAndView("redirect:usuario/cadastrar-user");
	}

	private ModelAndView retornaErroUser(String msg) {
		ModelAndView mv = new ModelAndView("redirect:usuario/cadastrar-user");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
