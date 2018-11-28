package controlador;

public class Cargo {
	private int pk_Cargo;
	private String nome;
	private String descricao;

	public Cargo() {

	}

	public Cargo(int pk, String nome, String descricao) {
		super();
		this.pk_Cargo = pk;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Cargo(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * imprime na saida as informacoes do objeto
	 * varre o vetor de enderecos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getpk_Cargo() {
		return pk_Cargo;
	}

	public void setpk_Cargo(int ok) {
		this.pk_Cargo = ok;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
