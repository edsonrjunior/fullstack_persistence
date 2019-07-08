package br.com.fiap.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EstoquePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public EstoquePK() {
		
	}
	
	public EstoquePK(Integer id, Produto produto){
		this.id = id;
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
