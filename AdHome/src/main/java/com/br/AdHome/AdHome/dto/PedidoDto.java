package com.br.AdHome.AdHome.dto;

import javax.validation.constraints.NotNull;

import com.br.AdHome.AdHome.models.Pedido;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PedidoDto {
	
	@NotNull
	private Integer qtdItens;
	@NotNull
	private Double totalPedido;
	
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItensDto(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	public Double getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setQtdItens(qtdItens);
		pedido.setTotalPedido(totalPedido);
		return pedido;
	}
	public Pedido toPedido(Pedido pedido) {
		pedido.setQtdItens(qtdItens);
		pedido.setTotalPedido(totalPedido);
		return pedido;
	}
	public void fromPedido(Pedido pedido) {
		this.qtdItens = pedido.getQtdItens();
		this.totalPedido = pedido.getTotalPedido();
	
	}
}
