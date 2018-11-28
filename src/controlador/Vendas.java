package controlador;

import java.util.ArrayList;
import java.util.Date;

public class Vendas {
	private int pk_Vendas;
	private Cliente cliente;
	private Funcionario funcionario;
	private Date dataVenda;
	private int numeroVenda;
	private ArrayList<VendasItens> vendasItens;
	private ArrayList<FinanceiroEntradas> financeiroEntradas;

	Vendas() {
		vendasItens = new ArrayList<>();
		financeiroEntradas = new ArrayList<>();
	}

	public Vendas(int pk_Vendas, Cliente cliente, Funcionario funcionario, Date dataVenda, int numeroVenda,
			ArrayList<VendasItens> vendasItens, ArrayList<FinanceiroEntradas> financeiroEntradas) {
		super();
		this.pk_Vendas = pk_Vendas;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.numeroVenda = numeroVenda;
		this.vendasItens = vendasItens;
		this.financeiroEntradas = financeiroEntradas;
	}
	public Vendas(int pk_Vendas, Cliente cliente, Funcionario funcionario, Date dataVenda,int numeroVenda) {
		super();
		this.pk_Vendas = pk_Vendas;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.numeroVenda = numeroVenda;
	}

	public Vendas(Cliente cliente, Funcionario funcionario, Date dataVenda, int numeroVenda,
			ArrayList<VendasItens> vendasItens, ArrayList<FinanceiroEntradas> financeiroEntradas) {
		super();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.numeroVenda = numeroVenda;
		this.vendasItens = vendasItens;
		this.financeiroEntradas = financeiroEntradas;
	}

	public Vendas(Cliente cliente, Funcionario funcionario, Date dataVenda, int numeroVenda) {
		super();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.dataVenda = dataVenda;
		this.numeroVenda = numeroVenda;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Vendas [pk_Vendas=" + pk_Vendas + ", cliente=" + cliente + ", funcionario=" + funcionario
				+ ", vendasItens=" + vendasItens + ", financeiroEntradas=" + financeiroEntradas + "]";
	}

	public int getPk_Vendas() {
		return pk_Vendas;
	}

	public void setPk_Vendas(int pk_Vendas) {
		this.pk_Vendas = pk_Vendas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public int getNumeroVenda() {
		return numeroVenda;
	}

	public void setNumeroVenda(int numeroVenda) {
		this.numeroVenda = numeroVenda;
	}

	public ArrayList<VendasItens> getVendasItens() {
		return vendasItens;
	}

	public void setVendasItens(ArrayList<VendasItens> vendasItens) {
		this.vendasItens = vendasItens;
	}

	public ArrayList<FinanceiroEntradas> getFinanceiroEntradas() {
		return financeiroEntradas;
	}

	public void setFinanceiroEntradas(ArrayList<FinanceiroEntradas> financeiroEntradas) {
		this.financeiroEntradas = financeiroEntradas;
	}

}
