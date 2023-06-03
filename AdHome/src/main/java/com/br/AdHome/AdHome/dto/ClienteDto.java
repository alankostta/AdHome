package com.br.AdHome.AdHome.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.models.Endereco;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ClienteDto {
    private BigInteger clienteId;
    
    @NotBlank
    @Size(max = 70)
    private String nomeClie;
    
    @Size(max = 15)
    private String sexo;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dataNasci;
    
    private Set<Endereco> listEndereco;
    
    private Set<Contato> listContato;
    
   
    public BigInteger getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }
    
    public String getNomeClie() {
        return nomeClie;
    }
    
    public void setNomeClie(String nomeClie) {
        this.nomeClie = nomeClie;
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
    
    public Set<Endereco> getListEndereco() {
        return listEndereco;
    }
    
    public void setListEndereco(Set<Endereco> listEndereco) {
        this.listEndereco = listEndereco;
    }
    
    public Set<Contato> getListContato() {
        return listContato;
    }
    
    public void setListContato(Set<Contato> listContato) {
        this.listContato = listContato;
    }
     
    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nomeClie);
        cliente.setSexo(this.sexo);
        cliente.setDataNasci(this.dataNasci);
        cliente.setEndereco(this.listEndereco);
        cliente.setContato(this.listContato);
        return cliente;
    }
    
    public Cliente toCliente(Cliente cliente) {
        cliente.setNome(this.nomeClie);
        cliente.setSexo(this.sexo);
        cliente.setDataNasci(this.dataNasci);
        cliente.setEndereco(this.listEndereco);
        cliente.setContato(this.listContato);
        return cliente;
    }
    
    public void fromCliente(Cliente cliente) {
        this.nomeClie = cliente.getNome();
        this.sexo = cliente.getSexo();
        this.dataNasci = cliente.getDataNasci();
        this.listEndereco = cliente.getEndereco();
        this.listContato = cliente.getContato();
    }
}
