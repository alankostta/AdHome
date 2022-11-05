package com.br.AdHome.AdHome.models;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pagamento_id",nullable = false, unique = true, length = 10)
	private Long pagamentoId;
	@Column(name = "Pix_pagamento",nullable = false)
	private Double pagaPix;
	@Column(name = "Cartao_pagamento", nullable = false)
	private Double pagaCartao;
	@Column(name = "Dinhe_pagamento",nullable = false)
	private Double pagaDinhe;
	@Column(name = "data_pagamento",nullable = false, length = 30)
	private LocalDateTime dataPagamanto;
	@Column(name = "ano_ref",nullable = false)
	private Integer anoRef;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "pedi_id", unique = true)
	private Pedido pedido;
	
	public Pagamento() {
		
	}
	public Pagamento(Double pagaPix, Double pagaCartao, Double pagaDinhe, 
			LocalDateTime dataPagamento, Pedido pedido, Integer anoRef) {
		super();
		this.setPagaPix(pagaPix);
		this.setPagaCartao(pagaCartao);
		this.setPagaDinhe(pagaDinhe);
		this.setDataPagamanto(dataPagamento);
		this.setPedido(pedido);
		this.setAnoRef(anoRef);
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataPagamanto, pagaCartao, pagaDinhe, pagaPix, pagamentoId, anoRef);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(dataPagamanto, other.dataPagamanto) && Objects.equals(pagaCartao, other.pagaCartao)
				&& Objects.equals(pagaDinhe, other.pagaDinhe) && Objects.equals(pagaPix, other.pagaPix)
				&& Objects.equals(pagamentoId, other.pagamentoId)&& Objects.equals(anoRef, other.anoRef);
	}
	public Integer getAnoRef() {
		return anoRef;
	}
	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}
	public Double getPagaPix() {
		return pagaPix;
	}
	public void setPagaPix(Double pagaPix) {
		this.pagaPix = pagaPix;
	}
	public Double getPagaCartao() {
		return pagaCartao;
	}
	public void setPagaCartao(Double pagaCartao) {
		this.pagaCartao = pagaCartao;
	}
	public Double getPagaDinhe() {
		return pagaDinhe;
	}
	public void setPagaDinhe(Double pagaDinhe) {
		this.pagaDinhe = pagaDinhe;
	}
	public Long getPagamentoId() {
		return pagamentoId;
	}
	public void setPagamentoId(Long pagamentoId) {
		this.pagamentoId = pagamentoId;
	}
	public LocalDateTime getDataPagamanto() {
		return dataPagamanto;
	}
	public void setDataPagamanto(LocalDateTime dataPagamanto) {
		this.dataPagamanto = dataPagamanto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "Pagamento [pagamentoId=" + pagamentoId + ", pagaPix=" + pagaPix + ", pagaCartao=" + pagaCartao
				+ ", pagaDinhe=" + pagaDinhe + ", dataPagamanto=" + dataPagamanto + ", pedido=" + pedido 
				+"anoRef="+anoRef+"]";
	}
}
