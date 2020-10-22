package classes;

public class ContaCorrente extends Conta {

	public ContaCorrente() {
		super();
	}
	
	public ContaCorrente(Cliente cliente, long numero, long agencia, double saldo, double limiteContratado) {
		super(cliente, numero, agencia, saldo, limiteContratado);
	}

	public ContaCorrente(long numero, long agencia, double saldo, double limiteContratado) {
		super(numero, agencia, saldo, limiteContratado);
	}
}
