package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Enderecos;

public class ClienteEnderecoDAO {
	public static int create(Enderecos o) {

		if (o.getPk_Enderecos() != 0) {
			throw new RuntimeException("O objeto ja foi inserido no banco de dados");
		}
		try {

			Connection conn = BancoDados.createConnection();

			PreparedStatement stm = conn.prepareStatement(
					"INSERT INTO clientesenderecos(fk_cliente, logradouro, bairro, cidade, estado, pais, cep) VALUES (?, ?, ?, ?, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setInt(1, o.getFk());
			stm.setString(2, o.getLogradouro());
			stm.setString(3, o.getBairro());
			stm.setString(4, o.getCidade());
			stm.setString(5, o.getEstado());
			stm.setString(6, o.getPais());
			stm.setString(7, o.getCep());

			stm.execute();

			ResultSet rs = stm.getGeneratedKeys();
			rs.next();
			o.setPk_Enderecos(rs.getInt(1));
			return o.getPk_Enderecos();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Retorna um objeto cargo que representa a t-upla da pk informada
	 * 
	 * @param pk
	 *            a chave primaria do cargo a ser retornado
	 * @return o cargo relativo a pk informada
	 */
	public static Enderecos retreave(int pk) {
		try {

			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn
					.prepareStatement("select * from clientesenderecos where pk_endereco = ?");

			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet rs = stm.getResultSet();

			if (!rs.next()) {// if (rs.next()==false)
				throw new RuntimeException("chave primaria nao encontrada");
			}

			return new Enderecos(pk, rs.getInt("fk_cliente"), rs.getString("logradouro"), rs.getString("bairro"),
					rs.getString("cidade"), rs.getString("estado"), rs.getString("pais"), rs.getString("cep"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retorna um objeto cargo que representa a t-upla da pk informada
	 * 
	 * @param pk
	 *            a chave primaria do cargo a ser retornado
	 * @return o cargo relativo a pk informada
	 */
	public static ArrayList<Enderecos> retreaveAll(int fk) {
		try {

			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn
					.prepareStatement("select * from clientesenderecos where fk_cliente = ?");
			stm.setInt(1, fk);

			stm.executeQuery();

			ResultSet rs = stm.getResultSet();

			ArrayList<Enderecos> aux = new ArrayList<>();

			while (rs.next()) {
				aux.add(new Enderecos(rs.getInt("pk_endereco"), fk, rs.getString("logradouro"),
						rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"), rs.getString("pais"),
						rs.getString("cep")));
			}

			return aux;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void update(Enderecos o) {

		if (o.getPk_Enderecos() == 0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}

		try {

			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"UPDATE clientesenderecos SET fk_cliente=?, logradouro=?, bairro=?, cidade=?, estado=?, pais=?, cep=? WHERE pk_endereco = ?");

			stm.setInt(1, o.getFk());
			stm.setString(2, o.getLogradouro());
			stm.setString(3, o.getBairro());
			stm.setString(4, o.getCidade());
			stm.setString(5, o.getEstado());
			stm.setString(6, o.getPais());
			stm.setString(7, o.getCep());
			stm.setInt(8, o.getPk_Enderecos());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delete(Enderecos o) {

		if (o.getPk_Enderecos() == 0) {
			throw new RuntimeException("O objeto nao existe no banco de dados");
		}

		try {

			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn
					.prepareStatement("DELETE FROM clientesenderecos WHERE pk_endereco = ?");

			stm.setInt(1, o.getPk_Enderecos());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
