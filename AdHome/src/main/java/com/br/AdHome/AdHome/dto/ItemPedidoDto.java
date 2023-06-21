package com.br.AdHome.AdHome.dto;

import com.br.AdHome.AdHome.models.ItemPedido;

public class ItemPedidoDto {
	
	private Integer quantidade;
	
	private ProdutoDto produto;
	
	private Double subTotal;

	public ItemPedidoDto() {
		super();
	}
	
	public ItemPedidoDto(ProdutoDto produto) {
		super();
		this.produto = produto;
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
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public ProdutoDto getProdutoDto() {
		return produto;
	}

	public void setProdutoDto(ProdutoDto produto) {
		this.produto = produto;
	}

	public ItemPedido toItemPedido() {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setQuantidade(quantidade);
		itemPedido.setSubTotal(subTotal);
		// Configure outras propriedades do objeto ItemPedido, se necessário
		return itemPedido;
	}
	public ItemPedido toItemPedido(ItemPedido itemPedido) {
		itemPedido.setQuantidade(quantidade);
		itemPedido.setSubTotal(subTotal);
		// Configure outras propriedades do objeto ItemPedido, se necessário
		return itemPedido;
	}
	public void fromItemPedido(ItemPedido item) {
		this.quantidade = item.getQuantidade();
		this.subTotal =  item.getSubTotal();
	}
}

