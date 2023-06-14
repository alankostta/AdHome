package com.br.AdHome.AdHome.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.br.AdHome.AdHome.models.AdUser;

public class AduserDto {
	
	private BigInteger userId;
	
	private String nomeUser;
	
	private String emailUser;

	private String password;

	private String nome;
	
	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public AdUser toAdUser() {
		AdUser user = new AdUser();
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		return user;
	}
	public AdUser toAdUser(AdUser user) {
		if(getUserId()== null) {
			return toAdUser();
		}else {
		user.setUserId(this.userId.longValue());
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		return user;
		}
	}
	public void fromAdUser(AdUser user) {
		this.userId = BigInteger.valueOf(user.getUserId());
		this.nome = user.getNome();
		this.nomeUser = user.getNomeUser();
		this.emailUser = user.getEmailUser();
		this.password = user.getPassword();
	}
	public List<AduserDto> listUser(List<AdUser> user){
		List<AduserDto> adUserDtoList = new ArrayList<>();
	    for (AdUser adUser : user) {
	        AduserDto dto = new AduserDto();
	        dto.fromAdUser(adUser);
	        adUserDtoList.add(dto);
	    }
	    return adUserDtoList;
	}
}
