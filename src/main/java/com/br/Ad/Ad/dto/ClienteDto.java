package com.br.Ad.Ad.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.models.Endereco;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ClienteDto {

    private Long id;
    private String nome;
    private String sexo;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNasci;
    private Integer anoRef;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAltera;
    private List<ContatoDto> contato = new ArrayList<>();
    private List<EnderecoDto> endereco = new ArrayList<>();
 
    public ClienteDto() {
        super();
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setSexo(this.sexo);
        cliente.setDataNasci(this.dataNasci);
        cliente.setAnoRef(this.anoRef);
        cliente.setDataCadastro(this.dataCadastro);
        cliente.setDataAltera(this.dataAltera);
        // Mapear contatos
        List<Contato> contatos = new ArrayList<>();
        for (ContatoDto contatoDto : this.contato) {
            contatos.add(contatoDto.toContato());
        }
        cliente.setContato(contatos);

        // Mapear endereços
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto : this.endereco) {
            enderecos.add(enderecoDto.toEndereco());
        }
        cliente.setEndereco(enderecos);

        return cliente;
    }
    public Cliente toCliente(Cliente cliente) {
       
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setSexo(this.sexo);
        cliente.setDataNasci(this.dataNasci);
        cliente.setAnoRef(this.anoRef);
        cliente.setDataCadastro(this.dataCadastro);
        cliente.setDataAltera(this.dataAltera);

        // Mapear contatos
        List<Contato> contatos = new ArrayList<>();
        for (ContatoDto contatoDto : this.contato) {
            contatos.add(contatoDto.toContato());
        }
        cliente.setContato(contatos);

        // Mapear endereços
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto : this.endereco) {
            enderecos.add(enderecoDto.toEndereco());
        }
        cliente.setEndereco(enderecos);

        return cliente;
    }
    public Cliente toClienteDto() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setSexo(this.sexo);
        cliente.setDataNasci(this.dataNasci);
        cliente.setAnoRef(this.anoRef);
        cliente.setDataCadastro(this.dataCadastro);
        cliente.setDataAltera(this.dataAltera);
          
        if (this.contato != null) {
            // Mapear contatos
            List<Contato> contatos = new ArrayList<>();
            for (ContatoDto contatoDto : this.contato) {
                contatos.add(contatoDto.toContato());
            }
            cliente.setContato(contatos);
        } else {
            cliente.setContato(new ArrayList<>()); // Inicialize como uma lista vazia
        }

        // Mapear endereços
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto : this.endereco) {
            enderecos.add(enderecoDto.toEndereco());
        }
        cliente.setEndereco(enderecos);

        return cliente;
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

	public Date getDataNasci() {
		return dataNasci;
	}

	public void setDataNasci(Date dataNasci) {
		this.dataNasci = dataNasci;
	}

	public Integer getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(Integer anoRef) {
		this.anoRef = anoRef;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAltera() {
		return dataAltera;
	}

	public void setDataAltera(LocalDateTime dataAltera) {
		this.dataAltera = dataAltera;
	}

	public List<ContatoDto> getContato() {
		return contato;
	}

	public void setContato(List<ContatoDto> contato) {
		this.contato = contato;
	}

	public List<EnderecoDto> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<EnderecoDto> endereco) {
		this.endereco = endereco;
	}
}