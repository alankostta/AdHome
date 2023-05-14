package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id",nullable = false, length = 10, unique = true)
	private Long produtoId;
	
	@Column(name = "descricao",nullable = false, length = 255)
	private String descricao;
	
	@Column(name = "marca",nullable = false, length = 255)
	private String marca;
	
	@Column(name = "valor_Entrada",nullable = false)
	private Double valorEntrada;
	
	@Column(name = "valor_saida", nullable = true)
	private Double valorSaida;
	
	@Column(name = "Qtd_estoque", nullable = false)
	private Integer estoqueQtd = 0;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "data_cadastro", nullable = false, length = 30)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime dataCadastro;
	
	@Column(name = "ano_ref",nullable = false, length = 20)
	private Integer anoRef;
	
	@Column(name = "data_altera", nullable = false, length = 30)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime dataAltera;
	
	@ManyToOne
	@JoinColumn(name = "itens_id")
	private ItemPedido itens;
	
	public Produto() {
		
	}
	public Produto(String descricao, Double valorEntrada, 
			Double valorSaida, Integer estoqueQtd, Double preco,
			LocalDateTime dataCadastro,LocalDateTime dataAltera, 
			Integer anoRef, ItemPedido itens) {
	
		this.setDescricao(descricao);
		this.setValorEntrada(valorEntrada);
		this.setValorSaida(valorSaida);
		this.setEstoqueQtd(estoqueQtd);
		this.setDataCadastro(dataCadastro);
		this.setDataAltera(dataAltera);
		this.setPreco(preco);
		this.setItens(itens);
	}
	
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId= produtoId;
	}
	public String getDescricao() {
		return descricao;
	}
	public Integer getAnoRef() {
		return anoRef;
	}
	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public Double getValorSaida() {
		return valorSaida;
	}
	public void setValorSaida(Double valorSaida) {
		this.valorSaida = valorSaida;
	}
	public Integer getEstoqueQtd() {
		return estoqueQtd;
	}
	public void setEstoqueQtd(Integer estoqueQtd) {
		this.estoqueQtd = estoqueQtd;
	}
	public ItemPedido getItens() {
		return itens;
	}
	public void setItens(ItemPedido itens) {
		this.itens = itens;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public LocalDateTime getDataAltera() {
		return dataAltera;
	}
	public void setDataAltera(LocalDateTime dataAltera) {
		this.dataAltera = dataAltera;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "Produto [produtoId=" + produtoId + ", descricao=" + descricao + ", marca=" + marca + ", valorEntrada="
				+ valorEntrada + ", valorSaida=" + valorSaida + ", estoqueQtd=" + estoqueQtd + ", preco=" + preco
				+ ", dataCadastro=" + dataCadastro + ", anoRef=" + anoRef + ", dataAltera=" + dataAltera
				+ ", itens=" + itens + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(anoRef, dataAltera, dataCadastro, descricao, estoqueQtd, itens,
				marca, preco, produtoId, valorEntrada, valorSaida);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(anoRef, other.anoRef)
				&& Objects.equals(dataAltera, other.dataAltera) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(estoqueQtd, other.estoqueQtd)
				&& Objects.equals(itens, other.itens)
				&& Objects.equals(marca, other.marca) && Objects.equals(preco, other.preco)
				&& Objects.equals(produtoId, other.produtoId) && Objects.equals(valorEntrada, other.valorEntrada)
				&& Objects.equals(valorSaida, other.valorSaida);
	}
}
