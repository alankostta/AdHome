package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contato_id", nullable = false, length = 10, unique = true)
	private Long contatoId;
	
	@Column(name = "tele_contato", nullable = false, length = 30)
	private String telefone;
	
	@Column(name = "email_contato", nullable = true, length = 150, unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private ContatoEnum contatoEnum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	public Contato() {
		
	}
	public Contato(String telefone, String email, Cliente cliente, 
			Fornecedor fornecedor, ContatoEnum contatoEnum) {
	
		this.setTelefone(telefone);
		this.setEmail(email);
		this.setCliente(cliente);
		this.setFornecedor(fornecedor);
		this.setContatoEnum(contatoEnum);
	}
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
	public Long getContatoId() {
		return contatoId;
	}
	public void setContatoId(Long contatoId) {
		this.contatoId = contatoId;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public ContatoEnum getContatoEnum() {
		return contatoEnum;
	}
	public void setContatoEnum(ContatoEnum contatoEnum) {
		this.contatoEnum = contatoEnum;
	}
}
