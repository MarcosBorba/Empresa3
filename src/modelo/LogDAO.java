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
import controlador.Log;

/**
 * insere o Log 'log' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class LogDAO {
	public static int create(Log log) {
		if (log.getPk_log() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("insert into log (data,usuario,descricao) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, log.getDataAcesso().toString());
			stm.setString(2, log.getUsuario());
			stm.setString(3, log.getDescricao());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			log.setPk_log(idGerado);

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
	public static Log retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from log where pk_log = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Log(resultset.getInt("pk_log"),
					       resultset.getDate("data"),
					       resultset.getString("usuario"), 
					       resultset.getString("descricao"));
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
	public static ArrayList<Log> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from log where pk_log=?");
			stm.setInt(1,pk);
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Log> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Log(resultset.getInt("pk_log"),
					       resultset.getDate("data"),
					       resultset.getString("usuario"), 
					       resultset.getString("descricao")));
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
	 * @param log
	 * @author marcos
	 */
	public static void update(Log log) {
		if (log.getPk_log() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE log SET data=?, usuario=?, descricao WHERE pk_log=?;");
			stm.setDate(1, (Date) log.getDataAcesso());
			stm.setString(2, log.getUsuario());
			stm.setString(2, log.getDescricao());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param log
	 * @author marcos
	 */
	public static void delete(Log log) {
		if (log.getPk_log() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM log WHERE pk_log=?;");
			stm.setInt(1, log.getPk_log());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
