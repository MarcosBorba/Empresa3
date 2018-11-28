package controlador;

import java.util.Date;

public class FinanceiroSaidas {
	private int pk_Saidas;
	private Compras compras;
	private Date dataEmissaoPagar;
	private Date dataVencimentoPagamento;
	private Date dataBaixa;
	private double valorPago;
	private String tipoDocumentoPago;

	FinanceiroSaidas() {

	}

	public FinanceiroSaidas(int pk_Saidas, Compras compras, Date dataEmissaoPagar, Date dataVencimentoPagamento,
			Date dataBaixa, double valorPago, String tipoDocumentoPago) {
		super();
		this.pk_Saidas = pk_Saidas;
		this.compras = compras;
		this.dataEmissaoPagar = dataEmissaoPagar;
		this.dataVencimentoPagamento = dataVencimentoPagamento;
		this.dataBaixa = dataBaixa;
		this.valorPago = valorPago;
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

	public FinanceiroSaidas(Compras compras, Date dataEmissaoPagar, Date dataVencimentoPagamento, Date dataBaixa,
			double valorPago, String tipoDocumentoPago) {
		super();
		this.compras = compras;
		this.dataEmissaoPagar = dataEmissaoPagar;
		this.dataVencimentoPagamento = dataVencimentoPagamento;
		this.dataBaixa = dataBaixa;
		this.valorPago = valorPago;
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "FinanceiroSaidas [pk_Saidas=" + pk_Saidas + ", compras=" + compras + ", dataEmissaoPagar="
				+ dataEmissaoPagar + ", dataVencimentoPagamento=" + dataVencimentoPagamento + ", dataBaixa=" + dataBaixa
				+ ", valorPago=" + valorPago + ", tipoDocumentoPago=" + tipoDocumentoPago + "]";
	}

	public int getPk_Saidas() {
		return pk_Saidas;
	}

	public void setPk_Saidas(int pk_Saidas) {
		this.pk_Saidas = pk_Saidas;
	}

	public Compras getCompras() {
		return compras;
	}

	public void setCompras(Compras compras) {
		this.compras = compras;
	}

	public Date getDataEmissaoPagar() {
		return dataEmissaoPagar;
	}

	public void setDataEmissaoPagar(Date dataEmissaoPagar) {
		this.dataEmissaoPagar = dataEmissaoPagar;
	}

	public Date getDataVencimentoPagamento() {
		return dataVencimentoPagamento;
	}

	public void setDataVencimentoPagamento(Date dataVencimentoPagamento) {
		this.dataVencimentoPagamento = dataVencimentoPagamento;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public String getTipoDocumentoPago() {
		return tipoDocumentoPago;
	}

	public void setTipoDocumentoPago(String tipoDocumentoPago) {
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

}
