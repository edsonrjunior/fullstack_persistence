package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {


}