package controlador;

public class ComprasItens {
	private int pk_Item;
	private Compras compras;
	private Produtos produtos;
	private int quantidadeDeItens;
	private double valorUnitarioProduto;

	public ComprasItens() {

	}

	public ComprasItens(int pk_Item, Compras compras, Produtos produtos, int quantidadeDeItens,
			double valorUnitarioProduto) {
		super();
		this.pk_Item = pk_Item;
		this.compras = compras;
		this.produtos = produtos;
		this.quantidadeDeItens = quantidadeDeItens;
		this.valorUnitarioProduto = valorUnitarioProduto;
	}

	public ComprasItens(Compras compras, Produtos produtos, int quantidadeDeItens, double valorUnitarioProduto) {
		super();
		this.compras = compras;
		this.produtos = produtos;
		this.quantidadeDeItens = quantidadeDeItens;
		this.valorUnitarioProduto = valorUnitarioProduto;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "ComprasItens [pk_Item=" + pk_Item + ", compras=" + compras + ", produtos=" + produtos
				+ ", quantidadeDeItens=" + quantidadeDeItens + ", valorUnitarioProduto=" + valorUnitarioProduto + "]";
	}

	public int getPk_Item() {
		return pk_Item;
	}

	public void setPk_Item(int pk_Item) {
		this.pk_Item = pk_Item;
	}

	public Compras getCompras() {
		return compras;
	}

	public void setCompras(Compras compras) {
		this.compras = compras;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public int getQuantidadeDeItens() {
		return quantidadeDeItens;
	}

	public void setQuantidadeDeItens(int quantidadeDeItens) {
		this.quantidadeDeItens = quantidadeDeItens;
	}

	public double getValorUnitarioProduto() {
		return valorUnitarioProduto;
	}

	public void setValorUnitarioProduto(double valorUnitarioProduto) {
		this.valorUnitarioProduto = valorUnitarioProduto;
	}

}
