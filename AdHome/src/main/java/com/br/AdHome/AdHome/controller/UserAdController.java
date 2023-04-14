package com.br.AdHome.AdHome.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.AdHome.configs.UserDetailsServiceImpl;
import com.br.AdHome.AdHome.dto.AduserDto;
import com.br.AdHome.AdHome.models.AdUser;
import com.br.AdHome.AdHome.models.RoleModel;
import com.br.AdHome.AdHome.models.RoleName;

@Controller
@RestController
public class UserAdController {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/cadastrar-user")
	public ModelAndView exibirCadastrarUser(AduserDto aduserDto) {
		var mv = new ModelAndView("/cadastrar-user");
		return mv;
	}
	@GetMapping("/usuarios")
	public ResponseEntity<List<AdUser>> listarUsuarios() {
	    List<AdUser> usuarios = userDetailsServiceImpl.findAllUser();
	    return ResponseEntity.ok(usuarios);
	}
	@PostMapping("/cadastrar-user")
	public ModelAndView saveUser(@Valid AduserDto aduserDto, BindingResult aduserDtoResult) {
		ModelAndView mv = new ModelAndView("/cadastrar-user");

		if (aduserDtoResult.hasErrors()) {
			this.retornaErroUser("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		}

		AdUser user = aduserDto.toAdUser();
		String encodedPassword = passwordEncoder.encode(aduserDto.getPassword());
	    user.setPassword(encodedPassword);
		userDetailsServiceImpl.saveUser(user);

		return new ModelAndView("redirect:/cadastrar-user");
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

		return new ModelAndView("redirect:/cadastrar-user");
	}

	private ModelAndView retornaErroUser(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/cadastrar-user");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
