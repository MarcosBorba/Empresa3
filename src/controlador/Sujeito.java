package controlador;

import java.util.ArrayList;

public class Sujeito {
	private int pk;
	private String nome;
	private ArrayList<Enderecos> enderecos;

	public Sujeito() {
		enderecos = new ArrayList<Enderecos>();
	}

	public Sujeito(int pk, String nome, ArrayList<Enderecos> enderecos) {
		super();
		this.pk = pk;
		this.nome = nome;
		this.enderecos = enderecos;
	}

	public Sujeito(String nome, ArrayList<Enderecos> enderecos) {
		super();
		this.nome = nome;
		this.enderecos = enderecos;
	}

	public Sujeito(String nome) {
		super();
		this.nome = nome;
		enderecos = new ArrayList<Enderecos>();
	}

    public Sujeito(int pk, String nome) {
        this.pk = pk;
        this.nome = nome;
        enderecos = new ArrayList<Enderecos>();
    }
        
        

	/**
	 * imprime na saida as informacoes do objeto varre o vetor de enderecos
	 */
	public void print() {
		System.out.println(this);

		/*
		 * for(int i=0;i<funcionarioEndereco.size();i++) {
		 * funcionarioEndereco.get(i).print(); }
		 */
		for (Enderecos aux : enderecos) {

			aux.print();

		}
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Enderecos> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(ArrayList<Enderecos> enderecos) {
		this.enderecos = enderecos;
	}

}
