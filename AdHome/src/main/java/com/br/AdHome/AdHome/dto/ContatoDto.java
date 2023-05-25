package com.br.AdHome.AdHome.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.models.TipoFoneEnum;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ContatoDto {

	@NotBlank
	@Size(max = 30)
	private String telefone;
	@Size(max = 150)
	@Email
	private String email;

	private TipoFoneEnum tipoFone;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoFoneEnum getTipoFone() {
		return tipoFone;
	}

	public void setTipoFone(TipoFoneEnum tipoFone) {
		this.tipoFone = tipoFone;
	}

	public Contato toContato() {
		Contato contato = new Contato();
		contato.setEmail(this.email);
		contato.setTelefone(this.telefone);
		contato.setTipoFone(tipoFone);
		return contato;
	}

	public Contato toContato(Contato contato) {
		contato = new Contato();
		contato.setEmail(this.email);
		contato.setTelefone(this.telefone);
		contato.setTipoFone(tipoFone);
		return contato;
	}

	public void fromContato(Contato contato) {
		this.email = contato.getEmail();
		this.telefone = contato.getTelefone();
		this.tipoFone = contato.getTipoFone();

	}
}
