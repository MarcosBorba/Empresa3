package controlador;

import java.util.ArrayList;

public class Funcionario extends Sujeito {

	private Cpf cpf;
	private Cargo fk_Cargo;

	public Funcionario() {
	}

	public Funcionario(int pk, String nome, ArrayList<Enderecos> enderecos, Cpf cpf, Cargo fk_Cargo) {
		super(pk, nome, enderecos);
		this.cpf = cpf;
		this.fk_Cargo = fk_Cargo;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Funcionario(String nome, ArrayList<Enderecos> enderecos, Cpf cpf, Cargo fk_Cargo) {
		super(nome, enderecos);
		this.cpf = cpf;
		this.fk_Cargo = fk_Cargo;
	}

	public Funcionario(String nome, Cpf cpf, Cargo fk_Cargo) {
		super(nome);
		this.cpf = cpf;
		this.fk_Cargo = fk_Cargo;
	}
	
	

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Cargo getFk_Cargo() {
		return fk_Cargo;
	}

	public void setFk_Cargo(Cargo fk_Cargo) {
		this.fk_Cargo = fk_Cargo;
	}

}
