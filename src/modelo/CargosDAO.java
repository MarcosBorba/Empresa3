package modelo;

import java.sql.Connection;
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
import controlador.Cargo;

/**
 * insere o Cargo 'cargo' no banco de dados
 * 
 * @param o objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class CargosDAO {
	public static int create(Cargo cargo) {
		if (cargo.getpk_Cargo() != 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("insert into cargos (nome,descricao) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, cargo.getNome());
			stm.setString(2, cargo.getDescricao());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			cargo.setpk_Cargo(idGerado);
			
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * Seleciona todos elementos onde pk no banco de dados for true
	 * @param pk
	 * @return null
	 * @author marcos
	 */
	public static Cargo retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from cargos where pk_cargo = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Cargo(resultset.getInt("pk_cargo"), resultset.getString("nome"),
					resultset.getString("descricao"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * Seleciona todos elementos de todas pk no banco de dados
	 * @return null
	 * @author marcos
	 */
	public static ArrayList<Cargo> retreaveAll() {
		try {
			Connection conn2 = BancoDados.createConnection();
			Statement stm = conn2.createStatement();

			stm.executeQuery("select * from cargos order by nome");

			ResultSet resultset = stm.getResultSet();

			ArrayList<Cargo> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Cargo(resultset.getInt("pk_cargo"), 
						               resultset.getString("nome"),
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
	 * @param cargo
	 * @author marcos
	 */
	public static void update(Cargo cargo) {
		if (cargo.getpk_Cargo() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE cargos SET nome=?, descricao=? WHERE pk_cargo=?;");
			stm.setString(1, cargo.getNome());
			stm.setString(2, cargo.getDescricao());
			stm.setInt(3, cargo.getpk_Cargo());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param cargo
	 * @author marcos
	 */
	public static void delete(Cargo cargo) {
		if (cargo.getpk_Cargo() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM cargos WHERE pk_cargo=?;");
			stm.setInt(1, cargo.getpk_Cargo());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}