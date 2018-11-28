package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import controlador.Cliente;
import controlador.Enderecos;
/**
 * Implementa o CRUD para objeto controlador Create/Retreave/Update/Delete
 * @author marcos
 */
import controlador.Fornecedor;
import controlador.Funcionario;
import controlador.Sujeito;
/**
 * insere o objeto de Enderecos 'enderecos' relacionando a pk do objeto relacionado com a fk no banco de dados
 * @param o objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 */
public class EnderecosDAO {
	/*public static int create(Object pessoa) {
		Sujeito sujeito = null;
		String nomeTabela = null;
		String foreignKey = null;
		if (pessoa instanceof Funcionario) {
			sujeito = (Funcionario) pessoa;
			nomeTabela = "funcionariosenderecos";
			foreignKey = "fk_funcionario";
		} else if (pessoa instanceof Cliente) {
			sujeito = (Cliente) pessoa;
			nomeTabela = "clientesenderecos";
			foreignKey = "fk_cliente";
		} else if (pessoa instanceof Fornecedor) {
			sujeito = (Fornecedor) pessoa;
			nomeTabela = "fornecedoresenderecos";
			foreignKey = "fk_fornecedor";
		}
		if (sujeito.getEnderecos().size() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		for (int i = 0; i < sujeito.getEnderecos().size(); i++) {
			try {
				Connection conn = BancoDados.createConnection();
				PreparedStatement stm = conn.prepareStatement(
						"insert into" + nomeTabela + "(" + foreignKey
								+ ",logradouro,bairro,cidade,estado,pais,cep) values (?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				stm.setInt(1, sujeito.getPk());
				stm.setString(2, sujeito.getEnderecos().get(i).getLogradouro());
				stm.setString(3, sujeito.getEnderecos().get(i).getBairro());
				stm.setString(4, sujeito.getEnderecos().get(i).getCidade());
				stm.setString(5, sujeito.getEnderecos().get(i).getEstado());
				stm.setString(6, sujeito.getEnderecos().get(i).getPais());
				stm.setString(7, sujeito.getEnderecos().get(i).getCep());

				stm.execute();

				ResultSet result1 = stm.getGeneratedKeys();
				result1.next();
				int idGerado = result1.getInt(1);
				sujeito.getEnderecos().get(i).setPk_Enderecos(idGerado);

				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	*//**
	 * Seleciona todos elementos onde pk no banco de dados for true
	 * @param pk
	 * @return null
	 * @author marcos
	 *//*
	public static Enderecos retreave(Object pessoa) {
		Sujeito sujeito = null;
		String nomeTabela = null;
		String foreignKey = null;
		String primaryKey = null;
		if (pessoa instanceof Funcionario) {
			sujeito = (Funcionario) pessoa;
			nomeTabela = "funcionariosenderecos";
			foreignKey = "fk_funcionario";
			primaryKey = "pk_endereco_funcionario";
		} else if (pessoa instanceof Cliente) {
			sujeito = (Cliente) pessoa;
			nomeTabela = "clientesenderecos";
			foreignKey = "fk_cliente";
			primaryKey = "pk_endereco_cliente";
		} else if (pessoa instanceof Fornecedor) {
			sujeito = (Fornecedor) pessoa;
			nomeTabela = "fornecedoresenderecos";
			foreignKey = "fk_fornecedor";
			primaryKey = "pk_endereco_fornecedor";
		}
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2
					.prepareStatement("select * from " + nomeTabela + " where " + foreignKey + " = ?");
			stm.setInt(1, sujeito.getPk());

			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			if (!resultset.next()) {
				throw new RuntimeException("Chave primaria nao encontrada");
			}
			return new Enderecos(resultset.getInt(primaryKey), resultset.getString("logradouro"),
					resultset.getString("bairro"), resultset.getString("cidade"), resultset.getString("estado"),
					resultset.getString("pais"), resultset.getString("cep"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*//**
	 * Seleciona todos elementos de todas pk no banco de dados
	 * @param pessoa
	 * @return null
	 * @author marcos
	 *//*
	public static ArrayList<Enderecos> retreaveAll(Object pessoa) {
		Sujeito sujeito = null;
		String nomeTabela = null;
		String foreignKey = null;
		if (pessoa instanceof Funcionario) {
			sujeito = (Funcionario) pessoa;
			nomeTabela = "funcionariosenderecos";
			foreignKey = "fk_funcionario";
		} else if (pessoa instanceof Cliente) {
			sujeito = (Cliente) pessoa;
			nomeTabela = "clientesenderecos";
			foreignKey = "fk_cliente";
		} else if (pessoa instanceof Fornecedor) {
			sujeito = (Fornecedor) pessoa;
			nomeTabela = "fornecedoresenderecos";
			foreignKey = "fk_fornecedor";
		}
		try {
			Connection conn2 = BancoDados.createConnection();
			PreparedStatement stm = conn2
					.prepareStatement("select * from " + nomeTabela + " where " + foreignKey + " = ? ");
			stm.setInt(1, sujeito.getPk());
			stm.executeQuery();

			ResultSet resultset = stm.getResultSet();

			ArrayList<Enderecos> auxiliar = new ArrayList<>();

			while (resultset.next()) {
				auxiliar.add(new Enderecos(resultset.getInt(foreignKey), resultset.getString("logradouro"),
						resultset.getString("bairro"), resultset.getString("cidade"), resultset.getString("estado"),
						resultset.getString("pais"), resultset.getString("cep")));
			}

			return auxiliar;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*//**
	 * Atualiza o elemento no banco de dados onde a pk informada for true
	 * @param pessoa
	 * @author marcos
	 *//*
	public static void update(Object pessoa) {
		Sujeito sujeito = null;
		String nomeTabela = null;
		String foreignKey = null;
		String primaryKey = null;
		if (pessoa instanceof Funcionario) {
			sujeito = (Funcionario) pessoa;
			nomeTabela = "funcionariosenderecos";
			foreignKey = "fk_funcionario";
			primaryKey = "pk_endereco_funcionario";
		} else if (pessoa instanceof Cliente) {
			sujeito = (Cliente) pessoa;
			nomeTabela = "clientesenderecos";
			foreignKey = "fk_cliente";
			primaryKey = "pk_endereco_cliente";
		} else if (pessoa instanceof Fornecedor) {
			sujeito = (Fornecedor) pessoa;
			nomeTabela = "fornecedoresenderecos";
			foreignKey = "fk_fornecedor";
			primaryKey = "pk_endereco_fornecedor";
		}
		if (sujeito.getEnderecos().size() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		for (int i = 0; i < sujeito.getEnderecos().size(); i++) {
			try {
				Connection conn = BancoDados.createConnection();
				PreparedStatement stm = conn.prepareStatement("UPDATE " + nomeTabela + "SET " + foreignKey
						+ "=?, logradouro=? , bairro=?, cidade=?, estado=?, pais=?, cep=? WHERE " + primaryKey + "=?;");
				stm.setInt(1, sujeito.getPk());
				stm.setString(2, sujeito.getEnderecos().get(i).getLogradouro());
				stm.setString(3, sujeito.getEnderecos().get(i).getBairro());
				stm.setString(4, sujeito.getEnderecos().get(i).getCidade());
				stm.setString(5, sujeito.getEnderecos().get(i).getEstado());
				stm.setString(6, sujeito.getEnderecos().get(i).getPais());
				stm.setString(7, sujeito.getEnderecos().get(i).getCep());
				stm.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	*//**
	 * Deleta o elemento no banco de dados onde a pk informada for true
	 * @param pessoa
	 * @author marcos
	 *//*
	public static void delete(Object pessoa) {
		Sujeito sujeito = null;
		String nomeTabela = null;
		String primaryKey = null;
		if (pessoa instanceof Funcionario) {
			sujeito = (Funcionario) pessoa;
			nomeTabela = "funcionariosenderecos";
			primaryKey = "pk_endereco_funcionario";
		} else if (pessoa instanceof Cliente) {
			sujeito = (Cliente) pessoa;
			nomeTabela = "clientesenderecos";
			primaryKey = "pk_endereco_cliente";
		} else if (pessoa instanceof Fornecedor) {
			sujeito = (Fornecedor) pessoa;
			nomeTabela = "fornecedoresenderecos";
			primaryKey = "pk_endereco_fornecedor";
		}
		if (sujeito.getEnderecos().size() == 0) {
			throw new RuntimeException("Objeto ja existente");
		}
		for (int i = 0; i < sujeito.getEnderecos().size(); i++) {
			try {
				Connection conn = BancoDados.createConnection();
				PreparedStatement stm = conn
						.prepareStatement("DELETE FROM " + nomeTabela + " WHERE " + primaryKey + " =?;");
				stm.setInt(1, sujeito.getEnderecos().get(i).getPk_Enderecos());

				stm.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}