package com.br.AdHome.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name="qtd_itens")
    private Integer quantidade = 0;
    
    @Column(name="preco_iten")
    private Double precoIten = 0.0;
    
    @Column(name="sub_total")
    private Double subTotal = 0.0;
    
    @Column(name="valor_total")
    private Double valorTotal = 0.0;
        
    @ManyToOne
    private Produto produto;
    
    @ManyToOne
    private Pedido pedido;

	public Item() {
		super();
	}

	public Item(Long id, Integer quantidade, Double precoIten, Double subTotal
			, Produto produto, Pedido pedido) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.subTotal = subTotal;
		this.precoIten = precoIten;
		this.produto = produto;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
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

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
