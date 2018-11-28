package modelo;

import java.sql.Connection;
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
import controlador.Vendas;
import java.sql.Date;

/**
 * insere o Vendas 'vendas' no banco de dados
 * 
 * @param o
 *            objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class VendasDAO {
	public static int create(Vendas vendas) {
		if (vendas.getPk_Vendas() != 0) {
			throw new RuntimeException("Objeto ja existente");
		}else if(vendas.getCliente() == null) {
			throw new RuntimeException("Cliente nao foi adicionado");
		}else if(vendas.getFuncionario() == null) {
			throw new RuntimeException("Funcionario nao foi adicionado");
		}else if(vendas.getCliente().getPk() == 0) {
			throw new RuntimeException("Cliente novo insira");
		}else if(vendas.getFuncionario().getPk() == 0) {
			throw new RuntimeException("Funcionario novo insira");
		}else if(vendas.getVendasItens().size() == 0||vendas.getVendasItens() == null) {
			throw new RuntimeException("Itens nao foram adicionados");
		}else if(vendas.getFinanceiroEntradas().size() == 0||vendas.getFinanceiroEntradas() == null) {
			throw new RuntimeException("Financeiros nao foram adicionados");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement(
					"insert into vendas (fk_cliente,fk_vendedor,datavenda,numero) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stm.setObject(1, vendas.getCliente().getPk());
			stm.setObject(2, vendas.getFuncionario().getPk());
			stm.setTimestamp(3, new Timestamp(vendas.getDataVenda().getTime()));
			stm.setInt(4, (int)vendas.getNumeroVenda());

			stm.execute();

			ResultSet result1 = stm.getGeneratedKeys();
			result1.next();
			int idGerado = result1.getInt(1);
			vendas.setPk_Vendas(idGerado);
			for(int i=0;i <vendas.getVendasItens().size();i++) {
				vendas.getVendasItens().get(i).setFk_venda(idGerado);
			}
			for(int i=0;i <vendas.getFinanceiroEntradas().size();i++) {
				vendas.getFinanceiroEntradas().get(i).setFk_venda(idGerado);
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
	public static Vendas retreave(int pk) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from vendas where pk_venda = ?");
			stm.setInt(1, pk);

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Vendas(pk, 
					   ClienteDAO.retreave(resultset.getInt("fk_cliente")),
			           FuncionarioDAO.retreave(resultset.getInt("fk_vendedor")),
			           resultset.getDate("datavenda"),
			           resultset.getInt("numero"),
			           VendasItensDAO.retreaveAll(resultset.getInt("pk_venda")),
			           FinanceiroEntradasDAO.retreaveAll(resultset.getInt("pk_venda")));
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
	public static ArrayList<Vendas> retreaveAll() {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("select * from vendas");
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Vendas> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Vendas(resultset.getInt("pk_venda"), 
					   ClienteDAO.retreave(resultset.getInt("fk_cliente")),
				           FuncionarioDAO.retreave(resultset.getInt("fk_vendedor")),
				           resultset.getDate("datavenda"),
				           resultset.getInt("numero")));
			}

			return auxiliar;
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
	public static ArrayList<Vendas> retreaveDate(String date) {
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2.prepareStatement("SELECT * FROM vendas Where datavenda >= ?");
                        stm.setDate(1, Date.valueOf(date));
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Vendas> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Vendas(resultset.getInt("pk_venda"), 
					   ClienteDAO.retreave(resultset.getInt("fk_cliente")),
				           FuncionarioDAO.retreave(resultset.getInt("fk_vendedor")),
				           resultset.getDate("datavenda"),
				           resultset.getInt("numero")));
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
	 * @param venda
	 * @author marcos
	 */
	public static void update(Vendas venda) {
		if (venda.getPk_Vendas() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE vendas SET datavenda=?, numero=? WHERE pk_venda=?;");
			stm.setTimestamp(1, new Timestamp(venda.getDataVenda().getTime()));
			stm.setInt(2, venda.getNumeroVenda());
			stm.setInt(3,venda.getPk_Vendas());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * 
	 * @param venda
	 * @author marcos
	 */
	public static void delete(Vendas venda) {
		if (venda.getPk_Vendas() == 0) {
			throw new RuntimeException("Objeto nao existe no BD");
		}
		try {
			Connection conn = BancoDados.createConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM vendas WHERE pk_venda=?;");
			stm.setInt(1, venda.getPk_Vendas());

			stm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
