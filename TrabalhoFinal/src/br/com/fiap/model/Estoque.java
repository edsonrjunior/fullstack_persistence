package br.com.fiap.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstoquePK id;

	private Integer quantidade;

	public Estoque() {
	}

	public Estoque(Integer id, Produto produto, Integer quantidade) {
		this.id = new EstoquePK(id, produto);
		this.quantidade = quantidade;
	}

	public EstoquePK getId() {
		return id;
	}

	public void setId(EstoquePK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}