package com.br.AdHome.models;

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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ']+$", message = "O nome não pode conter números ou símbolos")
	@NotBlank(message = "Informe o nome")
	@Size(min = 3, max = 70, message = "O campo nome precisa ter no minimo {min} digitos ou no maxímo {max}")
	@Column(name = "nome_fornecedor", nullable = false, length = 70)
	private String nome;
	
	@NotBlank(message = "Informe o nome da empresa")
	@Size(min = 1, max = 70, message = "O campo empresa precisa ter no minimo {min} digitos ou no maxímo {max}")
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
	
	@Valid
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name = "fornecedor_endereco", 
		joinColumns = @JoinColumn(name = "fornecedor_fk"), 
		inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private List<Endereco> endereco = new ArrayList<>();
	/*
	 * Quando e criado um Set<> ele ao invés de criar uma lista de objetos ele cria
	 * um grupo único de objetos evitando ser criado várias instancias do mesmo
	 * objeto
	 */
	@Valid
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contato_id")
	private Contato contato;
	
	@Valid
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fornecedor_id")
	private Set<Produto> produtos = new HashSet<>();

	public Fornecedor() {
		super();
	}

	public Fornecedor(Long id, String nome, String nomeEmpresa, LocalDateTime dataCadastroForne, Integer anoRef,
			LocalDateTime dataAlteraForne, List<Endereco> endereco, Contato contato, Set<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeEmpresa = nomeEmpresa;
		this.dataCadastroForne = dataCadastroForne;
		this.anoRef = anoRef;
		this.dataAlteraForne = dataAlteraForne;
		this.endereco = endereco;
		this.contato = contato;
		this.produtos = produtos;
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

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
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

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
}
