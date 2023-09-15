package com.br.Ad.Ad.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.br.Ad.Ad.models.AdUser;
import com.br.Ad.Ad.models.BandeiraCartao;
import com.br.Ad.Ad.models.Pedido;
import com.br.Ad.Ad.models.PedidoEnumStatus;
import com.br.Ad.Ad.models.PedidoEnumTipoPagamento;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PedidoDto {

	private ClienteDto clienteDto;
	private EnderecoDto enderecoDto;
	private AdUser aduser;
	private Double valorPedido;
	private Double descontoPedido;
	private String observacaoPedido;
	private PedidoEnumStatus enumStatus;
	private PedidoEnumTipoPagamento enumPagamento;
	private BandeiraCartao enumCartao;
	private List<ItemPedidoDto> itens = new ArrayList<>();
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCadastro;

	public BandeiraCartao getEnumCartao() {
		return enumCartao;
	}

	public void setEnumCartao(BandeiraCartao enumcartao) {
		this.enumCartao = enumcartao;
	}

	public List<ItemPedidoDto> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDto> itens) {
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

	public ClienteDto getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDto clienteDto) {
		this.clienteDto = clienteDto;
	}

	public EnderecoDto getEnderecoDto() {
		return enderecoDto;
	}

	public void setEnderecoDto(EnderecoDto enderecoDto) {
		this.enderecoDto = enderecoDto;
	}

	public AdUser getAduser() {
		return aduser;
	}

	public void setAduser(AdUser aduser) {
		this.aduser = aduser;
	}

	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setEnumStatus(this.enumStatus);
		pedido.setEnumPagamento(this.enumPagamento);
		pedido.setEnumCartao(this.enumCartao);
		pedido.setDataCadastro(this.dataCadastro);
		pedido.setValorPedido(this.valorPedido);
		pedido.setObservacaoPedido(this.observacaoPedido);
		pedido.setUser(this.aduser);
		pedido.setEndereco(this.enderecoDto.toEndereco());
		pedido.setCliente(this.clienteDto.toCliente());
		return pedido;
	}

	public Pedido toPedido(Pedido pedido) {
		pedido.setEnumStatus(this.enumStatus);
		pedido.setEnumPagamento(this.enumPagamento);
		pedido.setEnumCartao(this.enumCartao);
		pedido.setDataCadastro(this.dataCadastro);
		pedido.setValorPedido(this.valorPedido);
		pedido.setObservacaoPedido(this.observacaoPedido);
		pedido.setUser(this.aduser);
		pedido.setEndereco(this.enderecoDto.toEndereco());
		pedido.setCliente(this.clienteDto.toCliente());
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

		this.aduser = pedido.getUser();
	}

	public void addItem(ItemPedidoDto item) {
		this.itens.add(item);
	}

	public void removeItem(ItemPedidoDto item) {
		this.itens.remove(item);
	}

}
