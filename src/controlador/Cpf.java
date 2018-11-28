package controlador;

public class Cpf {

	private String cpf;

	public Cpf() {

	}

	public Cpf(String cpf) {
		super();
		this.cpf = cpf;
	}

	/**
	 * Valida um cpf informado
	 * 
	 * @param cpf
	 *            ,o cpf que for validado, sem uso de separadores
	 * @return true se o cpf for valido, false caso o contrario
	 */
	public static boolean validaCpf(String cpf) {
		return true;
	}

	/**
	 * Valida um cpf informado
	 * 
	 * @param cpf
	 *            ,o cpf que for validado, sem uso de separadores
	 * @return true se o cpf for valido, false caso o contrario
	 */
	public boolean validaCpf() {
		return validaCpf(this.cpf);
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
		return "Cpf [cpf=" + cpf + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
