package classes;

public class Cliente {

	private Conta conta;
	private String nome;

	public Cliente() {
	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public Cliente(Conta conta, String nome) {
		super();
		this.conta = conta;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [ 
			nome = " + this.nome + ", conta = " + this.conta.getNumero() + " 
		]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (
			(this.nome == null) ? 0 : this.nome.hashCode()
		);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
	
		if (obj == null)
			return false;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		Cliente other = (Cliente) obj;
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}

		return true;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
