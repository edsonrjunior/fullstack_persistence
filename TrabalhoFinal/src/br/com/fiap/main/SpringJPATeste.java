package br.com.fiap.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.model.Cliente;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.Estoque;
import br.com.fiap.model.ItemPedido;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import br.com.fiap.service.ClienteService;
import br.com.fiap.service.EnderecoService;
import br.com.fiap.service.EstoqueService;
import br.com.fiap.service.PedidoService;
import br.com.fiap.service.ProdutoService;

public class SpringJPATeste {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("resources/spring.xml");

		ClienteService clienteService = ctx.getBean(ClienteService.class);
		EnderecoService enderecoService = ctx.getBean(EnderecoService.class);
  		ProdutoService produtoService = ctx.getBean(ProdutoService.class);
		EstoqueService estoqueService = ctx.getBean(EstoqueService.class);
		PedidoService pedidoService = ctx.getBean(PedidoService.class);
		
		ArrayList<ItemPedido> itensP1 = new ArrayList<ItemPedido>();
		ArrayList<ItemPedido> itensP2 = new ArrayList<ItemPedido>();
		ArrayList<ItemPedido> itensP3 = new ArrayList<ItemPedido>();

		// Criando os clientes
		Cliente cliente1 = new Cliente("Edson Raimundo Jr", "999.999.999-99", "94834-1617");
		Cliente cliente2 = new Cliente("Mariana Silva", "888.999.999-99", "94834-8788");
		Cliente cliente3 = new Cliente("Katia Antes", "888.999.777-99", "94838-7627");

		// Salvando os clientes no banco
		System.out.println("Salvando os clientes no banco");
		clienteService.addAll(Arrays.asList(cliente1, cliente2, cliente3));
		
		//Criando os enderecos
		Endereco end1 = new Endereco ("Rua Jaguare, 999", 333, 05347010, "Sao Paulo", "SP", cliente1);
		Endereco end2 = new Endereco ("Rua Quinze, 444", 335, 87888654, "Osasco", "SP", cliente2);
		Endereco end3 = new Endereco ("Rua Onze, 444", 735, 89888654, "Rio de Janeiro", "RJ", cliente3);

		//Salvando os enderecos
		enderecoService.addAll(Arrays.asList(end1, end2, end3));
		
		// Criando os produtos
		Produto produto1 = new Produto(1, "TV", 2500.00);
		Produto produto2 = new Produto(2, "Geladeira", 2800.00);
		Produto produto3 = new Produto(3, "Microondas", 400.00);
		Produto produto4 = new Produto(4, "Notebook", 3500.00);

		// Salvando os produtos no banco
		System.out.println("Salvando os produtos no banco");
		produtoService.addAll(Arrays.asList(produto1, produto2, produto3, produto4));

		// Criando os items do estoque
		Estoque item1 = new Estoque(1, produto1, 100);
		Estoque item2 = new Estoque(2, produto1, 0);
		Estoque item3 = new Estoque(1, produto2, 150);
		Estoque item4 = new Estoque(2, produto2, 50);
		Estoque item5 = new Estoque(1, produto3, 160);
		Estoque item6 = new Estoque(1, produto4, 1000);

		//Salvando os estoques no banco
		System.out.println("Salvando os estoques no banco");
		estoqueService.addAll(Arrays.asList(item1, item2, item3, item4, item5, item6));
		
		// Criando os pedidos
		Pedido pedido1 = new Pedido(cliente1, new Date());
		Pedido pedido2 = new Pedido(cliente2, new Date());
		Pedido pedido3 = new Pedido(cliente3, new Date());

		// Criando os itens do pedido
		ItemPedido item1Pedido1 = new ItemPedido(pedido1, produto1, 11);
		ItemPedido item2Pedido1 = new ItemPedido(pedido1, produto2, 21);
		ItemPedido item1Pedido2 = new ItemPedido(pedido2, produto2, 12);
		ItemPedido item1Pedido3 = new ItemPedido(pedido3, produto3, 13);

		// Salvando os itens do pedido no pedido
		itensP1.add(item1Pedido1);
		itensP1.add(item2Pedido1);
		pedido1.setItens(itensP1);

		itensP2.add(item1Pedido2);
		pedido2.setItens(itensP2);
	
		itensP3.add(item1Pedido3);
		pedido3.setItens(itensP3);
				
		//Salvando os pedidos no banco
		System.out.println("Salvando os pedidos no banco");
		pedidoService.addAll(Arrays.asList(pedido1, pedido2, pedido3));				

		System.out.println("Banco criado!");
		
		//Fazendo consultas - Teste do cache
		System.out.println("Fazendo consulta a lista de clientes");
		ArrayList<Cliente> cli = (ArrayList<Cliente>) clienteService.findAll();

		cli.forEach(e ->
				System.out.println(e.getNome()));

		System.out.println("Fazendo segunda consulta a lista de clientes");
		ArrayList<Cliente> cli2 = (ArrayList<Cliente>) clienteService.findAll();

		cli2.forEach(e ->
				System.out.println(e.getNome()));

		ctx.close();		
		
	}
}
