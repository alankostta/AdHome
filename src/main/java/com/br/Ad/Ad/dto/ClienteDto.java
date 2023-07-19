package com.br.Ad.Ad.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.models.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ClienteDto {

	private BigInteger id;
	@NotBlank
	// @NotNull
	@Size(max = 70)
	private String nomeClie;
	// @NotNull
	@Size(max = 15)
	private String sexo;
	// @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNasci;

	private List<Endereco> endereco = new ArrayList<>();

	private List<Contato> contato = new ArrayList<>();

	public ClienteDto() {
		super();
	}

	public ClienteDto(BigInteger id, @NotBlank @Size(max = 70) String nomeClie, @Size(max = 15) String sexo,
			Date dataNasci, List<Endereco> endereco, List<Contato> contato) {
		super();
		this.id = id;
		this.nomeClie = nomeClie;
		this.sexo = sexo;
		this.dataNasci = dataNasci;
		this.endereco = endereco;
		this.contato = contato;
	}
	
	public ClienteDto(BigInteger id, @NotBlank @Size(max = 70) String nomeClie, @Size(max = 15) String sexo,
			Date dataNasci, Endereco endereco, Contato contato) {
		super();
		this.id = id;
		this.nomeClie = nomeClie;
		this.sexo = sexo;
		this.dataNasci = dataNasci;
		 if (endereco != null) {
	            this.endereco.add(endereco);
	        }
	        if (contato != null) {
	            this.contato.add(contato);
	        }
	}

	public void addEndereco(Endereco endereco) {
		if (this.endereco == null) {
			this.endereco = new ArrayList<>();
		}
		this.endereco.add(endereco);
	}
	
	// Método para adicionar um objeto Contato à lista de contatos
	public void addContato(Contato contato) {
		if (this.contato == null) {
			this.contato = new ArrayList<>();
		}
		this.contato.add(contato);
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Contato> getContato() {
		return contato;
	}

	public void setContato(List<Contato> contato) {
		this.contato = contato;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

		if (this.contato != null) {
			List<Contato> contatos = new ArrayList<>();
			for (Contato contatoDto : this.contato) {
				Contato contato = new Contato();
				contato.setContatoEnum(contatoDto.getContatoEnum());
				contato.setEmail(contatoDto.getEmail());
				contato.setTelefone(contatoDto.getTelefone());
				// Defina outros atributos do contato, se houver
				contatos.add(contato);
			}
			cliente.setContato(contatos);
		}
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
