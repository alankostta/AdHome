package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
       
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="produto_id")
    private List<Produto> produto;

	public ItemPedido() {
		super();
	}

	public ItemPedido(Long id, Integer quantidade, Double precoIten, List<Produto> produto) {
		super();
		this.id = id;
		this.quantidade = quantidade;
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
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoIten() {
		return precoIten;
	}

	public void setPrecoIten(Double precoIten) {
		this.precoIten = precoIten;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
}
