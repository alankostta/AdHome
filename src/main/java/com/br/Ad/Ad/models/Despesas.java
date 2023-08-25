package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_despesas")
public class Despesas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao_debito")
	private String descDebito;
	
	@Column(name = "valor_debito")
	private BigDecimal valDebito;
	
	@Column(name = "data_vencimento")
	private LocalDate dtVencimento;
	
	@Column(name = "data_cadastro")
	private LocalDate dtCadastro;
	
	@ManyToOne
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	private PedidoEnumTipoPagamento enumPagamento;
	
	@Column(name = "tem_parcelamento")
	private Boolean parcela;
	
	@Column(name = "numero_parcela")
	private Integer numeroPercelas;
	
	public Despesas() {
		super();
	}

	public Despesas(Long id, String descDebito, BigDecimal valDebito, LocalDate dtVencimento, LocalDate dtCadastro,
			Categoria categoria, PedidoEnumTipoPagamento enumPagamento) {
		super();
		this.id = id;
		this.descDebito = descDebito;
		this.valDebito = valDebito;
		this.dtVencimento = dtVencimento;
		this.dtCadastro = dtCadastro;
		this.categoria = categoria;
		this.enumPagamento = enumPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDebito() {
		return descDebito;
	}

	public void setDescDebito(String descDebito) {
		this.descDebito = descDebito;
	}

	public BigDecimal getValDebito() {
		return valDebito;
	}

	public void setValDebito(BigDecimal valDebito) {
		this.valDebito = valDebito;
	}

	public LocalDate getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(LocalDate dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public PedidoEnumTipoPagamento getEnumPagamento() {
		return enumPagamento;
	}

	public void setEnumPagamento(PedidoEnumTipoPagamento enumPagamento) {
		this.enumPagamento = enumPagamento;
	}

	public Boolean getParcela() {
		return parcela;
	}

	public void setParcela(Boolean parcela) {
		this.parcela = parcela;
	}

	public Integer getNumeroPercelas() {
		return numeroPercelas;
	}

	public void setNumeroPercelas(Integer numeroPercelas) {
		this.numeroPercelas = numeroPercelas;
	}
}
