package br.com.edson.helper;

import javax.persistence.EntityManager;

import br.com.edson.entity.Cliente;

public class VendasHelper {

	private EntityManager entityManager;

	public VendasHelper(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void Salvar(Cliente cliente) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception exception) {
			entityManager.getTransaction().rollback();
		}

	}

}
