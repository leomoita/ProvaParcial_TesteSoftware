package main;

import java.util.Scanner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import classes.Cliente;
import classes.Conta;
import repositorios.ContasRepositorio;
import repositorios.interfaces.IContasRepositorio;
import servicos.ContasServico;
import servicos.interfaces.IContasServico;
import tests.suites.JSuiteTest;

public class Main {

	public static final Scanner SCANNER = new Scanner(System.in);

	private IContasRepositorio contasRepositorio;
	private IContasServico contasServico;

	public Main() {
		this.contasRepositorio = new ContasRepositorio();
		this.contasServico = new ContasServico(this.contasRepositorio);
	}

	public static void main(String[] args) {
		int opcao = -1;

		System.out.println("Escolha uma opção:");
		System.out.println("1 - Aplicação");
		System.out.println("2 - Testes");
		System.out.println("0 - Sair");
		System.out.print("Opção: ");
		opcao = Main.SCANNER.nextInt();

		switch (opcao) {
		case 1:
			Main main = new Main();
			main.runApp();
			break;

		case 2:
			Main.runTests();
			break;

		default:
			System.out.println();
			System.out.println("Saindo...");
			return;
		}

		Main.SCANNER.close();
	}

	public void runApp() {
		Cliente cliente = null;
		Conta primeiraConta = null;
		Conta segundaConta = null;
		double valor = 0;
		int opcao = -1;

		do {

			try {

				System.out.println();
				System.out.println("1 - Criar conta");
				System.out.println("2 - Buscar conta");
				System.out.println("3 - Buscar cliente");
				System.out.println("4 - Fechar conta");
				System.out.println("5 - Transferência");
				System.out.println("6 - Depósito");
				System.out.println("7 - Saque");
				System.out.println("0) Sair");
				System.out.print("Opção: ");
				opcao = Main.SCANNER.nextInt();

				switch (opcao) {
				case 1:
					cliente = this.contasServico.criarCliente();
					primeiraConta = this.contasServico.criarConta();

					primeiraConta.setCliente(cliente);
					cliente.setConta(primeiraConta);

					this.contasServico.adicionar(primeiraConta);

					break;

				case 2:
					this.contasServico.buscarPorConta();
					break;

				case 3:
					this.contasServico.buscarPorCliente();
					break;

				case 4:
					primeiraConta = this.contasServico.buscarPorContaComRetorno();

					if (primeiraConta == null) {
						System.out.println();
						System.out.println("Conta não encontrada!");
					} else {
						this.contasServico.remover(primeiraConta);
					}
					break;

				case 5:
					primeiraConta = this.contasServico.buscarPorContaComRetorno();
					segundaConta = this.contasServico.buscarPorContaComRetorno();

					System.out.println("Entre com o valor da transferência: ");
					valor = Main.SCANNER.nextDouble();

					this.contasServico.transferir(valor, primeiraConta, segundaConta);
					break;

				case 6:
					primeiraConta = this.contasServico.buscarPorContaComRetorno();

					System.out.print("Entre com o valor do depósito: ");
					valor = Main.SCANNER.nextDouble();

					primeiraConta.depositar(valor);
					break;

				case 7:
					primeiraConta = this.contasServico.buscarPorContaComRetorno();

					System.out.print("Entre com o valor do saque: ");
					valor = Main.SCANNER.nextDouble();

					primeiraConta.sacar(valor);
					break;

				default:
					System.out.println();
					System.out.println("Saindo!");
					return;
				}

			} catch (Exception e) {
				System.out.println();
				System.out.println("Erro: " + e.getMessage());
				opcao = -1;
			}

		} while (opcao != 0);

	}

	public static void runTests() {
		System.out.println();
		System.out.println();
		System.out.println("**************************************************");

		System.out.println("Executando testes!");
		Result result = JUnitCore.runClasses(JSuiteTest.class);

		System.out.println("**************************************************");
		System.out.println("Mensagens de falha:");
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.getMessage());
		}

		System.out.println();
		System.out.println("Sucesso: " + result.wasSuccessful());
	}

}
