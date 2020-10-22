package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import classes.Cliente;
import classes.Conta;
import repositorios.interfaces.IContasRepositorio;

public class ContasRepositorio implements IContasRepositorio {

	private List<Conta> contas;

	public ContasRepositorio() {
		this.contas = new ArrayList<Conta>();
	}

	@Override
	public boolean adicionar(Conta conta) throws IllegalArgumentException {
		if (this.buscar(conta) != null) {
			throw new IllegalArgumentException("Está conta já existe!");
		}

		if (this.buscar(conta.getCliente()) != null) {
			throw new IllegalArgumentException("O cliente não pode ter duas contas diferentes!");
		}

		this.contas.add(conta);

		return true;
	}

	@Override
	public Conta buscar(Conta conta) {
		Stream<Conta> stream = this.contas.parallelStream();
		Predicate<Conta> predicate = (c -> c.equals(conta));
		List<Conta> contas = stream.filter(predicate).collect(Collectors.toList());

		return contas.isEmpty() ? null : contas.get(0);
	}

	@Override
	public Conta buscar(Cliente cliente) {
		Stream<Conta> stream = this.contas.parallelStream();
		Predicate<Conta> predicate = (c -> c.getCliente().equals(cliente));
		List<Conta> contas = stream.filter(predicate).collect(Collectors.toList());

		return contas.isEmpty() ? null : contas.get(0);
	}

	@Override
	public boolean remover(Conta Conta) throws IllegalArgumentException {
		if (this.buscar(Conta) == null) {
			throw new IllegalArgumentException("Conta não encotrada!");
		}

		return this.contas.remove(Conta);
	}

}
