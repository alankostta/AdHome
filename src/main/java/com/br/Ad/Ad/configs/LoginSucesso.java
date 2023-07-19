package com.br.Ad.Ad.configs;

import java.io.IOException;
import java.util.Optional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.br.Ad.Ad.models.AdUser;
import com.br.Ad.Ad.models.RoleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoginSucesso extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException{
		String login = authentication.name();
		Optional<AdUser> adUser = userDetailsServiceImpl.findByUserName(login);
		if(adUser.isPresent()) {
		String redirectURL = "";
		if(temAutorizacao(adUser,"ROLE_ADMIN")) {
			redirectURL = "/index";
		 	}else {
		 		redirectURL = "/login";
		 	}
		response.sendRedirect(redirectURL);
		}
		
	}
	/*
	 * Metodo que verifica qual o tipo de role do usuario no sistema
	 */
	private boolean temAutorizacao(Optional<AdUser> user, String role) {
		AdUser us = user.get();
		for(RoleModel ro: us.getRoles()) {
			if(ro.getRoleName().equals(role)) {
				return true;
			}
		}
		return false;
	}
}
