package com.br.Ad.Ad.dto;

import java.math.BigInteger;
import java.util.List;

import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.models.ContatoEnum;
import com.br.Ad.Ad.models.Endereco;
import com.br.Ad.Ad.models.EnderecoEnum;
import com.br.Ad.Ad.models.Fornecedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class FornecedorDto{

	private BigInteger id;
	@NotBlank
	@NotNull
	@Size(max = 60)
	private String nome;
	@NotBlank
	@NotNull
	@Size(max = 80)
	private String nomeEmpresa;
	private EnderecoEnum enderecoEnum;
	private ContatoEnum contatoEnum;
	private List<Endereco> endereco;
	private Contato contato;
	
	public FornecedorDto() {
		super();
	}
	public FornecedorDto(BigInteger id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public BigInteger getId() {
		return id;
	}
	public void setFornecedorId(BigInteger id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public EnderecoEnum getEnderecoEnum() {
		return enderecoEnum;
	}
	public void setEnderecoEnum(EnderecoEnum enderecoEnum) {
		this.enderecoEnum = enderecoEnum;
	}
	public ContatoEnum getContatoEnum() {
		return contatoEnum;
	}
	public void setContatoEnum(ContatoEnum contatoEnum) {
		this.contatoEnum = contatoEnum;
	}
	public List<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
public Fornecedor toFornecedor() {
		
		Fornecedor fornecedor = new Fornecedor();
		//fornecedor.setFornecedorId(this.fornecedorId.longValue());
		fornecedor.setNome(this.nome);
		fornecedor.setNomeEmpresa(nomeEmpresa);
		fornecedor.setEndereco(this.endereco);
		fornecedor.setContatos(this.contato);
		return fornecedor;
	}
	public Fornecedor toFornecedor(Fornecedor fornecedor) {
		//fornecedor.setFornecedorId(this.fornecedorId.longValue());
		fornecedor.setNome(this.nome);
		fornecedor.setNomeEmpresa(nomeEmpresa);
		fornecedor.setEndereco(this.endereco);
		fornecedor.setContatos(this.contato);
		return fornecedor;
		
	}

	public void fromFornecedor(Fornecedor fornecedor) {
		this.id = BigInteger.valueOf(fornecedor.getId());
		this.nome = fornecedor.getNome();
		this.nomeEmpresa = fornecedor.getNomeEmpresa();
		this.endereco = fornecedor.getEndereco();
		this.contato = fornecedor.getContatos(); 
	}
}
