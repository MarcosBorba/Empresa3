package modelo;

import java.sql.Connection;
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
import controlador.Produtos;

/**
 * insere o Produtos 'produtos' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class ProdutosDAO {
	public static int create(Produtos produtos) {
		if (produtos.getPk_Produto() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into produtos (nome,estoqueminimo,qtsestoque) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setString(1, produtos.getNome());
			stm.setLong(2, produtos.getEstoqueMinimo());
			stm.setLong(3, produtos.getQuantidadeEstoque());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			produtos.setPk_Produto(idGerado);

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
	 * @param fk
	 * @return null
	 * @author marcos
	 */
	public static Produtos retreave(int fk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from produtos where pk_produto = ?");
			stm.setInt(1, fk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Produtos(resultset.getInt("pk_produto"), resultset.getString("nome"),
					resultset.getInt("estoqueminimo"), resultset.getInt("qtdestoque"));
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
	public static ArrayList<Produtos> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from produtos where pk_produtos");
			stm.setInt(1, pk);
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Produtos> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Produtos(resultset.getInt("pk_produto"), resultset.getString("nome"),
						resultset.getInt("estoqueminimo"), resultset.getInt("qtdestoque")));
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
	 * @param cliente
	 * @author marcos
	 */
	public static void update(Produtos produto) {
		if (produto.getPk_Produto() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn
					.prepareStatement("UPDATE produtos SET nome=?, estoqueminimo=?, qtsestoque=? WHERE pk_produto=?;");
			stm.setString(1, produto.getNome());
			stm.setInt(2, produto.getEstoqueMinimo());
			stm.setInt(3, produto.getQuantidadeEstoque());
			stm.setInt(4, produto.getPk_Produto());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param produto
	 * @author marcos
	 */
	public static void delete(Produtos produto) {
		if (produto.getPk_Produto() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM produtos WHERE pk_produto=?;");
			stm.setInt(1, produto.getPk_Produto());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
