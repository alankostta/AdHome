package com.br.AdHome.AdHome.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.br.AdHome.AdHome.models.Pagamento;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PagamentoDto {
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double pagaPix;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double pagaCartao;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double pagaDinhe;
	
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
	public Pagamento toPagamento() {
		Pagamento pagamento = new Pagamento();
		pagamento.setPagaCartao(this.pagaCartao);
		pagamento.setPagaDinhe(this.pagaDinhe);
		pagamento.setPagaPix(this.pagaPix);
		return pagamento;
	}
	public Pagamento toPagamento(Pagamento pagamento) {
		
		pagamento.setPagaCartao(this.pagaCartao);
		pagamento.setPagaDinhe(this.pagaDinhe);
		pagamento.setPagaPix(this.pagaPix);
		return pagamento;
	}
	public void fromPagamento(Pagamento pagamento) {
		this.pagaCartao = pagamento.getPagaCartao();
		this.pagaDinhe = pagamento.getPagaDinhe();
		this.pagaPix = pagamento.getPagaPix();
	}
}
