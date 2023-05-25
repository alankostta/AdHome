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
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id", nullable = false, length = 10, unique = true)
	private Long clienteId;

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

	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Contato> contato = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cliente_endereco", 
	joinColumns = @JoinColumn(name = "cliente_fk"), 
	inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private Set<Endereco> endereco;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Pedido> pedido = new HashSet<>();

	public Cliente() {

	}

	public Cliente(String nome, String sexo, 
			Date dataNasci, LocalDateTime dataCadastro, 
			LocalDateTime dataAltera, Integer anoRef) {
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDataNasci(dataNasci);
		this.setDataCadastro(dataCadastro);
		this.setContato(getContato());
		this.setEndereco(getEndereco());
		this.setPedido(getPedido());
		this.setDataAltera(dataAltera);
		this.setAnoRef(anoRef);
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
		if(endereco == null) {
			endereco = new HashSet<>();
		}
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
	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId + ", nome=" + nome + ", sexo=" + sexo + ", dataNasci=" + dataNasci
				+ ", anoRef=" + anoRef + ", dataCadastro=" + dataCadastro + ", dataAltera=" + dataAltera + ", contato="
				+ contato + ", endereco=" + endereco + ", pedido=" + pedido + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoRef, clienteId, contato, dataAltera, dataCadastro, dataNasci, endereco, nome, pedido,
				sexo);
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
		return Objects.equals(anoRef, other.anoRef) && Objects.equals(clienteId, other.clienteId)
				&& Objects.equals(contato, other.contato) && Objects.equals(dataAltera, other.dataAltera)
				&& Objects.equals(dataCadastro, other.dataCadastro) && Objects.equals(dataNasci, other.dataNasci)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(nome, other.nome)
				&& Objects.equals(pedido, other.pedido) && Objects.equals(sexo, other.sexo);
	}
}
