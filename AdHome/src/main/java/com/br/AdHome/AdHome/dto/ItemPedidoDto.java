package com.br.AdHome.AdHome.dto;

import com.br.AdHome.AdHome.models.ItemPedido;

public class ItemPedidoDto {
	
	private Integer quantidade;
	
	private ProdutoDto produto;
	
	private Double subTotal;

	public ItemPedidoDto() {
		super();
	}

	public ItemPedidoDto(Integer quantidade, ProdutoDto produto, Double subTotal) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
		this.subTotal = subTotal;
	}

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
	public ProdutoDto getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}
	public ItemPedido toItens() {
		ItemPedido itens = new ItemPedido();
		itens.setQuantidade(quantidade);
	
		return itens;
	}
	public ItemPedido toItens(ItemPedido itens) {
		itens.setQuantidade(quantidade);
		
		return itens;
	}
	public void fromItemPedido(ItemPedido itens) {
		this.quantidade = itens.getQuantidade();
		
	}
}
