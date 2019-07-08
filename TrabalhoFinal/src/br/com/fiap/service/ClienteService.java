package br.com.fiap.service;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.Cliente;
import br.com.fiap.repository.ClienteRepository;

@Component
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Caching(put = { @CachePut(value = "ClienteCache", key = "#cliente.id") }, evict = {
			@CacheEvict(value = "allClientesCache", allEntries = true) })
	public void add(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Cacheable(value = "allClientesCache", unless = "#result.size() == 0")
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional
	@Caching(evict = { @CacheEvict(value = "allClientesCache", allEntries = true) })
	public void addAll(Collection<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			clienteRepository.save(cliente);
		}
	}

	@Cacheable(value = "ClienteCache", key = "#nome")
	public List<Cliente> findByName(String nome) {
		return clienteRepository.findByName(nome);
	}
}
