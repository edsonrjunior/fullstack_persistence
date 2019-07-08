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

import br.com.fiap.model.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Component
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Caching(put = { @CachePut(value = "ProdutoCache", key = "#produto.id") }, evict = {
			@CacheEvict(value = "allProdutosCache", allEntries = true) })
	public void add(Produto produto) {
		produtoRepository.save(produto);
	}

	@Transactional
	@Cacheable(value = "allProdutosCache", unless = "#result.size() == 0")
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Transactional
	@Caching(evict = { @CacheEvict(value = "allProdutosCache", allEntries = true) })
	public void addAll(Collection<Produto> produtos) {
		for (Produto produto : produtos) {
			produtoRepository.save(produto);
		}
	}

	@Transactional
	@Cacheable(value = "ClienteCache", key = "#nome")
	public List<Produto> findByName(String nome) {
		return produtoRepository.findByName(nome);
	}
}
