package tests.classes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import classes.Cliente;
import classes.Conta;
import classes.ContaBancaria;
import classes.ContaPoupanca;
import classes.ContaSalario;
import repositorios.ContasRepositorio;
import repositorios.interfaces.IContasRepositorio;
import servicos.ContasServico;
import servicos.interfaces.IContasServico;

public class JTestContasServico {

	private IContasRepositorio contasRepositorio;
	private IContasServico contasServico;

	public JTestContasServico() {
		contasRepositorio = new ContasRepositorio();
		contasServico = new ContasServico(this.contasRepositorio);
	}

	@Before
	public void inicializar() {
		Cliente primeiroCliente = new Cliente("Clebinho");
		Cliente segundoCliente = new Cliente("Carlão");

		Conta primeiraConta = new ContaSalario(primeiroCliente, 1, 1, 2000.00, 3500.00);
		primeiroCliente.setConta(primeiraConta);

		Conta segundaConta = new ContaPoupanca(segundoCliente, 2, 1, 2500.00, 2100.00);
		segundoCliente.setConta(segundaConta);

		this.contasServico.adicionar(primeiraConta);
		this.contasServico.adicionar(segundaConta);
	}

	@After
	public void finalizar() {
		Conta primeiraConta = new ContaBancaria(1, 1);
		Conta segundaConta = new ContaBancaria(2, 1);

		this.contasServico.remover(primeiraConta);
		this.contasServico.remover(segundaConta);
	}

	@Test
	public void testTransferencia() {
		Conta busca = null;

		busca = new ContaBancaria(1, 1);
		Conta beneficente = this.contasRepositorio.buscar(busca);

		busca = new ContaBancaria(2, 1);
		Conta beneficiario = this.contasRepositorio.buscar(busca);

		this.contasServico.transferir(600.00, beneficente, beneficiario);

		double saldoBeneficente = beneficente.getSaldo();
		double saldoBeneficiario = beneficiario.getSaldo();

		Assert.assertEquals("O saldo do beneficente deve ser de R$ 600.00", 600.00, saldoBeneficente, 0.00);
		Assert.assertEquals("O saldo do beneficiario deve ser de R$ 3000.00", 3000.00, saldoBeneficiario, 0.00);
	}

}
