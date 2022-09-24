package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id", nullable = false, length = 10, unique = true)
	private Long pedidoId;
	@Column(name = "data_pedido", nullable = false)
	private LocalDateTime dataPedido;
	@Column(name = "ano_ref",nullable = false)
	private Integer anoRef;
	@Column(name = "qtd_itens", nullable = false)
	private Integer qtdItens;
	
	@OneToOne(mappedBy="pedido",fetch = FetchType.LAZY)
	private Pagamento pagamanto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy="pedido",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Produto> produto;
	
	public Pedido() {
		
	}
	public Pedido(LocalDateTime dataPedido, Integer qtdItens,
			Cliente cliente, Pagamento pagamento, Integer anoRef) {
		super();
		this.setDataPedido(dataPedido);
		this.setQtdItens(qtdItens);
		this.setCliente(cliente);
		this.setPagamanto(pagamento);
		this.setProduto(getProduto());
		this.setAnoRef(anoRef);
	}
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public Integer getAnoRef() {
		return anoRef;
	}
	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public Pagamento getPagamanto() {
		return pagamanto;
	}
	public void setPagamanto(Pagamento pagamanto) {
		this.pagamanto = pagamanto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataPedido, pagamanto, pedidoId, produto, qtdItens, anoRef);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(cliente, other.cliente)
				&& Objects.equals(dataPedido, other.dataPedido) && Objects.equals(pagamanto, other.pagamanto)
				&& Objects.equals(pedidoId, other.pedidoId) && Objects.equals(produto, other.produto)
				&& Objects.equals(qtdItens, other.qtdItens) && Objects.equals(anoRef, other.anoRef);
	}
	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", dataPedido=" + dataPedido + ", qtdItens=" + qtdItens + ", pagamanto="
				+ pagamanto + ", cliente=" + cliente + ", produto=" + produto +"anoRef="+anoRef+"]";
	}
}
