package br.com.fiap.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String cpf;
	private String celular;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

	public Cliente() {

	}

	public Cliente(String nome, String cpf, String celular) {
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void addPedidos(Pedido pedido) {
		pedidos.add(pedido);
	}

	public void removePedidos(Pedido pedido) {
		pedidos.remove(pedido);
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void addEnderecos(Endereco endereco) {
		enderecos.add(endereco);
	}

	public void removeEnderecos(Endereco endereco) {
		enderecos.remove(endereco);
	}


}
