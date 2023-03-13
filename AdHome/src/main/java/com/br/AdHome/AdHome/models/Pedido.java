package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name = "data_altera", nullable = false)
	private LocalDateTime dataAlteraPedido;
	
	@Column(name = "ano_ref",nullable = false)
	private Integer anoRef;
	
	@Column(name = "qtd_itens", nullable = false)
	private Integer qtdItens;
	
	@Enumerated(EnumType.STRING)
	private PedidoEnumStatus enumStatus;
	
	@Enumerated(EnumType.STRING)
	private PedidoEnumTipoPagamento enumPagamento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
    private Set<ItemPedido> itens =  new HashSet<>();
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Cliente cliente;
	
	public Pedido() {
		
	}
	public Pedido(LocalDateTime dataPedido,LocalDateTime dataAlteraPedido, Integer qtdItens,
		Cliente cliente, Integer anoRef) {
		super();
		this.setDataPedido(dataPedido);
		this.setDataAlteraPedido(dataAlteraPedido);
		this.setQtdItens(qtdItens);
		this.setCliente(cliente);
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
	public LocalDateTime getDataAlteraPedido() {
		return dataAlteraPedido;
	}
	public void setDataAlteraPedido(LocalDateTime dataAlteraPedido) {
		this.dataAlteraPedido = dataAlteraPedido;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	public PedidoEnumStatus getEnumStatus() {
		return enumStatus;
	}
	public void setEnumStatus(PedidoEnumStatus enumStatus) {
		this.enumStatus = enumStatus;
	}
	public PedidoEnumTipoPagamento getEnumPagamento() {
		return enumPagamento;
	}
	public void setEnumPagamento(PedidoEnumTipoPagamento enumPagamento) {
		this.enumPagamento = enumPagamento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataPedido, pedidoId, qtdItens, anoRef);
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
				&& Objects.equals(dataPedido, other.dataPedido) && Objects.equals(pedidoId, other.pedidoId) 
				&& Objects.equals(qtdItens, other.qtdItens) && Objects.equals(anoRef, other.anoRef);
	}
	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", dataPedido=" + dataPedido + ", qtdItens=" + qtdItens + ", cliente=" + cliente +"anoRef="+anoRef+"]";
	}
}
