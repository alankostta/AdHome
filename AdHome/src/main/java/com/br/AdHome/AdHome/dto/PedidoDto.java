package com.br.AdHome.AdHome.dto;

import java.util.Date;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.models.PedidoEnumStatus;
import com.br.AdHome.AdHome.models.PedidoEnumTipoPagamento;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PedidoDto {
	
	private Double valorPedido;

	private Double descontoPedido;
	
	private String observacaoPedido;
	
	private PedidoEnumStatus enumStatus;
	
	private PedidoEnumTipoPagamento enumPagamento;
	
	private Date dataCadastro;
	
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
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setEnumStatus(enumStatus);
		pedido.setEnumPagamento(enumPagamento);
		pedido.setDataCadastro(dataCadastro);
		pedido.setValorPedido(valorPedido);
		pedido.setObservacaoPedido(observacaoPedido);
		return pedido;
	}
	public Pedido toPedido(Pedido pedido) {
		pedido.setEnumStatus(enumStatus);
		pedido.setEnumPagamento(enumPagamento);
		pedido.setDataCadastro(dataCadastro);
		pedido.setObservacaoPedido(observacaoPedido);
		pedido.setValorPedido(valorPedido);
		return pedido;
	}
	public void fromPedido(Pedido pedido) {
		this.enumPagamento = pedido.getEnumPagamento();
		this.enumStatus = pedido.getEnumStatus();
		this.dataCadastro = pedido.getDataCadastro();
		this.valorPedido = pedido.getValorPedido();
		this.descontoPedido = pedido.getDescontoPedido();
		this.observacaoPedido = pedido.getObservacaoPedido();
	}
}
