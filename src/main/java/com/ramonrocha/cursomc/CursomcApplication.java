package com.ramonrocha.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ramonrocha.cursomc.domain.Categoria;
import com.ramonrocha.cursomc.domain.Cidade;
import com.ramonrocha.cursomc.domain.Cliente;
import com.ramonrocha.cursomc.domain.Endereco;
import com.ramonrocha.cursomc.domain.Estado;
import com.ramonrocha.cursomc.domain.ItemPedido;
import com.ramonrocha.cursomc.domain.Pagamento;
import com.ramonrocha.cursomc.domain.PagamentoComBoleto;
import com.ramonrocha.cursomc.domain.PagamentoComCartao;
import com.ramonrocha.cursomc.domain.Pedido;
import com.ramonrocha.cursomc.domain.Produto;
import com.ramonrocha.cursomc.domain.enums.EstadoPagamento;
import com.ramonrocha.cursomc.domain.enums.TipoCliente;
import com.ramonrocha.cursomc.repositories.CategoriaRepository;
import com.ramonrocha.cursomc.repositories.CidadeRepository;
import com.ramonrocha.cursomc.repositories.ClienteRepository;
import com.ramonrocha.cursomc.repositories.EnderecoRepository;
import com.ramonrocha.cursomc.repositories.EstadoRepository;
import com.ramonrocha.cursomc.repositories.ItemPedidoRepository;
import com.ramonrocha.cursomc.repositories.PagamentoRepository;
import com.ramonrocha.cursomc.repositories.PedidoRepository;
import com.ramonrocha.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Móveis");
		Categoria categoria4 = new Categoria(null, "Eletrodomésticos");
		Categoria categoria5 = new Categoria(null, "Cama mesa e banho");
		Categoria categoria6 = new Categoria(null, "Jardinagem");
		Categoria categoria7 = new Categoria(null, "Lazer");
		Categoria categoria8 = new Categoria(null, "Esportes");

		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		Produto produto4 = new Produto(null, "Mesa de Escriório", 300.00);
		Produto produto5 = new Produto(null, "Toalha", 80.00);
		Produto produto6 = new Produto(null, "Colchão de Ar", 200.00);
		Produto produto7 = new Produto(null, "Guarda Roupas", 1350.00);
		Produto produto8 = new Produto(null, "Armário Cozinha", 1650.00);
		Produto produto9 = new Produto(null, "Barraca 4 pessoas", 140.00);
		Produto produto10 = new Produto(null, "Capacete", 250.00);
		Produto produto11 = new Produto(null, "Óculos", 250.00);
		Produto produto12 = new Produto(null, "Kit Jardinagem 10 Peças", 250.00);

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto7, produto8));
		categoria5.getProdutos().addAll(Arrays.asList(produto5));
		categoria6.getProdutos().addAll(Arrays.asList(produto12));
		categoria7.getProdutos().addAll(Arrays.asList(produto6, produto9));
		categoria8.getProdutos().addAll(Arrays.asList(produto10, produto11));

		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria5));
		produto6.getCategorias().addAll(Arrays.asList(categoria7));
		produto7.getCategorias().addAll(Arrays.asList(categoria3));
		produto8.getCategorias().addAll(Arrays.asList(categoria3));
		produto9.getCategorias().addAll(Arrays.asList(categoria7));
		produto10.getCategorias().addAll(Arrays.asList(categoria8));
		produto11.getCategorias().addAll(Arrays.asList(categoria8));
		produto12.getCategorias().addAll(Arrays.asList(categoria6));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);

		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5,
				categoria6, categoria7, categoria8));

		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7,
				produto8, produto9, produto10, produto11, produto12));

		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cliente1.getTelefones().addAll(Arrays.asList("4432225555", "44998668402"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "APTO 303", "Jardim", "38222222", cliente1,
				cidade1);
		Endereco endereco2 = new Endereco(null, "Rua Quintino Bocaiuva", "444", "APTO 25", "Felicidade", "87090138",
				cliente1, cidade2);

		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, endereco2);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2,
				sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().addAll(Arrays.asList(itemPedido3));

		produto1.getItens().addAll(Arrays.asList(itemPedido1));
		produto2.getItens().addAll(Arrays.asList(itemPedido3));
		produto3.getItens().addAll(Arrays.asList(itemPedido2));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

	}
}
