package com.br.AdHome.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_despesas")
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "Informe a descrição")
	@Column(name = "descricao_debito")
	private String descDebito;
	
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	@Column(name = "valor_debito")
	private BigDecimal valDebito;
	
	@NotNull(message = "Informe a data de vencimento")
	@Column(name = "data_vencimento")
	private LocalDate dtVencimento;
	
	@NotNull(message = "Informe a data de cadastro")
	@Column(name = "data_cadastro")
	private LocalDate dtCadastro;
	
	
	@ManyToOne
	private Categoria categoria;
	
	
	@Enumerated(EnumType.STRING)
	private PedidoTipoPagamentoEnum enumPagamento;
	
	@Column(name = "tem_parcelamento")
	private Boolean parcela = false;
	
	@Column(name = "numero_parcela")
	private Integer numeroPercelas = 0;
	
	public Despesa() {
		super();
	}

	public Despesa(Long id, String descDebito, BigDecimal valDebito, LocalDate dtVencimento, LocalDate dtCadastro,
			Categoria categoria, PedidoTipoPagamentoEnum enumPagamento) {
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

	public PedidoTipoPagamentoEnum getEnumPagamento() {
		return enumPagamento;
	}

	public void setEnumPagamento(PedidoTipoPagamentoEnum enumPagamento) {
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
