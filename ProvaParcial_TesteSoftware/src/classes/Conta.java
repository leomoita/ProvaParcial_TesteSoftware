package classes;

public abstract class Conta {

	private Cliente cliente;
	private long numero;
	private long agencia;
	private double saldo;
	private double limiteContratado;

	public Conta() {

	}

	public Conta(long numero, long agencia) {
		super();
		this.numero = numero;
		this.agencia = agencia;
	}

	public Conta(long numero, long agencia, double saldo, double limiteContratado) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.limiteContratado = limiteContratado;
	}

	public Conta(Cliente cliente, long numero, long agencia, double saldo, double limiteContratado) {
		super();
		this.cliente = cliente;
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.limiteContratado = limiteContratado;
	}
	
	public void depositar(double valor) throws IllegalArgumentException {
		if (valor <= 0)
			throw new IllegalArgumentException("Valor de depósito inválido");

		this.saldo += valor;
	}
	
	public String obterSaldo() {
		return "Saldo = " + this.saldo;
	}

	public String obterExtrato() {
		return this.toString();
	}

	public boolean podeRealizarTransacao(double valor) {
		return Math.abs(this.saldo - valor) > this.limiteContratado;
	}

	public double sacar(double valor) throws IllegalArgumentException {
		if (valor <= 0)
			throw new IllegalArgumentException("Valor de saque inválido");
		
		if (Math.abs(this.saldo - valor) > this.limiteContratado)
			throw new IllegalArgumentException("Limite contratado insuficiente!");		

		this.saldo -= valor;

		return this.saldo;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if ((this.getClass() != obj.getClass()) && !(obj instanceof ContaBancaria))
			return false;

		Conta other = (Conta) obj;
		if (this.agencia != other.agencia)
			return false;

		if (this.numero != other.numero)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.agencia ^ (this.agencia >>> 32));
		result = prime * result + (int) (this.numero ^ (this.numero >>> 32));
		return result;
	}
	
	@Override
	public String toString() {
		return "Conta [ numero = " 
		+ this.numero 
		+ ", agencia = " 
		+ this.agencia 
		+ ", cliente = "
				+ this.cliente.getNome() 
				+ ", saldo = " 
				+ this.saldo 
				+ ", limiteContratado = " 
				+ this.limiteContratado
				+ " ]";
	}


	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public long getAgencia() {
		return this.agencia;
	}

	public void setAgencia(long agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimiteContratado() {
		return this.limiteContratado;
	}

	public void setLimiteContratado(double limiteContratado) {
		this.limiteContratado = limiteContratado;
	}

}
