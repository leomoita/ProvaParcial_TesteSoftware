package servicos.interfaces;

import classes.Cliente;
import classes.Conta;

public interface IContasServico {

	public Cliente criarCliente();

	public Conta criarConta();

	public void adicionar(Conta conta);

	public void buscarPorConta();

	public Conta buscarPorContaComRetorno();

	public void buscarPorCliente();

	public void remover(Conta conta);

	public void transferir(double valor, Conta beneficente, Conta beneficiario);

}
