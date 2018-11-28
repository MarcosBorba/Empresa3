package controlador;

import java.util.ArrayList;

public class Fornecedor extends Sujeito {
	private Cpf cpf;
	private ArrayList<Compras> compras;

	Fornecedor() {
		super();
		compras = new ArrayList<>();
	}

	public Fornecedor(int pk, String nome, ArrayList<Enderecos> enderecos, Cpf cpf, ArrayList<Compras> compras) {
		super(pk, nome, enderecos);
		this.cpf = cpf;
		this.compras = compras;
	}

	public Fornecedor(String nome, ArrayList<Enderecos> enderecos, Cpf cpf, ArrayList<Compras> compras) {
		super(nome, enderecos);
		this.cpf = cpf;
		this.compras = compras;
	}

	public Fornecedor(String nome, Cpf cpf) {
		super(nome);
		this.cpf = cpf;
		compras = new ArrayList<>();
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Fornecedor [cpf=" + cpf + ", compras=" + compras + "]";
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Compras> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compras> compras) {
		this.compras = compras;
	}

}
