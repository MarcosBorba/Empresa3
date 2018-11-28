package controlador;

public class Produtos {
	private int pk_Produto;
	private String nome;
	private int estoqueMinimo;
	private int quantidadeEstoque;

	public Produtos() {

	}

	public Produtos(int pk_Produto, String nome, int estoqueMinimo, int quantidadeEstoque) {
		super();
		this.pk_Produto = pk_Produto;
		this.nome = nome;
		this.estoqueMinimo = estoqueMinimo;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Produtos(String nome, int estoqueMinimo, int quantidadeEstoque) {
		super();
		this.nome = nome;
		this.estoqueMinimo = estoqueMinimo;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Produtos(String nome, int estoqueMinimo) {
		super();
		this.nome = nome;
		this.estoqueMinimo = estoqueMinimo;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getPk_Produto() {
		return pk_Produto;
	}

	public void setPk_Produto(int pk_Produto) {
		this.pk_Produto = pk_Produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

}
