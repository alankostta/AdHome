package com.br.AdHome.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome_cate", nullable = false, length = 50)
	private String nomeCategoria;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="produto_id")
	private List<Produto> produtos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="despesas_id")
	private List<Despesa> despesas;

	public Categoria() {
		super();
	}

	public Categoria(Long id, String nomeCategoria, List<Produto> produtos, List<Despesa> despesas) {
		super();
		this.id = id;
		this.nomeCategoria = nomeCategoria;
		this.produtos = produtos;
		this.despesas = despesas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}
}
