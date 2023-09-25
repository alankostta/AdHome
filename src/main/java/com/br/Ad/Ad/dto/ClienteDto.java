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
    private ContatoDto contato;
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
        cliente.setContato(this.contato.toContato());

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
        if (this.contato != null) {
            cliente.setContato(this.contato.toContato());
        }

        // Mapear endereços
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto : this.endereco) {
            enderecos.add(enderecoDto.toEndereco());
        }
        cliente.setEndereco(enderecos);

        return cliente;
    }
    public void fromClienteDto(Cliente cliente) {
    	
        this.id =  cliente.getId();
        this.nome = cliente.getNome();
        this.sexo = cliente.getSexo();
        this.anoRef = cliente.getAnoRef();
        this.dataAltera = cliente.getDataAltera();
        this.dataCadastro = cliente.getDataCadastro();
        this.dataNasci =  cliente.getDataNasci();
        Contato contato = new Contato();
        contato = cliente.getContato();
        this.contato.fromContatoDto(contato);
         
		
		 List<Endereco> enderecos = new ArrayList<>();
		 for (EnderecoDto enderecoDto : this.endereco) { 
			 enderecos.add(enderecoDto.toEndereco()); 
			 }

        cliente.setEndereco(enderecos);

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

	public ContatoDto getContato() {
		return contato;
	}

	public void setContato(ContatoDto contato) {
		this.contato = contato;
	}

	public List<EnderecoDto> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<EnderecoDto> endereco) {
		this.endereco = endereco;
	}
}