package br.com.edson.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.edson.entity.Funcionario;

public class VendasHelper {

	private EntityManager em;

	public VendasHelper(EntityManager em) {
		this.em = em;
	}

	public void Salvar(Funcionario funcionario) {
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());

		}
	}

	//Usando Query
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionarios() {
		Query query = em.createQuery("select f from Funcionario f");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Funcionario buscarFuncionario(String numMatricula) {
		Query query = em.createQuery("select f from Funcionario f where matricula = :matricula");
		query.setParameter("matricula", numMatricula);
		Funcionario f = (Funcionario) query.getSingleResult();
		return f;
	}
	
	//JPQL
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodos(){
		Query query = em.createNamedQuery("Funcionario.findAll");
		return query.getResultList();
	}
	

}
