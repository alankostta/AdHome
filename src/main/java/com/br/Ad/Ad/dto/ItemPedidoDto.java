package com.br.Ad.Ad.dto;

import java.util.List;

import com.br.Ad.Ad.models.ItemPedido;
import com.br.Ad.Ad.models.Produto;

public class ItemPedidoDto {
	
	private Integer quantidade;
	private List<Produto> produto;
	private Double subTotal;	

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public ItemPedido toItens() {
		ItemPedido itens = new ItemPedido();
		itens.setQuantidade(quantidade);
		itens.setProduto(produto);
		return itens;
	}
	public ItemPedido toItens(ItemPedido itens) {
		itens.setQuantidade(quantidade);
		itens.setProduto(produto);
		return itens;
	}
	public void fromItemPedido(ItemPedido itens) {
		this.quantidade = itens.getQuantidade();
		this.produto = itens.getProduto();
	}
}
