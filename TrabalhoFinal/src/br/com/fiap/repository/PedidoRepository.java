package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}