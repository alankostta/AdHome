package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fornecedor_endereco", 
		joinColumns = @JoinColumn(name = "fornecedor_fk"), 
		inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private List<Endereco> endereco = new ArrayList<>();
	/*
	 * Quando e criado um Set<> ele ao invés de criar uma lista de objetos ele cria
	 * um grupo único de objetos evitando ser criado várias instancias do mesmo
	 * objeto
	 */
	@OneToMany(mappedBy="fornecedor", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="produto_id")
	private Set<Produto> produtos = new HashSet<>();

	public Fornecedor() {

	}

	public Fornecedor(Long id, String nome, String nomeEmpresa, LocalDateTime dataCadastroForne, LocalDateTime dataAlteraForne,
			List<Endereco> endereco, List<Contato> contato, Set<Produto> produto, Integer anoRef) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setNomeEmpresa(nomeEmpresa);
		this.setEndereco(endereco);
		this.setContatos(contato);
		this.setProdutos(produto);
		this.setDataAlteraForne(dataAlteraForne);
		this.setDataCadastroForne(dataCadastroForne);
		this.setAnoRef(anoRef);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
}
