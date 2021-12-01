package com.app.demo;

import com.app.demo.domain.Categoria;
import com.app.demo.domain.Cliente;
import com.app.demo.domain.Pedido;
import com.app.demo.domain.Produto;
import com.app.demo.repositories.CategoriaRepository;
import com.app.demo.repositories.ClienteRepository;
import com.app.demo.repositories.PedidoRepository;
import com.app.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("BANCO RODANDO");
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Categoria cat1 = new Categoria(null, "Categoria 1", 10);
		Categoria cat2 = new Categoria(null, "Categoria 2", 20);

		Produto prod1 = new Produto(23.5, "Arroz", null);
		Produto prod2 = new Produto(25, "Feijão", null);

		Cliente cliente1 = new Cliente(null, "Erick Augusto", "12345678900", "erick@email.com", "12345");

		Pedido pedido1 = new Pedido(null,date.parse("23/11/2021 15:24"));

		cat1.getProdutos().addAll(Arrays.asList(prod2, prod1));
		cat2.getProdutos().addAll(Arrays.asList(prod1));
		prod1.getCategorias().addAll(Arrays.asList(cat2, cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1));

		prod1.setPedido(pedido1);
		prod2.setPedido(pedido1);
		pedido1.getProdutos().addAll(Arrays.asList(prod1, prod2));

		cliente1.getPedido().addAll(Arrays.asList(pedido1));
		pedido1.setCliente(cliente1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		pedidoRepository.saveAll(Arrays.asList(pedido1));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2));

	}
}
