package com.br.AdHome.AdHome.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.models.PedidoEnumStatus;
import com.br.AdHome.AdHome.models.PedidoEnumTipoPagamento;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PedidoDto {
	
	@NotNull
	private Integer qtdItens;
	
	private PedidoEnumStatus enumStatus;
	
	private PedidoEnumTipoPagamento enumPagamento;
	
	private Set<Pedido> pedido;
	
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItensDto(Integer qtdItens) {
		this.qtdItens = qtdItens;
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
	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	public Set<Pedido> getPedido() {
		return pedido;
	}
	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setQtdItens(qtdItens);
		pedido.setEnumStatus(enumStatus);
		pedido.setEnumPagamento(enumPagamento);
		return pedido;
	}
	public Pedido toPedido(Pedido pedido) {
		pedido.setQtdItens(qtdItens);
		pedido.setEnumStatus(enumStatus);
		pedido.setEnumPagamento(enumPagamento);
		return pedido;
	}
	public void fromPedido(Pedido pedido) {
		this.qtdItens = pedido.getQtdItens();
		this.enumPagamento = pedido.getEnumPagamento();
		this.enumStatus = pedido.getEnumStatus();
	}
}
