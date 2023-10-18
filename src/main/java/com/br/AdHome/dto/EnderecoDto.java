package com.br.AdHome.dto;

import com.br.AdHome.models.Endereco;
import com.br.AdHome.models.EnderecoEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoDto {

	private Long id;

	@NotBlank
	@Size(max = 2)
	private String uf;
	
	@NotBlank
	@Size(max = 255)
	private String localidade;
	
	@NotBlank
	@Size(max = 255)
	private String bairro;
	
	@NotBlank(message = "cep é obrigatório")
	@Pattern(regexp = "\\d{8}", message = "cep deve ter 8 dígitos numéricos")
	private String cep;
	
	@NotBlank
	@Size(max = 255)
	private String logradouro;
	
	@Size(max = 255)
	private String complemento;
	
	@Size(max = 10)
	private String numero;
	
	private EnderecoEnum enderecoEnum;

	public Long getId() {
		return id;
	}

	public void setEnderecoId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		if (this.cep != null) {
			return this.cep.replaceAll("[^0-9]", "");
		}
		return null;
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

	public EnderecoEnum getEnderecoEnum() {
		return enderecoEnum;
	}

	public void setEnderecoEnum(EnderecoEnum enderecoEnum) {
		this.enderecoEnum = enderecoEnum;
	}

	public Endereco toEndereco() {// Passsando os Obj para classe endereço sem parametros no metodo
		Endereco endereco = new Endereco();
		endereco.setId(this.id);
		endereco.setUf(this.uf);
		endereco.setLocalidade(this.localidade);
		endereco.setBairro(this.bairro);
		endereco.setCep(this.cep);
		endereco.setLogradouro(this.logradouro);
		endereco.setComplemento(this.complemento);
		endereco.setNumero(this.numero);
		endereco.setEnderecoEnum(enderecoEnum);
		return endereco;
	}

	public Endereco toEndereco(Endereco endereco) { // Passsando os Obj para classe endereço com parametros no metodo

			endereco.setId(this.id);
			endereco.setUf(this.uf);
			endereco.setLocalidade(this.localidade);
			endereco.setBairro(this.bairro);
			endereco.setCep(this.cep);
			endereco.setLogradouro(this.logradouro);
			endereco.setComplemento(this.complemento);
			endereco.setNumero(this.numero);
			endereco.setEnderecoEnum(enderecoEnum);
			return endereco;
	}

	public void fromEndereco(Endereco endereco) { // Pegando da classe Endereco seus atributos.
		this.id = endereco.getId();
		this.uf = endereco.getUf();
		this.localidade = endereco.getLocalidade();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.complemento = endereco.getComplemento();
		this.numero = endereco.getNumero();
		this.enderecoEnum = endereco.getEnderecoEnum();
	}
}
