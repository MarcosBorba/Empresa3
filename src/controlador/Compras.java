package controlador;

import java.util.ArrayList;
import java.util.Date;

public class Compras {
	private int pk_Compra;
	private Fornecedor fornecedor;
	private String numeroCompra;
	private Date dataCompra;
	private ArrayList<ComprasItens> comprasItens;
	private ArrayList<FinanceiroSaidas> financeiroSaidas;

	public Compras() { 
		comprasItens = new ArrayList<>();
		financeiroSaidas = new ArrayList<>();
	}

	public Compras(int pk_Compra, Fornecedor fornecedor, String numeroCompra, Date dataCompra,
			ArrayList<ComprasItens> comprasItens, ArrayList<FinanceiroSaidas> financeiroSaidas) {
		super();
		this.pk_Compra = pk_Compra;
		this.fornecedor = fornecedor;
		this.numeroCompra = numeroCompra;
		this.dataCompra = dataCompra;
		this.comprasItens = comprasItens;
		this.financeiroSaidas = financeiroSaidas;
	}
	public Compras(int pk_Compra, Fornecedor fornecedor, String numeroCompra, Date dataCompra) {
		super();
		this.pk_Compra = pk_Compra;
		this.fornecedor = fornecedor;
		this.numeroCompra = numeroCompra;
		this.dataCompra = dataCompra;
	}

	public Compras(Fornecedor fornecedor, String numeroCompra, Date dataCompra, ArrayList<ComprasItens> comprasItens,
			ArrayList<FinanceiroSaidas> financeiroSaidas) {
		super();
		this.fornecedor = fornecedor;
		this.numeroCompra = numeroCompra;
		this.dataCompra = dataCompra;
		this.comprasItens = comprasItens;
		this.financeiroSaidas = financeiroSaidas;
	}

	public Compras(Fornecedor fornecedor, String numeroCompra, Date dataCompra) {
		super();
		this.fornecedor = fornecedor;
		this.numeroCompra = numeroCompra;
		this.dataCompra = dataCompra;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Compras [pk_Compra=" + pk_Compra + ", fornecedor=" + fornecedor + ", numeroCompra=" + numeroCompra
				+ ", dataCompra=" + dataCompra + ", comprasItens=" + comprasItens + ", financeiroSaidas="
				+ financeiroSaidas + "]";
	}

	public ArrayList<FinanceiroSaidas> getFinanceiroSaidas() {
		return financeiroSaidas;
	}

	public void setFinanceiroSaidas(ArrayList<FinanceiroSaidas> financeiroSaidas) {
		this.financeiroSaidas = financeiroSaidas;
	}

	public ArrayList<ComprasItens> getComprasItens() {
		return comprasItens;
	}

	public void setComprasItens(ArrayList<ComprasItens> comprasItens) {
		this.comprasItens = comprasItens;
	}

	public int getPk_Compra() {
		return pk_Compra;
	}

	public void setPk_Compra(int pk_Compra) {
		this.pk_Compra = pk_Compra;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNumeroCompra() {
		return numeroCompra;
	}

	public void setNumeroCompra(String numeroCompra) {
		this.numeroCompra = numeroCompra;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

}
