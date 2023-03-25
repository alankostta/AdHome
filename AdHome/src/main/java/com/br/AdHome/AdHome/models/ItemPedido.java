package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_item_pedido")
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_pedido_id")
	private Long itemPedidoId;
	
	@Column(name="qtd_itens")
	private Integer quantidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	public ItemPedido() {
		
	}
	public ItemPedido(Integer quantidade, Pedido Pedido, Produto produto) {
		this.setQuantidade(quantidade);
		this.setPedido(Pedido);
		this.setProduto(produto);
	}
	public Long getItemPedidoId() {
		return itemPedidoId;
	}
	public void setItemPedidoId(Long itemPedidoId) {
		this.itemPedidoId = itemPedidoId;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	@Override
	public String toString() {
		return "ItemPedido [itemPedidoId=" + itemPedidoId + ", quantidade=" + quantidade + ", pedido=" + pedido
				+ ", produto=" + produto + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemPedidoId, pedido, produto, quantidade);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(itemPedidoId, other.itemPedidoId) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(produto, other.produto) && Objects.equals(quantidade, other.quantidade);
	}
}
