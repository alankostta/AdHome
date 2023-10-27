package com.br.AdHome.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe a Uf")
	@SerializedName("uf")
	@Column(name = "uf",nullable = false, length = 2)
	private String uf;
	
	@NotBlank(message = "Informe a cidade")
	@SerializedName("localidade")
	@Column(name = "cidade",nullable = false, length = 255)
	private String localidade;
	
	@NotBlank(message = "Informe o bairro")
	@SerializedName("bairro")
	@Column(name = "bairro",nullable = false, length = 255)
	private String bairro;
	
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Insira um cep valido!")
	@NotBlank(message = "Informe o cep")
	@SerializedName("cep")
	@Column(name = "cep",nullable = false, length = 30)
	private String cep;
	
	@NotBlank(message = "Informe a rua ou av...")
	@SerializedName("logradouro")
	@Column(name = "logradouro", nullable = false, length = 255)
	private String logradouro;
	
	@SerializedName("complemento")
	@Column(name = "complemento",nullable = true, length = 255)
	private String complemento;
	
	@SerializedName("numero")
	@Column(name = "numero", nullable = true, length = 10)
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private EnderecoEnum enderecoEnum;
	
	//@JsonIgnore
	@JsonBackReference
	@ManyToMany(mappedBy = "endereco")
	private Set<Cliente> cliente;
		
	@JsonIgnore
	@ManyToMany(mappedBy = "endereco")
	private List<Fornecedor> fornecedor;
	
	@JsonIgnore
	@OneToOne
	private Pedido pedido;

	public Endereco() {
		super();
	}

	public Endereco(Long id, String uf, String localidade, String bairro, String cep, String logradouro,
			String complemento, String numero, EnderecoEnum enderecoEnum, Set<Cliente> cliente,
			List<Fornecedor> fornecedor, Pedido pedido) {
		super();
		this.id = id;
		this.uf = uf;
		this.localidade = localidade;
		this.bairro = bairro;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.numero = numero;
		this.enderecoEnum = enderecoEnum;
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public EnderecoEnum getEnderecoEnum() {
		return enderecoEnum;
	}

	public void setEnderecoEnum(EnderecoEnum enderecoEnum) {
		this.enderecoEnum = enderecoEnum;
	}

	public Set<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(Set<Cliente> cliente) {
		this.cliente = cliente;
	}

	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
