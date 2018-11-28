package controlador;

public class Enderecos {
	private int pk_Enderecos;
	private int fk;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;

	public Enderecos() {

	}

	public Enderecos(int pk_Enderecos, int fk, String logradouro, String bairro, String cidade, String estado,
			String pais, String cep) {
		super();
		this.pk_Enderecos = pk_Enderecos;
		this.fk = fk;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}

	public Enderecos(String logradouro, String bairro, String cidade, String estado, String pais, String cep) {
		super();
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}
        
        public Enderecos(int fk, String logradouro, String bairro, String cidade, String estado,
			String pais, String cep) {
		super();
		this.fk = fk;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.cep = cep;
	}

	/**
	 * imprime na saida as informacoes do objeto varre o vetor de enderecos
	 */
	public void print() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "Enderecos [pk_Enderecos=" + pk_Enderecos + ", fk=" + fk + ", logradouro=" + logradouro + ", bairro="
				+ bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep + "]";
	}

	public int getPk_Enderecos() {
		return pk_Enderecos;
	}

	public void setPk_Enderecos(int pk_Enderecos) {
		this.pk_Enderecos = pk_Enderecos;
	}

	public int getFk() {
		return fk;
	}

	public void setFk(int fk) {
		this.fk = fk;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
