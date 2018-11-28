package controlador;

import java.util.Date;

public class Log {
	private int pk_log;
	private Date dataAcesso;
	private String Usuario;
	private String descricao;

	Log() {

	}

	public Log(int pk_log, Date dataAcesso, String usuario, String descricao) {
		super();
		this.pk_log = pk_log;
		this.dataAcesso = dataAcesso;
		Usuario = usuario;
		this.descricao = descricao;
	}

	public Log(Date dataAcesso, String usuario, String descricao) {
		super();
		this.dataAcesso = dataAcesso;
		Usuario = usuario;
		this.descricao = descricao;
	}

	/**
	 * imprime na saida dos objetos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Log [pk_log=" + pk_log + ", Usuario=" + Usuario + ", descricao=" + descricao + "]";
	}

	public int getPk_log() {
		return pk_log;
	}

	public void setPk_log(int pk_log) {
		this.pk_log = pk_log;
	}

	public Date getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
