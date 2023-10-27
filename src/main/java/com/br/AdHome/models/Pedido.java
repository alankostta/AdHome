package com.br.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_altera", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataAlteraPedido;

	@Column(name = "ano_ref", nullable = false)
	private Integer anoRef;

	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1000000", inclusive = false)
	@Column(name = "valor_pedido", nullable = false)
	private Double valorPedido;

	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "1000000", inclusive = false)
	@Column(name = "desconto_pedido", nullable = false)
	private Double descontoPedido = 0.0;

	@Column(name = "observa_pedido")
	private String observacaoPedido;
	
	@NotNull(message = "Insira uma data")
	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private PedidoStatusEnum enumStatus;

	@Enumerated(EnumType.STRING)
	private PedidoTipoPagamentoEnum enumPagamento;

	@Enumerated(EnumType.STRING)
	private BandeiraCartaoEnum enumCartao;
	
	@Valid
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Item.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "iten_id")
	private List<Item> itens = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AdUser user;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Cliente cliente;

	public Pedido() {
		super();
	}

	public Pedido(Long id, LocalDateTime dataAlteraPedido, Integer anoRef, Double valorPedido, Double descontoPedido,
			String observacaoPedido, Date dataCadastro, PedidoStatusEnum enumStatus,
			PedidoTipoPagamentoEnum enumPagamento, BandeiraCartaoEnum enumCartao, List<Item> itens, AdUser user,
			Cliente cliente) {
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

	public PedidoStatusEnum getEnumStatus() {
		return enumStatus;
	}

	public void setEnumStatus(PedidoStatusEnum enumStatus) {
		this.enumStatus = enumStatus;
	}

	public PedidoTipoPagamentoEnum getEnumPagamento() {
		return enumPagamento;
	}

	public void setEnumPagamento(PedidoTipoPagamentoEnum enumPagamento) {
		this.enumPagamento = enumPagamento;
	}

	public BandeiraCartaoEnum getEnumCartao() {
		return enumCartao;
	}

	public void setEnumCartao(BandeiraCartaoEnum enumCartao) {
		this.enumCartao = enumCartao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
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
