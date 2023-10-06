package com.br.Ad.Ad.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import com.br.Ad.Ad.models.Categoria;
import com.br.Ad.Ad.models.Despesas;
import com.br.Ad.Ad.models.PedidoEnumTipoPagamento;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
public class DespesasDto {

	private Long id;

	private String descDebito;

	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	private BigDecimal valDebito;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtVencimento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtCadastro;

	private Categoria categoria;

	private PedidoEnumTipoPagamento enumPagamento;

	private Boolean parcela;

	private Integer numeroPercelas;

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

	public void setValDebito(String valDebito) {
		if (valDebito != null) {
			String cleanValue = valDebito.toString().replaceAll("[^0-9,]", "");
			cleanValue = cleanValue.replace(",", ".");
			this.valDebito = new BigDecimal(cleanValue);
		} else {
			this.valDebito = BigDecimal.ZERO;
		}
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
		if(dtCadastro == null) {
			dtCadastro = LocalDate.now();
	}
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
		if (numeroPercelas == null) {
			numeroPercelas = 0;
			this.numeroPercelas = numeroPercelas;
		}
		this.numeroPercelas = numeroPercelas;
	}

	public Despesas toDespesasDto() {

		Despesas despesas = new Despesas();
		despesas.setId(this.id);
		despesas.setDescDebito(this.descDebito);
		despesas.setValDebito(this.valDebito);
		despesas.setDtVencimento(this.dtVencimento);
		despesas.setDtCadastro(this.dtCadastro);
		despesas.setCategoria(this.categoria);
		despesas.setEnumPagamento(this.enumPagamento);
		despesas.setParcela(this.parcela);
		despesas.setNumeroPercelas(this.numeroPercelas);

		return despesas;
	}

	public Despesas toDespesasDto(Despesas despesas) {

		despesas.setId(this.id);
		despesas.setDescDebito(this.descDebito);
		despesas.setValDebito(this.valDebito);
		despesas.setDtVencimento(this.dtVencimento);
		despesas.setDtCadastro(this.dtCadastro);
		despesas.setCategoria(this.categoria);
		despesas.setEnumPagamento(this.enumPagamento);
		despesas.setParcela(this.parcela);
		despesas.setNumeroPercelas(this.numeroPercelas);

		return despesas;
	}
}
