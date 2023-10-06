package com.br.Ad.Ad.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.Ad.Ad.dto.ClienteDto;
import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.repositories.ClienteDtoMappers;
import com.br.Ad.Ad.repositories.ClienteRepository;
import com.br.Ad.Ad.repositories.ContatoRepository;
import jakarta.transaction.Transactional;

/* Conhecida como camada Beans
 * Service: realiza opeações de negócio.
 * Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
 * executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
 * enviar e-mail]
 */
@Service
@Transactional
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	ContatoRepository contatoRepository;
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	public void updateClienteDto(ClienteDto clienteDto) {
	    // Use orElse(null) para obter o Cliente ou null caso não seja encontrado
	    Cliente cliente = clienteRepository.findById(clienteDto.getId()).orElse(null);

	    if (cliente != null) {
	        // Atualize os campos do objeto Cliente a partir do ClienteDto usando o Mapper
	        ClienteDtoMappers.INSTANCE.updateClienteFromDto(clienteDto, cliente);

	        // Salve o cliente atualizado no repositório
	        clienteRepository.save(cliente);
	    } else {
	        // Lide com o caso em que o cliente não foi encontrado (por exemplo, lançando uma exceção)
	        // Você pode lançar uma exceção, retornar uma resposta de erro, etc.
	        // Exemplo: throw new EntityNotFoundException("Cliente não encontrado com o ID: " + clienteDto.getId());
	    }
	}
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	public void deleteCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	public List<Cliente>  findByNameContaining(String nome){
		return clienteRepository.findByNomeContaining(nome);
	}
	public Optional<Cliente> findClienteEndereco(Long id) {
		return clienteRepository.findClienteEnderecoById(id);
	}
	public List<Cliente> clienteProjecao(){
		List<Cliente> cliente = clienteRepository.projecaoCliente();
		return cliente;
	}
}
