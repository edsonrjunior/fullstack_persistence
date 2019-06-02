package br.com.edson.principal;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.edson.entity.Cliente;
import br.com.edson.entity.Endereco;
import br.com.edson.entity.Item;
import br.com.edson.entity.Pedido;
import br.com.edson.helper.VendasHelper;
import br.com.edson.pk.PedidosPK;

public class Programa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaVendas");
		EntityManager em = emf.createEntityManager();

		VendasHelper dao = new VendasHelper(em);

		try {
			// Definindo o Cliente
			Cliente cliente = new Cliente();
			cliente.setId(10);
			cliente.setEmpresa("Itaú");

			// Definindo o Endereços
			Endereco endereco = new Endereco();
			endereco.setRua("Rua dos Pinhais, 1200");
			endereco.setCidade("São Paulo");
			endereco.setCep("04500-000");
			endereco.setCliente(cliente);

			// Definindo o Pedido
			PedidosPK pedidosPK = new PedidosPK();
			pedidosPK.setCodigo(100);
			pedidosPK.setCategoria("Livros");

			Pedido pedido = new Pedido();
			pedido.setDataPedido(new Date());
			pedido.setPedidoPK(pedidosPK);
			pedido.setCliente(cliente);

			// Definindo dois Itens
			Item item1 = new Item();
			item1.setQuantidade(2);

			Item item2 = new Item();
			item2.setQuantidade(3);

			// Fazendo as associações
			pedido.getItens().add(item1);
			pedido.getItens().add(item2);

			cliente.getEnderecos().add(endereco);
			cliente.getPedidos().add(pedido);

			dao.Salvar(cliente);
			JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso.");

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
	}

}
