package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Cpf;
/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * 
 * @author marcos
 *
 */
import controlador.Fornecedor;

/**
 * insere o Fornecedor 'fornecedor' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class FornecedorDAO {
	public static int create(Fornecedor fornecedor) {
		if (fornecedor.getPk() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("insert into fornecedores (nome,cpf_cnpj) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCpf().getCpf());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			fornecedor.setPk(idGerado);
			if(fornecedor.getEnderecos()!=null) {
				for(int i=0;i<fornecedor.getEnderecos().size();i++) {
					fornecedor.getEnderecos().get(i).setFk(idGerado);
				}
			}
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
	public static Fornecedor retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from fornecedores where pk_fornecedor = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Fornecedor(pk, 
					              resultset.getString("nome"), 
					              FornecedorEnderecoDAO.retreaveAll(resultset.getInt("pk_fornecedor")),
					              new Cpf(resultset.getString("cpf")),
					              ComprasDAO.retreaveAll(pk));
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
	public static ArrayList<Fornecedor> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from fornecedores where pk_fornecedor=?");
			stm.setInt(1,pk);
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Fornecedor> auxiliar = new ArrayList<>();
			while (resultset.next()) {
				auxiliar.add(new Fornecedor(pk, 
			              resultset.getString("nome"), 
			              FornecedorEnderecoDAO.retreaveAll(resultset.getInt("pk_fornecedor")),
			              new Cpf(resultset.getString("cpf")),
			              ComprasDAO.retreaveAll(pk)));
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
	 * @param fornecedor
	 * @author marcos
	 */
	public static void update(Fornecedor fornecedor) {
		if (fornecedor.getPk() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE fornecedores SET nome=?, cpf=? WHERE pk_fornecedor=?;");
			stm.setString(1, fornecedor.getNome());
			stm.setString(2, fornecedor.getCpf().getCpf());
			stm.setInt(3,fornecedor.getPk());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param fornecedor
	 * @author marcos
	 */
	public static void delete(Fornecedor fornecedor) {
		if (fornecedor.getPk() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM fornecedores WHERE pk_fornecedor=?;");
			stm.setInt(1, fornecedor.getPk());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
