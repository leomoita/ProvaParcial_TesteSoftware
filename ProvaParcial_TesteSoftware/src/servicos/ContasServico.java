package servicos;

import classes.Cliente;
import classes.Conta;
import classes.ContaBancaria;
import classes.ContaCorrente;
import classes.ContaPoupanca;
import classes.ContaSalario;
import classes.TipoConta;
import main.Main;
import repositorios.interfaces.IContasRepositorio;
import servicos.interfaces.IContasServico;

public class ContasServico implements IContasServico {

	private IContasRepositorio repositorio;

	public ContasServico(IContasRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public Cliente criarCliente() {
		String nome = null;

		System.out.println();
		Main.SCANNER.nextLine();
		System.out.print("Digite o nome do cliente: ");
		nome = Main.SCANNER.nextLine();

		if (nome != null && !nome.isEmpty()) {
			return new Cliente(nome);
		}

		System.out.println();
		System.out.println("Entre com um nome válido!");

		return null;
	}

	@Override
	public Conta criarConta() {
		String tipo = null;
		long numero = 0;
		long agencia = 0;
		double saldo = 0.0;
		double limiteContratado = 0.0;

		System.out.print("Digite o número da conta: ");
		numero = Main.SCANNER.nextLong();
		System.out.print("Digite a agência da conta: ");
		agencia = Main.SCANNER.nextLong();
		System.out.print("Digite o limite para as transações da conta: ");
		limiteContratado = Main.SCANNER.nextDouble();
		System.out.println("Escolha o tipo da conta (Digite o nome da conta igual a opção correspondente):");
		System.out.println("Conta Corrente (CONTA_CORRENTE)");
		System.out.println("Conta Poupança (CONTA_POUPANCA)");
		System.out.println("Conta Salário (CONTA_SALARIO)");
		System.out.print("Opção: ");
		Main.SCANNER.nextLine();
		tipo = Main.SCANNER.nextLine();

		if (numero > 0 && agencia > 0 && limiteContratado >= 0 && tipo != null && !tipo.isEmpty()) {
			if (TipoConta.valueOf(tipo) == TipoConta.CONTA_CORRENTE)
				return new ContaCorrente(numero, agencia, saldo, limiteContratado);

			if (TipoConta.valueOf(tipo) == TipoConta.CONTA_POUPANCA)
				return new ContaPoupanca(numero, agencia, saldo, limiteContratado);
				
			if (TipoConta.valueOf(tipo) == TipoConta.CONTA_SALARIO)
				return new ContaSalario(numero, agencia, saldo, limiteContratado);
		}

		System.out.println();
		System.out.println("Entre com dados válidos!");

		return null;
	}

	@Override
	public void adicionar(Conta conta) {
		try {
			this.repositorio.adicionar(conta);

			System.out.println();
			System.out.println("Conta adicionada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void buscarPorConta() {
		long numero = 0;
		long agencia = 0;

		System.out.println();
		Main.SCANNER.nextLine();
		System.out.print("Digite o número da conta: ");
		numero = Main.SCANNER.nextLong();
		System.out.print("Digite a agência da conta: ");
		agencia = Main.SCANNER.nextLong();

		Conta contaBusca = new ContaBancaria(numero, agencia);
		Conta conta = this.repositorio.buscar(contaBusca);

		if (conta != null) {
			System.out.println();
			System.out.println(conta);
			return;
		}

		System.out.println();
		System.out.println("Conta não encontrada!");
	}

	@Override
	public Conta buscarPorContaComRetorno() {
		long numero = 0;
		long agencia = 0;

		System.out.println();
		Main.SCANNER.nextLine();
		System.out.print("Digite o número da conta: ");
		numero = Main.SCANNER.nextLong();
		System.out.print("Digite a agência da conta: ");
		agencia = Main.SCANNER.nextLong();

		Conta contaBusca = new ContaBancaria(numero, agencia);
		Conta conta = this.repositorio.buscar(contaBusca);

		return conta;
	}

	@Override
	public void buscarPorCliente() {
		String nome = null;

		System.out.println();
		Main.SCANNER.nextLine();
		System.out.print("Digite o nome do cliente: ");
		nome = Main.SCANNER.nextLine();

		Cliente cliente = new Cliente(nome);
		Conta conta = this.repositorio.buscar(cliente);

		if (conta != null) {
			System.out.println();
			System.out.println(conta);
			return;
		}

		System.out.println();
		System.out.println("Conta não encontrada!");
	}

	@Override
	public void remover(Conta conta) {
		try {
			this.repositorio.remover(conta);

			System.out.println();
			System.out.println("Conta removida com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void transferir(double valor, Conta beneficente, Conta beneficiario) {
		if (valor <= 0 || beneficente == null || beneficiario == null) {
			System.out.println();
			System.out.println("Entre com dados válidos!");
			return;
		}

		if (!beneficente.podeRealizarTransacao(valor)) {
			System.out.println();
			System.out.println("A conta beneficente não pode realizar a transação!");
			return;
		}

		try {
			beneficente.sacar(valor);
			beneficiario.depositar(valor);

			System.out.println();
			System.out.println("Transação realizada com sucesso!");
		} catch (Exception e) {
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

}
