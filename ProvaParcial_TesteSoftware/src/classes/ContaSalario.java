package classes;

/**
 * Conta salário.
 */
public class ContaSalario extends Conta {

	public ContaSalario() {
		super();
	}
	
	public ContaSalario(Cliente cliente, long numero, long agencia, double saldo, double limiteContratado) {
		super(cliente, numero, agencia, saldo, limiteContratado);
	}

	public ContaSalario(long numero, long agencia, double saldo, double limiteContratado) {
		super(numero, agencia, saldo, limiteContratado);
	}

}
