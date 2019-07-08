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

import br.com.fiap.model.Pedido;
import br.com.fiap.repository.PedidoRepository;

@Component
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Caching(put = { @CachePut(value = "PedidoCache", key = "#pedido.id") }, evict = {
			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public void add(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	@Transactional
	@Cacheable(value = "allPedidosCache", unless = "#result.size() == 0")
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Transactional
	@Caching(evict = { @CacheEvict(value = "allPedidosCache", allEntries = true) })
	public void addAll(Collection<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedidoRepository.save(pedido);
		}
	}

}
