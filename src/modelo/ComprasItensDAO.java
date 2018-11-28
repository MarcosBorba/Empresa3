package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.Compras;
/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * 
 * @author marcos
 *
 */
import controlador.ComprasItens;
import controlador.Produtos;;

/**
 * insere o ComprasItens 'ComprasItens' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class ComprasItensDAO {
	public static int create(ComprasItens comprasItens) {
		if (comprasItens.getPk_Item() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into comprasitens (fk_compra,fk_produto,qtd,valorunitario) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setLong(1, comprasItens.getCompras().getPk_Compra());
			stm.setLong(2, comprasItens.getProdutos().getPk_Produto());
			stm.setLong(3, comprasItens.getQuantidadeDeItens());
			stm.setLong(4, (long) comprasItens.getValorUnitarioProduto());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			comprasItens.setPk_Item(idGerado);

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
	public static ComprasItens retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from comprasitens where pk_item = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new ComprasItens(resultset.getInt("pk_item"), 
					                (Compras)resultset.getObject("fk_compra"),
					                (Produtos)resultset.getObject("fk_produto"), 
					                resultset.getInt("qtd"), 
					                resultset.getDouble("valorunitario"));
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
	public static ArrayList<ComprasItens> retreaveAll(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			
			PreparedStatement stm = conn2.prepareStatement("select * from compraitens where pk_item=?");
			stm.setInt(1,pk);
			
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<ComprasItens> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new ComprasItens(resultset.getInt("pk_item"), 
						                      (Compras)resultset.getObject("fk_compra"),
						                      (Produtos)resultset.getObject("fk_produto"), 
						                      resultset.getInt("qtd"), 
						                      resultset.getDouble("valorunitario")));
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
	public static void update(ComprasItens comprasItens) {
		if (comprasItens.getPk_Item() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"UPDATE compraitens SET fk_compra=?, fk_produto=? , qtd=?, valorunitario=? WHERE pk_item=?;");
			stm.setLong(1, comprasItens.getCompras().getPk_Compra());
			stm.setLong(2, comprasItens.getProdutos().getPk_Produto());
			stm.setLong(3, comprasItens.getQuantidadeDeItens());
			stm.setLong(4, (long) comprasItens.getValorUnitarioProduto());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param comprasItens
	 * @author marcos
	 */
	public static void delete(ComprasItens comprasItens) {
		if (comprasItens.getPk_Item() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM comprasitens WHERE pk_item=?;");
			stm.setInt(1, comprasItens.getPk_Item());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
