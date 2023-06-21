package com.br.AdHome.AdHome.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.AdHome.AdHome.models.BandeiraCartao;
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
	private BandeiraCartao enumCartao;
	
	private Set<ItemPedidoDto> itens = new HashSet<>();
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataCadastro;
	
	public PedidoDto() {
		
	}
	public PedidoDto(Set<ItemPedidoDto> itemDto, Double valorPedido, 
			Double descontoPedido, String observacaoPedido) {
		this.itens = itemDto;
	}
	public BandeiraCartao getEnumCartao() {
		return enumCartao;
	}
	
	public void setEnumCartao(BandeiraCartao enumcartao) {
		this.enumCartao = enumcartao;
	}
	
	public Set<ItemPedidoDto> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedidoDto> itens) {
		this.itens = itens;
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
		pedido.setEnumStatus(this.enumStatus);
		pedido.setEnumPagamento(this.enumPagamento);
		pedido.setEnumCartao(this.enumCartao);
		pedido.setDataCadastro(this.dataCadastro);
		pedido.setValorPedido(this.valorPedido);
		pedido.setObservacaoPedido(this.observacaoPedido);
		pedido.setDescontoPedido(this.descontoPedido);
		return pedido;
	}
	public Pedido toPedido(Pedido pedido) {
		pedido.setEnumStatus(this.enumStatus);
		pedido.setEnumPagamento(this.enumPagamento);
		pedido.setEnumCartao(this.enumCartao);
		pedido.setDataCadastro(this.dataCadastro);
		pedido.setObservacaoPedido(this.observacaoPedido);
		pedido.setValorPedido(this.valorPedido);
		pedido.setDescontoPedido(this.descontoPedido);
		return pedido;
	}
	public void fromPedido(Pedido pedido) {
		this.enumPagamento = pedido.getEnumPagamento();
		this.enumStatus = pedido.getEnumStatus();
		this.enumCartao = pedido.getEnumCartao();
		this.dataCadastro = pedido.getDataCadastro();
		this.valorPedido = pedido.getValorPedido();
		this.descontoPedido = pedido.getDescontoPedido();
		this.observacaoPedido = pedido.getObservacaoPedido();
	}
}
