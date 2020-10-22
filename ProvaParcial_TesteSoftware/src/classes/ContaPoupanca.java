package classes;

/**
 * Conta poupança.
 */
public class ContaPoupanca extends Conta {

	public ContaPoupanca() {
		super();
	}
	
	public ContaPoupanca(Cliente cliente, long numero, long agencia, double saldo, double limiteContratado) {
		super(cliente, numero, agencia, saldo, limiteContratado);
	}

	public ContaPoupanca(long numero, long agencia, double saldo, double limiteContratado) {
		super(numero, agencia, saldo, limiteContratado);
	}

}
