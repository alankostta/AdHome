package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_prod",nullable = false, length = 255)
	private String nome;
	
	@Column(name = "descricao",nullable = false, length = 255)
	private String descricao;
	
	@Column(name = "marca",nullable = false, length = 255)
	private String marca;
	
	@Column(name = "valor_Entrada",nullable = false)
	private Double valorEntrada = 0.0;
	
	@Column(name = "valor_saida", nullable = true)
	private Double valorSaida = 0.0;
	
	@Column(name = "Qtd_estoque", nullable = false)
	private Integer estoqueQtd = 0;
	
	@Column(name = "data_cadastro", nullable = false, length = 30)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime dataCadastro;
	
	@Column(name = "ano_ref",nullable = false, length = 20)
	private Integer anoRef;
	
	@Column(name = "data_altera", nullable = false, length = 30)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime dataAltera;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@ManyToOne
	private Categoria categoria;

	public Produto() {
		super();
	}

	public Produto(Long id, String nome, String descricao, String marca, Double valorEntrada, Double valorSaida, Integer estoqueQtd
			, LocalDateTime dataCadastro, Integer anoRef, LocalDateTime dataAltera, Fornecedor fornecedor, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.valorEntrada = valorEntrada;
		this.valorSaida = valorSaida;
		this.estoqueQtd = estoqueQtd;
		this.dataCadastro = dataCadastro;
		this.anoRef = anoRef;
		this.dataAltera = dataAltera;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}

	public LocalDateTime getDataAltera() {
		return dataAltera;
	}

	public void setDataAltera(LocalDateTime dataAltera) {
		this.dataAltera = dataAltera;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
