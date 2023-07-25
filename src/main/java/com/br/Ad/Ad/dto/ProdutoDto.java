package com.br.Ad.Ad.dto;

import java.math.BigInteger;

import com.br.Ad.Ad.models.Categoria;
import com.br.Ad.Ad.models.Fornecedor;
import com.br.Ad.Ad.models.Produto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */

public class ProdutoDto {
	
	private BigInteger id;
	
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	private String descricao;
	
	@NotBlank
	@Size(max = 255)
	private String marca;
	
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double valorEntrada;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double valorSaida;
	
	@NotNull
	@DecimalMin(value = "0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Integer estoqueQtd;
	
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double preco;
	
	private Fornecedor fornecedor;
	private Categoria categoria;
	
	public ProdutoDto() {
		super();
	}
	
	public ProdutoDto(BigInteger id, @NotBlank @Size(max = 255) String descricao,
			@NotBlank @Size(max = 255) String marca, String nome,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000000", inclusive = false) Double valorEntrada,
			@DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000000", inclusive = false) Double valorSaida,
			@NotNull @DecimalMin(value = "0", inclusive = false) @DecimalMax(value = "1000000", inclusive = false) Integer estoqueQtd,
			@NotNull @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "1000000", inclusive = false) 
			Double preco, Fornecedor fornecedor, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.marca = marca;
		this.valorEntrada = valorEntrada;
		this.valorSaida = valorSaida;
		this.estoqueQtd = estoqueQtd;
		this.preco = preco;
		this.categoria = categoria;
		this.fornecedor = fornecedor;
	}

	public BigInteger getId() {
		return id;
	}
	public void setProdutoId(BigInteger id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
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
		if(valorSaida == null) {
			valorSaida = 0.0;
			this.valorSaida = valorSaida;
		}
		else {
			this.valorSaida = valorSaida;
		}
	}
	public Integer getEstoqueQtd() {
		return estoqueQtd;
	}
	public void setEstoqueQtd(Integer estoqueQtd) {
		this.estoqueQtd = estoqueQtd;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Produto toProduto() {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		produto.setPreco(preco);
		produto.setMarca(marca);
		produto.setCategoria(categoria);
		produto.setFornecedor(fornecedor);
		return produto;
		
	}
	public Produto toProduto(Produto produto) {
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		produto.setPreco(preco);
		produto.setMarca(marca);
		produto.setCategoria(categoria);
		produto.setFornecedor(fornecedor);
		return produto;
		
	}
	public void fromProduto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valorEntrada = produto.getValorEntrada();
		this.valorSaida = produto.getValorSaida();
		this.estoqueQtd = produto.getEstoqueQtd();
		this.preco = produto.getPreco();
		this.marca = produto.getMarca();
		this.categoria = produto.getCategoria();
		this.fornecedor = produto.getFornecedor();
	}
}
