package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemPedido> itens =  new HashSet<>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "endereco_id", unique = true)
	private Endereco endereco;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	private AdUser user;
	
	public Pedido() {
		
	}
	public Pedido(LocalDateTime dataAlteraPedido, Cliente cliente,
			Integer anoRef, Date dataCadastro, Double valorPedido, AdUser user, Endereco endereco) {
 
		this.setDataAlteraPedido(dataAlteraPedido);
		this.setCliente(cliente);
		this.setAnoRef(anoRef);
		this.setDataCadastro(dataCadastro);
		this.setValorPedido(valorPedido);
		this.setUser(user);
		this.setEndereco(endereco);
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
	@Override
	public String toString() {
		return "Pedido [pedidoId=" + ", dataAlteraPedido=" + dataAlteraPedido
				+ ", anoRef=" + anoRef + ", valorPedido=" + valorPedido + ", descontoPedido="
				+ descontoPedido + ", observacaoPedido=" + observacaoPedido + ", dataCadastro=" + dataCadastro
				+ ", enumStatus=" + enumStatus + ", enumPagamento=" + enumPagamento + ", enumCartao=" + enumCartao
				+ ", itens=" + itens + ", endereco=" + endereco + ", cliente=" + cliente + ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(anoRef, cliente, dataAlteraPedido, dataCadastro, descontoPedido, endereco,
				enumPagamento, enumStatus, enumCartao, itens, observacaoPedido, pedidoId, user, valorPedido);
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
		return Objects.equals(anoRef, other.anoRef) && Objects.equals(cliente, other.cliente)
				&& Objects.equals(dataAlteraPedido, other.dataAlteraPedido)
				&& Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(descontoPedido, other.descontoPedido) && Objects.equals(endereco, other.endereco)
				&& enumPagamento == other.enumPagamento && enumStatus == other.enumStatus
				&& enumCartao == other.enumCartao && Objects.equals(itens, other.itens)
				&& Objects.equals(observacaoPedido, other.observacaoPedido) && Objects.equals(pedidoId, other.pedidoId)
				&& Objects.equals(user, other.user)
				&& Objects.equals(valorPedido, other.valorPedido);
	}
}
