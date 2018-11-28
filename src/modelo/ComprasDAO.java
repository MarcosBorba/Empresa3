package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * 
 * @author marcos
 *
 */
import controlador.Compras;
import controlador.Fornecedor;

/**
 * insere o Compras 'compra' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class ComprasDAO {

	public static int create(Compras compra) {
		if (compra.getPk_Compra() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into compras (fk_fornecedor,numero,datacompra) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setLong(1, compra.getFornecedor().getPk());
			stm.setString(2, compra.getNumeroCompra());
			stm.setString(3, compra.getDataCompra().toString());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			compra.setPk_Compra(idGerado);
			
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
	public static Compras retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from compras where pk_compra = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Compras(resultset.getInt("pk_compra"), 
					           (Fornecedor)resultset.getObject("fk_fornecedor"),
					           resultset.getString("numero"),
					           resultset.getDate("datacompra"));
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
	public static ArrayList<Compras> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			Statement stm = conn2.createStatement();

			stm.executeQuery("select * from compras");

			ResultSet resultset = stm.getResultSet();

			ArrayList<Compras> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Compras(resultset.getInt("pk_compra"), 
				           (Fornecedor)resultset.getObject("fk_fornecedor"),
				           resultset.getString("numero"),
				           resultset.getDate("datacompra")));
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
	 * @param compras
	 * @author marcos
	 */
	public static void update(Compras compras) {
		if (compras.getPk_Compra() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE compras SET fk_fornecedor=?, numero=? , datacompra=? WHERE pk_compra=?;");
			stm.setInt(1, compras.getFornecedor().getPk());
			stm.setString(2, compras.getNumeroCompra());
			stm.setDate(3, (Date) compras.getDataCompra());
			stm.setInt(4,compras.getPk_Compra());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param compras
	 * @author marcos
	 */
	public static void delete(Compras compras) {
		if (compras.getPk_Compra() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM compras WHERE pk_compra=?;");
			stm.setInt(1, compras.getPk_Compra());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
