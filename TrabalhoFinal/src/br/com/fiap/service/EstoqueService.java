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

import br.com.fiap.model.Estoque;
import br.com.fiap.repository.EstoqueRepository;

@Component
public class EstoqueService {
	@Autowired
	private EstoqueRepository estoqueRepository;


	@Transactional
	@Cacheable(value = "allEstoquesCache", unless = "#result.size() == 0")
	public List<Estoque> findAll() {
		return estoqueRepository.findAll();
	}

	@Caching(put = { @CachePut(value = "EstoqueCache", key = "#estoque.id") }, evict = {
			@CacheEvict(value = "allEstoquesCache", allEntries = true) })
	public void add(Estoque estoque) {
		estoqueRepository.save(estoque);
	}
	
	@Transactional
	@Caching(evict = { @CacheEvict(value = "allEstoquesCache", allEntries = true) })
	public void addAll(Collection<Estoque> estoques) {
		for (Estoque estoque : estoques) {
			estoqueRepository.save(estoque);
		}
	}

}
