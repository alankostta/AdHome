package com.br.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.br.AdHome.utils.Utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe o nome do produto")
	@Size(min = 1, max = 255, message = "O campo produto precisa ter no minimo {min} digitos ou no maxímo {max}")
	@Column(name = "nome_prod",nullable = false, length = 255)
	private String nome;
	
	@NotBlank(message = "Informe a descrição")
	@Size(min = 1, max = 255, message = "O campo produto precisa ter no minimo {min} digitos ou no maxímo {max}")
	@Column(name = "descricao",nullable = false, length = 255)
	private String descricao;
	
	@NotBlank(message = "Informe a marca do produto")
	@Size(min = 1, max = 255, message = "O campo produto precisa ter no minimo {min} digitos ou no maxímo {max}")
	@Column(name = "marca",nullable = false, length = 255)
	private String marca;
	
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1000000", inclusive = false)
	@Column(name = "valor_Entrada",nullable = false)
	private Double valorEntrada = 0.0;
	
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1000000", inclusive = false)
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataAltera;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@Valid
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
		String valor = Utils.doubleToString(valorEntrada);
		this.valorEntrada = Utils.stringToDouble(valor);
	}

	public Double getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(Double valorSaida) {
		String valor = Utils.doubleToString(valorSaida);
		this.valorSaida = Utils.stringToDouble(valor);
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
