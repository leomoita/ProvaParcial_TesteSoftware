package tests.classes;

import org.junit.Assert;
import org.junit.Test;

import classes.Cliente;
import classes.Conta;
import classes.ContaBancaria;
import classes.ContaPoupanca;
import classes.ContaSalario;
import repositorios.ContasRepositorio;
import repositorios.interfaces.IContasRepositorio;

public class JTestContasRepositorio {

	private IContasRepositorio contasRepositorio;

	public JTestContasRepositorio() {
		this.contasRepositorio = new ContasRepositorio();

		Cliente primeiroCliente = new Cliente("Clebinho");
		Cliente segundoCliente = new Cliente("Carlão");

		Conta primeiraConta = new ContaSalario(primeiroCliente, 1, 1, 2000.00, 3500.00);
		primeiroCliente.setConta(primeiraConta);

		Conta segundaConta = new ContaPoupanca(segundoCliente, 2, 1, 2500.00, 2100.00);
		segundoCliente.setConta(segundaConta);

		this.contasRepositorio.adicionar(primeiraConta);
		this.contasRepositorio.adicionar(segundaConta);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdicionarConta() throws IllegalArgumentException {
		Cliente cliente = new Cliente("Marcos");
		Conta conta = new ContaSalario(cliente, 1, 1, 0, 0);

		this.contasRepositorio.adicionar(conta);
	}

	@Test
	public void testBuscarConta() {
		Conta conta = new ContaBancaria(1, 1);
		Conta busca = this.contasRepositorio.buscar(conta);

		Assert.assertNotNull("A conta não pôde ser encontrada", busca);
	}

	@Test
	public void testBuscarContaCliente() {
		Cliente cliente = new Cliente("Clebinho");
		Conta busca = this.contasRepositorio.buscar(cliente);

		Assert.assertNotNull("A conta do cliente não pôde ser encontrada", busca);
	}

	@Test
	public void testRemoverConta() {
		Conta conta = new ContaBancaria(1, 1);
		Conta busca = this.contasRepositorio.buscar(conta);
		boolean removida = this.contasRepositorio.remover(busca);

		Assert.assertTrue("A conta não pôde ser removida", removida);
	}

}
