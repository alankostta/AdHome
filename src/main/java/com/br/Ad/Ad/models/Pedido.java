package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
    private Set<ItemPedido> itens =  new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "endereco_id", unique = true)
	private Endereco endereco;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "user_id", unique = true)
	private AdUser user;
	
	@ManyToOne
	private Cliente cliente;

	public Pedido() {
		super();
	}

	public Pedido(Long id, LocalDateTime dataAlteraPedido, Integer anoRef, Double valorPedido, Double descontoPedido,
			String observacaoPedido, Date dataCadastro, PedidoEnumStatus enumStatus,
			PedidoEnumTipoPagamento enumPagamento, BandeiraCartao enumCartao, Set<ItemPedido> itens, Endereco endereco,
			AdUser user, Cliente cliente) {
		super();
		this.id = id;
		this.dataAlteraPedido = dataAlteraPedido;
		this.anoRef = anoRef;
		this.valorPedido = valorPedido;
		this.descontoPedido = descontoPedido;
		this.observacaoPedido = observacaoPedido;
		this.dataCadastro = dataCadastro;
		this.enumStatus = enumStatus;
		this.enumPagamento = enumPagamento;
		this.enumCartao = enumCartao;
		this.itens = itens;
		this.endereco = endereco;
		this.user = user;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getObservacaoPedido() {
		return observacaoPedido;
	}

	public void setObservacaoPedido(String observacaoPedido) {
		this.observacaoPedido = observacaoPedido;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public BandeiraCartao getEnumCartao() {
		return enumCartao;
	}

	public void setEnumCartao(BandeiraCartao enumCartao) {
		this.enumCartao = enumCartao;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	
}