package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
	
	@Column(name="descri_cate", nullable = false, length = 150)
	private String descricaoCategoria;
	
	@Column(name = "data_Cadastro", nullable = false, length = 30)
	private LocalDateTime dataCadasto;
	
	@Column(name = "data_Alte", nullable = false, length = 30)
	private LocalDateTime dataAlte;
	
	@Column(name = "ano_ref", nullable = false)
	private Integer anoRef;
	
	@OneToMany(mappedBy = "categorias")
	private List<Produto> produtos;
	
	public Categoria() {
		super();
	}

	public Categoria(Long categoriaId, String nomeCategoria, String descricaoCategoria, LocalDateTime dataCadasto,
			LocalDateTime dataAlte, Integer ano_ref) {
		super();
		this.categoriaId = categoriaId;
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
		this.dataCadasto = dataCadasto;
		this.dataAlte = dataAlte;
		this.anoRef = ano_ref;
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

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public LocalDateTime getDataCadasto() {
		return dataCadasto;
	}

	public void setDataCadasto(LocalDateTime dataCadasto) {
		this.dataCadasto = dataCadasto;
	}

	public LocalDateTime getDataAlte() {
		return dataAlte;
	}

	public void setDataAlte(LocalDateTime dataAlte) {
		this.dataAlte = dataAlte;
	}

	public Integer getAno_ref() {
		return anoRef;
	}

	public void setAno_ref(Integer anoRef) {
		this.anoRef = anoRef;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria [categoriaId=" + categoriaId + ", nomeCategoria=" + nomeCategoria + ", descricaoCategoria="
				+ descricaoCategoria + ", dataCadasto=" + dataCadasto + ", dataAlte=" + dataAlte + ", anoRef="
				+ anoRef + ", produtos=" +produtos+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoRef, categoriaId, dataAlte, dataCadasto, descricaoCategoria, nomeCategoria, produtos);
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
		return Objects.equals(anoRef, other.anoRef) && Objects.equals(categoriaId, other.categoriaId)
				&& Objects.equals(dataAlte, other.dataAlte) && Objects.equals(dataCadasto, other.dataCadasto)
				&& Objects.equals(descricaoCategoria, other.descricaoCategoria)
				&& Objects.equals(nomeCategoria, other.nomeCategoria)
				&& Objects.equals(produtos, other.produtos);
	}
}
