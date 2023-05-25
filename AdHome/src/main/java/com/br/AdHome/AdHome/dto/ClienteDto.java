package com.br.AdHome.AdHome.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.models.Endereco;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ClienteDto{
	
	private BigInteger clienteId;
	@NotBlank
	//@NotNull
	@Size(max = 70)
	private String nomeClie;
	//@NotNull
	@Size(max = 15)
	private String sexo;
	//@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataNasci;
	//private EnderecoEnum enderecoEnum;
//	private ContatoEnum contatoEnum;
	private Set<Endereco> endereco;
	private Set<Contato> contato;
	
	public Set<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}
	public Set<Contato> getContato() {
		return contato;
	}
	public void setContato(Set<Contato> contato) {
		this.contato = contato;
	}
	public BigInteger getClienteId() {
		return clienteId;
	}
	public void setClienteId(BigInteger clienteId) {
		this.clienteId = clienteId;
	}
	public String getNomeClie() {
		return nomeClie;
	}
	public void setNomeClie(String nome) {
		this.nomeClie = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDataNasci() {
		return dataNasci;
	}
	public void setDataNasci(Date dataNasci) {
		this.dataNasci = dataNasci;
	}

	public Cliente toCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setNome(this.nomeClie);
		cliente.setSexo(this.sexo);
		cliente.setDataNasci(this.dataNasci);
		cliente.setEndereco(this.endereco);
		cliente.setContato(this.contato);

		return cliente;
	}
	public Cliente toCliente(Cliente cliente) {
		
		cliente.setNome(this.nomeClie);
		cliente.setSexo(this.sexo);
		cliente.setDataNasci(this.dataNasci);
		cliente.setEndereco(this.endereco);
		cliente.setContato(this.contato);

		return cliente;
	}
	public void fromCliente(Cliente cliente) {
		this.nomeClie = cliente.getNome();
		this.sexo = cliente.getSexo();
		this.dataNasci = cliente.getDataNasci();
		this.endereco = cliente.getEndereco();
		this.contato = cliente.getContato(); 

	}
}
