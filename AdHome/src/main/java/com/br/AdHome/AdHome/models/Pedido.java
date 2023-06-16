package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id", nullable = false, length = 10, unique = true)
	private Long pedidoId;
	
	@Column(name = "data_altera", nullable = false)
	private LocalDateTime dataAlteraPedido;
	
	@Column(name = "ano_ref",nullable = false)
	private Integer anoRef;
	
	@Column(name = "valor_pedido", nullable = false)
	private Double valorPedido;
	
	@Column(name = "desconto_pedido", nullable = false)
	private Double descontoPedido;
	
	@Column(name = "observa_pedido")
	private String observacaoPedido;
	
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Enumerated(EnumType.STRING)
	private PedidoEnumStatus enumStatus;
	
	@Enumerated(EnumType.STRING)
	private PedidoEnumTipoPagamento enumPagamento;
	
	@Enumerated(EnumType.STRING)
	private BandeiraCartao enumCartao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemPedido> itens =  new HashSet<>();
	
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.PERSIST, orphanRemoval =  true)
	private Endereco endereco;
	
	@OneToOne(mappedBy = "pedido",cascade = CascadeType.PERSIST, orphanRemoval =  true)
	private AdUser user;
	
	public Pedido() {
		
	}
	
	public Pedido(Long pedidoId, LocalDateTime dataAlteraPedido, Integer anoRef, Double valorPedido,
			Double descontoPedido, String observacaoPedido, Date dataCadastro, PedidoEnumStatus enumStatus,
			PedidoEnumTipoPagamento enumPagamento, BandeiraCartao enumCartao, Cliente cliente, Set<ItemPedido> itens,
			Endereco endereco, AdUser user) {
		super();
		this.pedidoId = pedidoId;
		this.dataAlteraPedido = dataAlteraPedido;
		this.anoRef = anoRef;
		this.valorPedido = valorPedido;
		this.descontoPedido = descontoPedido;
		this.observacaoPedido = observacaoPedido;
		this.dataCadastro = dataCadastro;
		this.enumStatus = enumStatus;
		this.enumPagamento = enumPagamento;
		this.enumCartao = enumCartao;
		this.cliente = cliente;
		this.itens = itens;
		this.endereco = endereco;
		this.user = user;
	}

	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
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
	public String getObservacaoPedido() {
		return observacaoPedido;
	}
	public void setObservacaoPedido(String observacaoPedido) {
		this.observacaoPedido = observacaoPedido;
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
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Double getValorPedido() {
		return valorPedido;
	}
	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}
	public Double getDescontoPedido() {
		return descontoPedido;
	}
	public void setDescontoPedido(Double descontoPedido) {
		this.descontoPedido = descontoPedido;
	}
	public BandeiraCartao getEnumCartao() {
		return enumCartao;
	}
	public void setEnumCartao(BandeiraCartao enumCartao) {
		this.enumCartao = enumCartao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public AdUser getUser() {
		return user;
	}
	public void setUser(AdUser user) {
		this.user = user;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", dataAlteraPedido=" + dataAlteraPedido + ", anoRef=" + anoRef
				+ ", valorPedido=" + valorPedido + ", descontoPedido=" + descontoPedido + ", observacaoPedido="
				+ observacaoPedido + ", dataCadastro=" + dataCadastro + ", enumStatus=" + enumStatus
				+ ", enumPagamento=" + enumPagamento + ", enumCartao=" + enumCartao + ", cliente=" + cliente
				+ ", itens=" + itens + ", endereco=" + endereco + ", user=" + user + "]";
	}
}
