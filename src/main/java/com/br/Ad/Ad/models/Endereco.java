package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "uf",nullable = false, length = 2)
	private String uf;
	
	@Column(name = "cidade",nullable = false, length = 255)
	private String localidade;
	
	@Column(name = "bairro",nullable = false, length = 255)
	private String bairro;
	
	@Column(name = "cep",nullable = false, length = 30)
	private String cep;
	
	@Column(name = "logradouro", nullable = false, length = 255)
	private String logradouro;
	
	@Column(name = "complemento",nullable = true, length = 255)
	private String complemento;
	
	@Column(name = "numero", nullable = true, length = 10)
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private EnderecoEnum enderecoEnum;
	
	@JsonIgnore
	@ManyToMany
	private Set<Cliente> cliente;
		
	@JsonIgnore
	@ManyToMany
	private List<Fornecedor> fornecedor;
	
	@JsonIgnore
	@OneToOne
	private Pedido pedido;
	
	public Endereco() {
		
	}

	public Endereco(Long id, String uf, String localidade, String bairro, 
			String cep, String logradouro, String complemento,
			String numero, Set<Cliente> cliente, List<Fornecedor> fornecedor,
			Pedido pedido, EnderecoEnum enderecoEnum, ContatoEnum contatoEnum) {
		this.setId(id);
		this.setUf(uf);
		this.setLocalidade(localidade);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setLogradouro(logradouro);
		this.setComplemento(complemento);
		this.setNumero(numero);
		this.setCliente(cliente);
		this.setFornecedor(fornecedor);
		this.setPedido(pedido);
		this.setEnderecoEnum(enderecoEnum);
	
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
	
	public EnderecoEnum getEnderecoEnum() {
		return enderecoEnum;
	}

	public void setEnderecoEnum(EnderecoEnum enderecoEnum) {
		this.enderecoEnum = enderecoEnum;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@PreUpdate
	public void atualizaInsertEndereco() {
		for(Fornecedor forne : this.getFornecedor()) {
			forne.setEndereco(null);
		}
	}
}
