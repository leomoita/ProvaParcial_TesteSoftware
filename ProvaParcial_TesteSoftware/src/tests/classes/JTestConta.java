package tests.classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.Conta;
import classes.ContaBancaria;

public class JTestConta {

	private static Conta conta;

	@BeforeClass
	public static void inicializar() {
		JTestConta.conta = new ContaBancaria(1, 1, 0.00, 0.00);
	}

	@AfterClass
	public static void finalizar() {
		JTestConta.conta = null;
	}

	@Before
	public void inicializarConta() {
		JTestConta.conta.setSaldo(600.00);
		JTestConta.conta.setLimiteContratado(2000.00);
	}

	@After
	public void finalizarConta() {
		JTestConta.conta.setSaldo(0.00);
		JTestConta.conta.setLimiteContratado(0.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDepositoFalho() throws IllegalArgumentException {
		JTestConta.conta.depositar(-200.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaqueFalho() throws IllegalArgumentException {
		JTestConta.conta.sacar(-200.00);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLimiteContratadoFalho() {
		JTestConta.conta.sacar(2600.00);
	}

	@Test
	public void testDeposito() {
		JTestConta.conta.depositar(200.00);

		double saldo = JTestConta.conta.getSaldo();

		Assert.assertEquals("O saldo da conta deve ser de R$ 700.00", 700.00, saldo, 0.00);
	}

	@Test
	public void testSaque() {
		JTestConta.conta.sacar(500.00);

		double saldo = JTestConta.conta.getSaldo();

		Assert.assertEquals("O saldo da conta deve ser de R$ 0.00", 0.00, saldo, 0.00);
	}

	@Test
	public void testLimiteContratado() {
		JTestConta.conta.sacar(2500.00);

		double saldo = JTestConta.conta.getSaldo();

		Assert.assertEquals("O saldo da conta deve ser de R$ -1000.00", -2000.00, saldo, 0.00);
	}

}
