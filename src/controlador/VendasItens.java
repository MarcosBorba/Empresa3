package controlador;

public class VendasItens {

    private int pk_Item;
    private int fk_venda;
    private Produtos fk_produto;
    private int quantidadeDeItens;
    private double valorUnitarioProduto;
    private double valor_Total;

    VendasItens() {

    }

    public VendasItens(int pk_Item, int fk_venda, Produtos fk_produto, int quantidadeDeItens, double valorUnitarioProduto, double valor_Total) {
        this.pk_Item = pk_Item;
        this.fk_venda = fk_venda;
        this.fk_produto = fk_produto;
        this.quantidadeDeItens = quantidadeDeItens;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.valor_Total = valor_Total;
    }

    public VendasItens(int fk_venda, Produtos fk_produto, int quantidadeDeItens, double valorUnitarioProduto) {
        super();
        this.fk_venda = fk_venda;
        this.fk_produto = fk_produto;
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
        return "VendasItens{" + "pk_Item=" + pk_Item + ", fk_venda=" + fk_venda + ", fk_produto=" + fk_produto + ", quantidadeDeItens=" + quantidadeDeItens + ", valorUnitarioProduto=" + valorUnitarioProduto + ", valor_Total=" + valor_Total + '}';
    }

    public int getPk_Item() {
        return pk_Item;
    }

    public void setPk_Item(int pk_Item) {
        this.pk_Item = pk_Item;
    }

    public int getFk_venda() {
        return fk_venda;
    }

    public void setFk_venda(int fk_venda) {
        this.fk_venda = fk_venda;
    }

    public Produtos getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(Produtos fk_produto) {
        this.fk_produto = fk_produto;
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

    public double getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }

}
