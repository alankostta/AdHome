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

@SuppressWarnings("serial")
@Entity
@Table(name="tb_item_pedido")
public class ItemPedido implements Serializable{
	
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
        
    }
    public ItemPedido(Long id, Integer quantidade,Double precoIten,  Pedido Pedido, List<Produto> produto) {
        this.setQuantidade(quantidade);
        this.setPrecoIten(precoIten);
        this.setProduto(produto);
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
		
		//if(produto == null && this.precoIten == null) {
			//this.setPrecoIten(produto.getPreco());
		//}
	}
    public List<Produto> getProduto() {
        return produto;
    }
    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    public void addItens(Produto prod) {
    	this.produto.add(prod);
    }
//    private void calcularValorPedido() {
//    	for (Produto produ : this.produto) {
//			this.precoTotalDoPedido += produ.getValorSaida();
//		}
//    }
}
