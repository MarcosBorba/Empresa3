package controlador;

import java.util.Date;

public class FinanceiroEntradas {
	private int pk_Entrada;
	private int fk_venda;
	private Date dataEmissaoPagar;
	private Date dataVencimentoPagamento;
	private Date dataBaixa;
	private double valorPago;
	private String tipoDocumentoPago;

	FinanceiroEntradas() {

	}

	public FinanceiroEntradas(int pk_Entrada, int fk_venda, Date dataEmissaoPagar, Date dataVencimentoPagamento,
			Date dataBaixa, double valorPago, String tipoDocumentoPago) {
		super();
		this.pk_Entrada = pk_Entrada;
		this.fk_venda = fk_venda;
		this.dataEmissaoPagar = dataEmissaoPagar;
		this.dataVencimentoPagamento = dataVencimentoPagamento;
		this.dataBaixa = dataBaixa;
		this.valorPago = valorPago;
		this.tipoDocumentoPago = tipoDocumentoPago;
	}

	public FinanceiroEntradas(int fk_venda, Date dataEmissaoPagar, Date dataVencimentoPagamento, Date dataBaixa,
			double valorPago, String tipoDocumentoPago) {
		super();
		this.fk_venda = fk_venda;
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
		return "FinanceiroEntradas [pk_Entrada=" + pk_Entrada + ", fk_venda=" + fk_venda + ", dataEmissaoPagar="
				+ dataEmissaoPagar + ", dataVencimentoPagamento=" + dataVencimentoPagamento + ", dataBaixa=" + dataBaixa
				+ ", valorPago=" + valorPago + ", tipoDocumentoPago=" + tipoDocumentoPago + "]";
	}

	public int getPk_Entrada() {
		return pk_Entrada;
	}

	public void setPk_Entrada(int pk_Entrada) {
		this.pk_Entrada = pk_Entrada;
	}

	public int getFk_venda() {
		return fk_venda;
	}

	public void setFk_venda(int fk_venda) {
		this.fk_venda = fk_venda;
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
