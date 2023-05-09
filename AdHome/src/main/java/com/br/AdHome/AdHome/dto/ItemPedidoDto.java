package com.br.AdHome.AdHome.dto;

import com.br.AdHome.AdHome.models.ItemPedido;
import com.br.AdHome.AdHome.models.Produto;

public class ItemPedidoDto {
	
	private Integer quantidade;
	
<<<<<<< Updated upstream
	private Produto produto;
=======
	private Double subTotal;
	
	private List<Produto> produto;
>>>>>>> Stashed changes

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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
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
