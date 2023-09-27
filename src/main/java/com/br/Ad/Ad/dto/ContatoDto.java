package com.br.Ad.Ad.dto;

import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.models.ContatoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ContatoDto {
	
	private Long id = 0L;
	
    @NotBlank
    @Size(max = 30)
    private String telefone;

    @Size(max = 150)
    @Email
    private String email;

    private ContatoEnum contatoEnum;

    public ContatoEnum getContatoEnum() {
        return contatoEnum;
    }

    public void setContatoEnum(ContatoEnum contatoEnum) {
        this.contatoEnum = contatoEnum;
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

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contato toContato() {
        Contato contato = new Contato();
        contato.setId(this.id);
        contato.setEmail(this.email);
        contato.setTelefone(this.telefone);
        contato.setContatoEnum(this.contatoEnum);
        return contato;
    }
	public Contato toContato(Contato contato) {
        contato.setId(this.id);
        contato.setEmail(this.email);
        contato.setTelefone(this.telefone);
        contato.setContatoEnum(this.contatoEnum);
        return contato;
    }
    public void fromContato(Contato contato) {
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setId(contato.getId());
        contatoDto.setEmail(contato.getEmail());
        contatoDto.setTelefone(contato.getTelefone());
        contatoDto.setContatoEnum(contato.getContatoEnum());
    }
    public Contato fromContatoDto(Contato contato) {
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setId(contato.getId());
        contatoDto.setEmail(contato.getEmail());
        contatoDto.setTelefone(contato.getTelefone());
        contatoDto.setContatoEnum(contato.getContatoEnum());
        
        return contato;
    }
}
