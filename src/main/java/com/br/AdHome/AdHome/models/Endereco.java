package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable{
	private static final long serialVersionUID = 3L; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_id",nullable = false, length = 10, unique = true)
	private Long enderecoId;
	@Column(name = "UF",nullable = false, length = 2)
	private String uf;
	@Column(name = "cidade",nullable = false, length = 255)
	private String cidade;
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = true)
	private Set<Cliente> cliente;
		
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Fornecedor_Endereco",
			joinColumns = @JoinColumn(name =  "endereco_fk",nullable = true),
			inverseJoinColumns = @JoinColumn(name = "fornecedor_fk",nullable = true))
	private Set<Fornecedor> fornecedor;
	
	public Endereco() {
		
	}

	public Endereco(String uf, String cidade, String bairro, String cep, String logradouro, String complemento,
			String numero, Set<Cliente> cliente, Set<Fornecedor> fornecedor) {
		super();
		this.setUf(uf);
		this.setCidade(cidade);
		this.setBairro(bairro);
		this.setCep(cep);
		this.setLogradouro(logradouro);
		this.setComplemento(complemento);
		this.setNumero(numero);
		this.setCliente(cliente);
		this.setFornecedor(fornecedor);
	
	}
	@Override
	public int hashCode() {
		return Objects.hash(enderecoId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(enderecoId, other.enderecoId);
				
	}
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
	public Long getEnderecoId() {
		return enderecoId;
	}
	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}
	public Set<Cliente> getCliente() {
		return cliente;
	}
	public void setCliente(Set<Cliente> cliente) {
		this.cliente = cliente;
	}
	public Set<Fornecedor> getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Set<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}
	@PreUpdate
	public void atualizaInsertEndereco() {
		for(Fornecedor forne : this.getFornecedor()) {
			forne.setEndereco(null);
		}
	}
	@Override
	public String toString() {
		return "Endereco [enderecoId=" + enderecoId + ", uf=" + uf + ", cidade=" + cidade + ", bairro=" + bairro
				+ ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", numero=" + numero
				+ ", cliente=" + cliente + ", fornecedor=" + fornecedor + "]";
	}	
}
