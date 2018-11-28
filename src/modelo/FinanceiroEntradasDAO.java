package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * 
 * @author marcos
 *
 */
import controlador.FinanceiroEntradas;

/**
 * insere o FinanceiroEntradas 'financeiroEntradas' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class FinanceiroEntradasDAO { 
	public static int create(FinanceiroEntradas financeiroEntradas) {
		if (financeiroEntradas.getPk_Entrada() != 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into financeiroentradas (fk_venda,dataemissao,datavencimento,databaixa,valor,tipodocumento) values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setObject(1, financeiroEntradas.getFk_venda());
			stm.setTimestamp(2, new Timestamp(financeiroEntradas.getDataEmissaoPagar().getTime()));
			stm.setTimestamp(3, new Timestamp(financeiroEntradas.getDataVencimentoPagamento().getTime()));
			stm.setTimestamp(4, new Timestamp(financeiroEntradas.getDataBaixa().getTime()));
			stm.setDouble(5, financeiroEntradas.getValorPago());
			stm.setString(6, financeiroEntradas.getTipoDocumentoPago());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			financeiroEntradas.setPk_Entrada(idGerado);

			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * Seleciona todos elementos onde pk no banco de dados for true
	 * 
	 * @param pk
	 * @return null
	 * @author marcos
	 */
	public static FinanceiroEntradas retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from financeiroentradas where pk_entrada = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new FinanceiroEntradas(pk,
                    resultset.getInt("fk_venda"),
                    resultset.getDate("dataemissao"),
                    resultset.getDate("datavencimento"),
                    resultset.getDate("databaixa"),
                    resultset.getDouble("valor"),
                    resultset.getString("tipodocumento"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Seleciona todos elementos de todas pk no banco de dados
	 * 
	 * @param fk_venda
	 * @return null
	 * @author marcos
	 */
	public static ArrayList<FinanceiroEntradas> retreaveAll(int fk_venda) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from financeiroentradas where fk_venda=?");

			stm.setInt(1,fk_venda);
			stm.executeQuery();
			ResultSet resultset = stm.getResultSet();
			
			ArrayList<FinanceiroEntradas> auxiliar = new ArrayList<>();
			
			while(resultset.next()) {
				auxiliar.add(new FinanceiroEntradas(resultset.getInt("pk_entrada"),
						resultset.getInt("fk_venda"),
	                    resultset.getDate("dataemissao"),
	                    resultset.getDate("datavencimento"),
	                    resultset.getDate("databaixa"),
	                    resultset.getDouble("valor"),
	                    resultset.getString("tipodocumento")));
			}

			return auxiliar;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Atualiza o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param financeiroEntradas
	 * @author marcos
	 */
	public static void update(FinanceiroEntradas financeiroEntradas) {
		if (financeiroEntradas.getPk_Entrada() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE financeiroentradas SET fk_venda=?, dataemissao=?, datavencimento=?, databaixa=?, valor=? tipodocumento=? WHERE pk_financeiroentradas=?;");
			stm.setInt(1, financeiroEntradas.getFk_venda());
			stm.setDate(2, (Date) financeiroEntradas.getDataEmissaoPagar());
			stm.setDate(3, (Date) financeiroEntradas.getDataVencimentoPagamento());
			stm.setDate(4, (Date) financeiroEntradas.getDataBaixa());
			stm.setDouble(5, financeiroEntradas.getValorPago());
			stm.setString(6, financeiroEntradas.getTipoDocumentoPago());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param financeiroentradas
	 * @author marcos
	 */
	public static void delete(FinanceiroEntradas financeiroentradas) {
		if (financeiroentradas.getPk_Entrada() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM financeiroentradas WHERE pk_financeiroentrada=?;");
			stm.setInt(1, financeiroentradas.getPk_Entrada());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
