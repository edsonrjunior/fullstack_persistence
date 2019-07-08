package br.com.fiap.service;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.Endereco;
import br.com.fiap.repository.EnderecoRepository;

@Component
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Caching(put = { @CachePut(value = "EnderecoCache", key = "#endereco.id") }, evict = {
			@CacheEvict(value = "allEnderecosCache", allEntries = true) })
	public void add(Endereco endereco) {
		enderecoRepository.save(endereco);
	}

	@Transactional
	@Cacheable(value = "allEnderecosCache", unless = "#result.size() == 0")
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	@Transactional
	@Caching(evict = { @CacheEvict(value = "allEnderecosCache", allEntries = true) })
	public void addAll(Collection<Endereco> enderecos) {
		for (Endereco endereco : enderecos) {
			enderecoRepository.save(endereco);
		}
	}

}
