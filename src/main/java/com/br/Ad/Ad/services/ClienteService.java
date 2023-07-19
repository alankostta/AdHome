package com.br.Ad.Ad.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.br.Ad.Ad.models.Cliente;
import com.br.Ad.Ad.repositories.ClienteRepository;
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
	
	final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
		
	}
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
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
	public List<Object[]> findClienteEndereco(Long id) {
		return clienteRepository.findClienteEnderecoById(id);
	}
	public List<Cliente> clienteProjecao(){
		List<Cliente> cliente = clienteRepository.projecaoCliente();
		return cliente;
	}
}
