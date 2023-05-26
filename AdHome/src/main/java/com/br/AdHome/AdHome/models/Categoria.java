package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id", nullable = false, length = 10, unique = true)
	private Long categoriaId;
	
	@Column(name="nome_cate", nullable = false, length = 50)
	private String nomeCategoria;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST)
	private List<Produto> produtos;
	
	public Categoria() {
		
	}

	public Categoria(Long categoriaId, String nomeCategoria) {
		this.categoriaId = categoriaId;
		this.nomeCategoria = nomeCategoria;
		this.setProdutos(produtos);
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
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

	@Override
	public String toString() {
		return "Categoria [categoriaId=" + categoriaId + ", nomeCategoria=" + nomeCategoria + ", produtos=" + produtos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoriaId, nomeCategoria, produtos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(
				categoriaId, other.categoriaId)
				&& Objects.equals(nomeCategoria, other.nomeCategoria)
				&& Objects.equals(produtos, other.produtos);
	}
}
