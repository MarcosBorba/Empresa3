package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * 
 * @author marcos
 *
 */
import controlador.FinanceiroSaidas;;

/**
 * insere o FinanceiroSaidas 'financeiroSaidas' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 */
public class FinanceiroSaidasDAO {
	public static int create(FinanceiroSaidas financeiroSaidas) {
		if (financeiroSaidas.getPk_Saidas() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into financeiroSaidas (fk_compra,dataemissao,datavencimento,databaixa,valor,tipodocumento) values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setLong(1, financeiroSaidas.getCompras().getPk_Compra());
			stm.setString(2, financeiroSaidas.getDataEmissaoPagar().toString());
			stm.setString(3, financeiroSaidas.getDataVencimentoPagamento().toString());
			stm.setString(4, financeiroSaidas.getDataBaixa().toString());
			stm.setLong(5, (long) financeiroSaidas.getValorPago());
			stm.setString(6, financeiroSaidas.getTipoDocumentoPago());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			financeiroSaidas.setPk_Saidas(idGerado);
			
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
	public static FinanceiroSaidas retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from where pk_financeirosasida = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new FinanceiroSaidas(resultset.getInt("pk_saida"),
                    ComprasDAO.retreave(pk),
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
	 * @param pk
	 * @return null
	 * @author marcos
	 */
	public static ArrayList<FinanceiroSaidas> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			 PreparedStatement stm = conn2.prepareStatement("select * from financeirosaidas where pk_saida=?");

			stm.setInt(1,pk);

			ResultSet resultset = stm.getResultSet();

			ArrayList<FinanceiroSaidas> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new FinanceiroSaidas(resultset.getInt("pk_saida"),
						                          ComprasDAO.retreave(pk),
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
	 * @param financeirosaidas
	 * @author marcos
	 */
	public static void update(FinanceiroSaidas financeirosaidas) {
		if (financeirosaidas.getPk_Saidas() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE financeirosaidas SET fk_compra=?, dataemissao=?, datavencimento=?, databaixa=?, valor=? tipodocumento=? WHERE pk_financeirosaidas=?;");
			stm.setInt(1, financeirosaidas.getCompras().getPk_Compra());
			stm.setDate(2, (Date) financeirosaidas.getDataEmissaoPagar());
			stm.setDate(3, (Date) financeirosaidas.getDataVencimentoPagamento());
			stm.setDate(4, (Date) financeirosaidas.getDataBaixa());
			stm.setDouble(5, financeirosaidas.getValorPago());
			stm.setString(6, financeirosaidas.getTipoDocumentoPago());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param financeirosaidas
	 * @author marcos
	 */
	public static void delete(FinanceiroSaidas financeirosaidas) {
		if (financeirosaidas.getPk_Saidas() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM financeirosaidas WHERE pk_saida=?;");
			stm.setInt(1, financeirosaidas.getPk_Saidas());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
