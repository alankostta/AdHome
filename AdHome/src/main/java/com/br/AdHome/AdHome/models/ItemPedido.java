package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    
    @Column(name="sub_total")
    private Double subTotal;
    
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;
    
    public ItemPedido() {
        
    }
    public ItemPedido(Integer quantidade,Double subTotal,  Pedido Pedido, Produto produto) {
        this.setQuantidade(quantidade);
        this.setSubTotal(subTotal);
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
    public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
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
}
