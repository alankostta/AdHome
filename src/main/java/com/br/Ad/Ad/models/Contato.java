package com.br.Ad.Ad.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "tele_contato", nullable = false, length = 30)
	private String telefone;
	
	@Column(name = "email_contato", nullable = true, length = 150, unique = true)
	private String email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	private Fornecedor fornecedor;
	
	@Enumerated(EnumType.STRING)
	private ContatoEnum contatoEnum;
	
	public Contato() {
		
	}

	public Contato(Long id, String telefone, String email, Cliente cliente, Fornecedor fornecedor, ContatoEnum contatoEnum) {
		super();
		this.setId(id);
		this.telefone = telefone;
		this.email = email;
		this.cliente = cliente;
		this.fornecedor = fornecedor;
		this.contatoEnum = contatoEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
