package repositorios.interfaces;

import classes.Cliente;
import classes.Conta;

public interface IContasRepositorio {

	public boolean adicionar(Conta conta) throws IllegalArgumentException;

	public Conta buscar(Conta conta);

	public Conta buscar(Cliente cliente);

	public boolean remover(Conta conta) throws IllegalArgumentException;

}
