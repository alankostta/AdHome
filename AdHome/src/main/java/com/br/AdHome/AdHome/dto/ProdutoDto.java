package com.br.AdHome.AdHome.dto;

import java.math.BigInteger;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.br.AdHome.AdHome.models.Produto;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */

public class ProdutoDto {
	
	private BigInteger produtoId;
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
	
	public BigInteger getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(BigInteger produtoId) {
		this.produtoId = produtoId;
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
	public Produto toProduto() {
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		produto.setPreco(preco);
		produto.setMarca(marca);
		return produto;
		
	}
	public Produto toProduto(Produto produto) {
		
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		produto.setPreco(preco);
		produto.setMarca(marca);
		return produto;
		
	}
	public void fromProduto(Produto produto) {
		
		this.descricao = produto.getDescricao();
		this.valorEntrada = produto.getValorEntrada();
		this.valorSaida = produto.getValorSaida();
		this.estoqueQtd = produto.getEstoqueQtd();
		this.preco = produto.getPreco();
		this.marca = produto.getMarca();
	}
}
