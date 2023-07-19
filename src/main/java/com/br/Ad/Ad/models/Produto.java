package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	
	public Produto() {
		
	}
	public Produto(Long id, String descricao, Double valorEntrada, 
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
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		//this.valorEntrada * this.cacularLucro();
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
//	public double calcularLucro() {
//		return this.preco * (this.porcentagemDeLocroDesejado / 100);
//	}
}
