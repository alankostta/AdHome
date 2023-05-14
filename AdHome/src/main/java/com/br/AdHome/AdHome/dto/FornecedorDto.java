package com.br.AdHome.AdHome.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.models.ContatoEnum;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.models.EnderecoEnum;
import com.br.AdHome.AdHome.models.Fornecedor;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class FornecedorDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BigInteger fornecedorId;
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
	private Set<Endereco> endereco;
	private Set<Contato> contato;
	@Autowired
	ModelMapper modelMapper;
	
	public FornecedorDto() {
		super();
	}
	public FornecedorDto(BigInteger fornecedorId, String nome) {
		super();
		this.fornecedorId = fornecedorId;
		this.nome = nome;
	}
	public BigInteger getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(BigInteger fornecedorId) {
		this.fornecedorId = fornecedorId;
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
	public FornecedorDto toFornecedorDto(Fornecedor fornecedor) {
		return modelMapper.map(fornecedor, FornecedorDto.class);
	}
	public void fromFornecedor(Fornecedor fornecedor) {
		this.fornecedorId = BigInteger.valueOf(fornecedor.getFornecedorId());
		this.nome = fornecedor.getNome();
		this.nomeEmpresa = fornecedor.getNomeEmpresa();
		this.endereco = fornecedor.getEndereco();
		this.contato = fornecedor.getContatos(); 
	}
}
