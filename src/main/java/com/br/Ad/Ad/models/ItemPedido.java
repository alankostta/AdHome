package com.br.Ad.Ad.models;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_item_pedido")
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name="qtd_itens")
    private Integer quantidade;
    
    @Column(name="preco_iten")
    private Double precoIten;
    
    @Column(name="sub_total")
    private Double subTotal;
    
    @Column(name="valor_total")
    private Double valorTotal;
       
    @ManyToOne
    private Produto produto;
    
    @ManyToOne
    private Pedido pedido;

	public ItemPedido() {
		super();
	}

	public ItemPedido(Long id, Integer quantidade, Double precoIten, Double subTotal
			, Double valorTotal, Produto produto, Pedido pedido) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.subTotal = subTotal;
		this.precoIten = precoIten;
		this.produto = produto;
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		if(quantidade == null) {
			quantidade = 0;
			return quantidade;
		}
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

	public Double getPrecoIten() {
		return precoIten;
	}

	public void setPrecoIten(Double precoIten) {
		this.precoIten = precoIten;
	}

	public Produto getProduto() {
		return produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
