package com.br.AdHome.AdHome.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.br.AdHome.AdHome.models.Endereco;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 * DTO(Data Transfer Object)
 */

public class EnderecoDto {
	@NotBlank
	@Size(max = 2)
	private String uf;
	@NotBlank
	@Size(max = 255)
	private String cidade;
	@NotBlank
	@Size(max = 255)
	private String bairro;
	@NotBlank
	@Size(max = 30)
	private String cep;
	@NotBlank
	@Size(max = 255)
	private String logradouro;

	@Size(max = 255)
	private String complemento;

	@Size(max = 10)
	private String numero;
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Endereco toEndereco() {
		Endereco endereco = new Endereco();
		endereco.setUf(this.uf);
		endereco.setCidade(this.cidade);
		endereco.setBairro(this.bairro);
		endereco.setCep(this.cep);
		endereco.setLogradouro(this.logradouro);
		endereco.setComplemento(this.complemento);
		endereco.setNumero(this.numero);
		return endereco;
	}
	public Endereco toEndereco(Endereco endereco) {
		endereco.setUf(this.uf);
		endereco.setCidade(this.cidade);
		endereco.setBairro(this.bairro);
		endereco.setCep(this.cep);
		endereco.setLogradouro(this.logradouro);
		endereco.setComplemento(this.complemento);
		endereco.setNumero(this.numero);
		return endereco;
	}
	public void fromEndereco(Endereco endereco) {
		this.uf = endereco.getUf();
		this.cidade = endereco.getCidade();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.complemento = endereco.getComplemento();
		this.numero = endereco.getNumero();
	}

}
