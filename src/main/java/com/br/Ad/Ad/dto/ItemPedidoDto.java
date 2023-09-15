package com.br.Ad.Ad.dto;

import com.br.Ad.Ad.models.ItemPedido;
import com.br.Ad.Ad.models.Pedido;
import com.br.Ad.Ad.models.Produto;

public class ItemPedidoDto {
	
	private Integer quantidade = 0;
	private Double precoIten = 0.0;
	private Double subTotal = 0.0;
	private Double valorTotal = 0.0;
	private Produto produto;
	private Pedido pedido;
		

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Double getPrecoIten() {
		return precoIten;
	}

	public void setPrecoIten(Double precoIten) {
		this.precoIten = precoIten;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Double getValorTotal() {
		if(valorTotal == null) {
			valorTotal = 0.0;
			return valorTotal;
		}
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
	
	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public ItemPedido toItens() {
		ItemPedido itens = new ItemPedido();
		itens.setQuantidade(this.quantidade);
		itens.setSubTotal(this.subTotal);
		itens.setProduto(this.produto);
		itens.setPrecoIten(this.precoIten);
		itens.setPedido(this.pedido);
		return itens;
	}
	public ItemPedido toItens(ItemPedido itens) {
		itens.setQuantidade(this.quantidade);
		itens.setSubTotal(this.subTotal);
		itens.setProduto(this.produto);
		itens.setPrecoIten(this.precoIten);
		itens.setPedido(this.pedido);
		return itens;
	}
	public void fromItemPedido(ItemPedido itens) {
		this.quantidade = itens.getQuantidade();
		this.subTotal = itens.getSubTotal();
		this.produto = itens.getProduto();
		this.precoIten = itens.getPrecoIten();
		this.pedido = itens.getPedido();
	}
}
