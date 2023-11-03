package com.br.AdHome.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ']+$", message = "O nome não pode conter números ou símbolos")
	@NotBlank(message = "Informe o nome")
	@Size(min = 3, max = 70, message = "O campo nome precisa ter no minimo {min} digitos ou no maxímo {max}")
	@Column(name = "nome_clie", nullable = false, length = 70)
	private String nome;

	@NotNull(message = "Selecione o sexo")
	@Size(min = 1, max = 30, message = "Sexo invalido")
	@Column(name = "sexo_clie", nullable = false, length = 30)
	private String sexo;

	@NotNull(message = "Informe a data de nascimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataNasci_clie", nullable = false, length = 30)
	@Temporal(TemporalType.DATE)
	private LocalDate dataNasci;

	// @NotNull(message = "Atributo é obrigatório")
	@Column(name = "ano_ref", nullable = false)
	private Integer anoRef;

	// @NotNull(message = "Atributo data de cadastro é obrigatório")
	@Column(name = "data_Cadastro", nullable = false, length = 30)
	private LocalDateTime dataCadastro;

	// @NotNull(message = "Campo data de alterção é obrigatório")
	@Column(name = "data_Altera", length = 30, nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataAltera;

	@Valid
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Contato.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "contato_id")
	private Contato contato;

	@Valid
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "cliente_endereco", joinColumns = @JoinColumn(name = "cliente_fk"), inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private List<Endereco> endereco;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	@JoinColumn(name = "cliente_id")
	private Set<Pedido> pedido;

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String sexo, LocalDate dataNasci, Integer anoRef, LocalDateTime dataCadastro,
			LocalDateTime dataAltera, Contato contato, List<Endereco> endereco, Set<Pedido> pedido) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNasci = dataNasci;
		this.anoRef = anoRef;
		this.dataCadastro = dataCadastro;
		this.dataAltera = dataAltera;
		this.contato = contato;
		this.endereco = endereco;
		this.pedido = pedido;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(LocalDate dataNasci) {

		this.dataNasci = dataNasci;
	}

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		if (anoRef == null) {
			Calendar cal = Calendar.getInstance();
			anoRef = cal.get(Calendar.YEAR);
		}
		this.anoRef = anoRef;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		if (dataCadastro == null) {
			dataCadastro = LocalDateTime.now(ZoneId.of("UTC"));
		}
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAltera() {
		return dataAltera;
	}

	public void setDataAltera(LocalDateTime dataAltera) {
		if (dataAltera == null) {
			dataAltera = LocalDateTime.now(ZoneId.of("UTC"));
		}
		this.dataAltera = dataAltera;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}
}
