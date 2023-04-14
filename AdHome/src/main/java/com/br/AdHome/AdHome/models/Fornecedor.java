package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fornecedor_id", nullable = false, length = 10, unique = true)
	private Long fornecedorId;
	
	@Column(name = "nome_fornecedor", nullable = false, length = 60)
	private String nome;
	
	@Column(name = "empresa_fornecedor", nullable = false, length = 80)
	private String nomeEmpresa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_cadastro", nullable = false, length = 60)
	private LocalDateTime dataCadastroForne;
	
	@Column(name = "ano_ref", nullable = false)
	private Integer anoRef;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_altera", nullable = false, length = 60)
	private LocalDateTime dataAlteraForne;
	
	@Column(name = "endereco_enum", nullable = false, length = 60)
	@Enumerated(EnumType.STRING)
	private EnderecoEnum enderecoEnum;
	
	@Column(name = "contato_enum", nullable = false, length = 60)
	@Enumerated(EnumType.STRING)
	private ContatoEnum contatoEnum;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "Fornecedor_Endereco", 
		joinColumns = @JoinColumn(name = "fornecedor_fk"), 
		inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private Set<Endereco> endereco = new HashSet<>();
	/*
	 * Quando e criado um Set<> ele ao invés de criar uma lista de objetos ele cria
	 * um grupo único de objetos evitando ser criado várias instancias do mesmo
	 * objeto
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.PERSIST)
	private Set<Contato> contatos = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.PERSIST)
	private Set<Produto> produtos = new HashSet<>();

	public Fornecedor() {

	}

	public Fornecedor(String nome, String nomeEmpresa, LocalDateTime dataCadastroForne, LocalDateTime dataAlteraForne,
			Set<Endereco> endereco, Set<Contato> contato, Set<Produto> produto, Integer anoRef) {
		super();
		this.setNome(nome);
		this.setNomeEmpresa(nomeEmpresa);
		this.setEndereco(endereco);
		this.setContatos(contato);
		this.setProdutos(produto);
		this.setDataAlteraForne(dataAlteraForne);
		this.setDataCadastroForne(dataCadastroForne);
		this.setAnoRef(anoRef);
	}

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public LocalDateTime getDataCadastroForne() {
		return dataCadastroForne;
	}

	public void setDataCadastroForne(LocalDateTime dataCadastroForne) {
		this.dataCadastroForne = dataCadastroForne;
	}

	public LocalDateTime getDataAlteraForne() {
		return dataAlteraForne;
	}

	public void setDataAlteraForne(LocalDateTime dataAlteraForne) {
		this.dataAlteraForne = dataAlteraForne;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
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

	@PreRemove
	private void removeAssociations() {
		for (Contato c : contatos) {
			c.setFornecedor(null);
		}
		contatos.clear();

		for (Endereco e : endereco) {
			e.getFornecedor().remove(this);
		}
		endereco.clear();
	}
	@Override
	public int hashCode() {
		return Objects.hash(contatoEnum, contatos, dataAlteraForne, dataCadastroForne, endereco, enderecoEnum,
				fornecedorId, nome, produtos, nomeEmpresa, anoRef);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return contatoEnum == other.contatoEnum && Objects.equals(contatos, other.contatos)
				&& Objects.equals(dataAlteraForne, other.dataAlteraForne)
				&& Objects.equals(dataCadastroForne, other.dataCadastroForne)
				&& Objects.equals(endereco, other.endereco) && enderecoEnum == other.enderecoEnum
				&& Objects.equals(fornecedorId, other.fornecedorId) && Objects.equals(nome, other.nome)
				&& Objects.equals(produtos, other.produtos) && Objects.equals(nomeEmpresa, other.nomeEmpresa)
				&& Objects.equals(anoRef, other.anoRef);
	}

	@Override
	public String toString() {
		return "Fornecedor [fornecedorId=" + fornecedorId + ", nome=" + nome + ", nomeEmpresa=" + nomeEmpresa
				+ ", dataCadastroForne=" + dataCadastroForne + ", dataAlteraForne=" + dataAlteraForne + "anoRef="
				+ anoRef + ", enderecoEnum=" + enderecoEnum + ", contatoEnum=" + contatoEnum + ", endereco=" + endereco
				+ ", contatos=" + contatos + ", produtos=" + produtos + "]";
	}
}
