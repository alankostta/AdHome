package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome_cate", nullable = false, length = 50)
	private String nomeCategoria;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id")
	private List<Produto> produtos;
	
	public Categoria() {
		
	}

	public Categoria(Long id, String nomeCategoria, String descricaoCategoria, Date dataCadasto,
			LocalDateTime dataAlte, Integer ano_ref) {
		
		this.nomeCategoria = nomeCategoria;
		this.setProdutos(produtos);
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
}
