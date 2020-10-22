package classes;

public final class ContaBancaria extends Conta {

	public ContaBancaria() {
		super();
	}

	public ContaBancaria(long numero, long agencia) {
		super(numero, agencia);
	}

	public ContaBancaria(long numero, long agencia, double saldo, double limiteContratado) {
		super(numero, agencia, saldo, limiteContratado);
	}

	public ContaBancaria(Cliente cliente, long numero, long agencia, double saldo, double limiteContratado) {
		super(cliente, numero, agencia, saldo, limiteContratado);
	}

}
