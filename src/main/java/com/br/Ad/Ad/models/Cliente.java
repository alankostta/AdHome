package com.br.Ad.Ad.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_clie", nullable = false, length = 70)
	private String nome;

	@Column(name = "sexo_clie", nullable = false, length = 30)
	private String sexo;

	@Column(name = "dataNasci_clie", nullable = false, length = 30)
	@Temporal(TemporalType.DATE)
	private Date dataNasci;

	@Column(name = "ano_ref", nullable = false)
	private Integer anoRef;

	@Column(name = "data_Cadastro", nullable = false, length = 30)
	private LocalDateTime dataCadastro;

	@Column(name = "data_Altera", length = 30, nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataAltera;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Contato> contato;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "cliente_endereco", 
	joinColumns = @JoinColumn(name = "cliente_fk"), 
	inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private List<Endereco> endereco;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
	private Set<Pedido> pedido;

	public Cliente() {

	}

	public Cliente(Long id, String nome, String sexo, Date dataNasci, Integer anoRef, LocalDateTime dataCadastro,
			LocalDateTime dataAltera, List<Contato> contato, List<Endereco> endereco, Set<Pedido> pedido) {
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

	public LocalDateTime getDataAltera() {
		return dataAltera;
	}

	public void setDataAltera(LocalDateTime dataAltera) {
		this.dataAltera = dataAltera;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public Date getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(Date dataNasci) {
		this.dataNasci = dataNasci;
	}

	public List<Contato> getContato() {
		if(contato == null) {
			contato = new ArrayList<>();
		}
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	public List<Endereco> getEndereco() {
		if(endereco == null) {
			endereco = new ArrayList<>();
		}
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<Pedido> getPedido() {
		if(pedido == null) {
			pedido = new HashSet<>();
		}
		return pedido;
	}

	public void setPedido(Set<Pedido> pedido) {
			this.pedido = pedido;	
	}

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}
	public void addEndereco(Endereco endereco) {
		if(endereco != null && getEndereco().contains(endereco)) {
			getEndereco().add(endereco);
		}
		if(!endereco.getCliente().contains(this)) {
			endereco.getCliente().add(this);
		}
	}
	public void addContato(Contato contato) {
		if (this.contato == null && getContato().contains(contato)) {
			this.contato = new ArrayList<>();
		}
		this.contato.add(contato);
	}
}
