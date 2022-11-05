package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id",nullable = false,  length = 10, unique = true)
	private Long clienteId;
	@Column(name = "nome_clie",nullable = false,  length = 70)
	private String nome;
	@Column(name = "sexo_clie",nullable = false,  length = 30)
	private String sexo;
	@Column(name = "dataNasci_clie",nullable = false, length = 30)
	@Temporal(TemporalType.DATE)
	private Date dataNasci;
	@Column(name = "ano_ref",nullable = false)
	private Integer anoRef;
	@Column(name = "data_Cadastro",nullable = false, length = 30)
	private LocalDateTime dataCadastro;
	@Column(name = "data_Altera", length = 30, nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime dataAltera;
	@Enumerated(EnumType.STRING)
	private EnderecoEnum enderecoEnum;
	@Enumerated(EnumType.STRING)
	private ContatoEnum contatoEnum;
	
	@OneToMany(mappedBy="cliente",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Contato> contato  = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "Cliente_Endereco",
			joinColumns = @JoinColumn(name = "cliente_fk", nullable = true),
			inverseJoinColumns = @JoinColumn(name = "endereco_fk", nullable = true))
	private Set<Endereco> endereco = new HashSet<>();

	@OneToMany(mappedBy="cliente",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<Pedido> pedido = new HashSet<>();

	public Cliente() {
		
	}
	public Cliente(String nome, String sexo, Date dataNasci, LocalDateTime dataCadastro,
			LocalDateTime dataAtera, Integer anoRef) {
		super();
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDataNasci(dataNasci);
		this.setDataCadastro(dataCadastro);
		this.setContato(getContato());
		this.setEndereco(getEndereco());
		this.setPedido(getPedido());
		this.setDataAltera(dataAltera);
		this.setAnoRef(anoRef);
		this.setEnderecoEnum(getEnderecoEnum());
		this.setContatoEnum(getContatoEnum());
	
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
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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
	
	public Set<Contato> getContato() {
		return contato;
	}
	public void setContato(Set<Contato> contato) {
		this.contato = contato;
	}
	public Set<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}
	public Set<Pedido> getPedido() {
		return pedido;
	}
	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
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
	public Integer getAnoRef() {
		return anoRef;
	}
	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clienteId, contato, contatoEnum, dataCadastro, dataAltera, dataNasci, endereco, enderecoEnum, nome,
				pedido, sexo, anoRef);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(clienteId, other.clienteId) && Objects.equals(contato, other.contato)
				&& contatoEnum == other.contatoEnum && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataAltera, other.dataAltera) && Objects.equals(dataNasci, other.dataNasci)
				&& Objects.equals(endereco, other.endereco) && enderecoEnum == other.enderecoEnum
				&& Objects.equals(nome, other.nome) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(sexo, other.sexo) && Objects.equals(anoRef, other.anoRef);
	}
}
