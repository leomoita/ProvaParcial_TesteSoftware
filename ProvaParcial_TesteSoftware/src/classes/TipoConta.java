package classes;

/**
 * Enumeração utilizada para o mapeamento dos tipos de conta de acordo com a
 * sentença (string) informada.
 */
public enum TipoConta {

	CONTA_CORRENTE("Conta Corrente"), CONTA_POUPANCA("Conta Poupança"), CONTA_SALARIO("Conta Salário");

	private String descricao;

	TipoConta(String nome) {
		this.descricao = nome;
	}

	@Override
	public String toString() {
		return "TipoConta [ descricao = " + this.descricao + " ]";
	}

}
