package com.br.AdHome.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Informe o telefone")
	@Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ']+$", message = "O número de telefone não deve conter simbolos")
	@Column(name = "tele_contato", nullable = false, length = 30)
	private String telefone;
	
	@NotBlank(message = "Informe o email")
	@Email(message = "Email invalido")
	@Column(name = "email_contato", nullable = true, length = 150)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private ContatoEnum contatoEnum;

	public Contato() {
		super();
	}
	public Contato(Long id, String telefone, String email,
			ContatoEnum contatoEnum) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.email = email;
		this.contatoEnum = contatoEnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContatoEnum getContatoEnum() {
		return contatoEnum;
	}

	public void setContatoEnum(ContatoEnum contatoEnum) {
		this.contatoEnum = contatoEnum;
	}
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Contato contato = (Contato) o;
	        return id != null && id.equals(contato.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }
}
