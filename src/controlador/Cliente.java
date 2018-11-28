package controlador;

import java.util.ArrayList;

public class Cliente extends Sujeito {

	private Cpf cpf;

	public Cliente() {
		super();
	}

	public Cliente(int pk, String nome, ArrayList<Enderecos> enderecos, Cpf cpf) {
		super(pk, nome, enderecos);
		this.cpf = cpf;
	}

	public Cliente(String nome, ArrayList<Enderecos> enderecos, Cpf cpf) {
		super(nome, enderecos);
		this.cpf = cpf;
	}

	public Cliente(String nome, Cpf cpf) {
		super(nome);
		this.cpf = cpf;
	}

    public Cliente(int pk, String nome,Cpf cpf) {
        super(pk, nome);
        this.cpf = cpf;
    }
        
        

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

}
