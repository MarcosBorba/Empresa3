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
import controlador.Cliente;
import controlador.Cpf;

/**
 * insere o Cliente 'cliente' no banco de dados
 *
 * @param o objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class ClienteDAO {

    public static int create(Cliente cliente) {
        if (cliente.getPk() != 0) {
            throw new RuntimeException("Objeto ja existente");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("insert into clientes (nome,cpf) values (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf().getCpf());

            stm.execute();

            ResultSet result1 = stm.getGeneratedKeys();
            result1.next();
            int idGerado = result1.getInt(1);
            cliente.setPk(idGerado);
            if (cliente.getEnderecos() != null) {
                for (int i = 0; i < cliente.getEnderecos().size(); i++) {
                    cliente.getEnderecos().get(i).setFk(idGerado);
                    ClienteEnderecoDAO.create(cliente.getEnderecos().get(i));
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
    public static Cliente retreave(int pk) {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from clientes where pk_cliente = ?");
            stm.setInt(1, pk);

            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            if (!resultset.next()) {
                throw new RuntimeException("Chave primaria nao encontrada");
            }
            return new Cliente(pk,
                    resultset.getString("nome"),
                    ClienteEnderecoDAO.retreaveAll(resultset.getInt("pk_cliente")),
                    new Cpf(resultset.getString("cpf")));
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
    public static ArrayList<Cliente> retreaveAll() {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from clientes order by nome");
            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            ArrayList<Cliente> auxiliar = new ArrayList<>();

            while (resultset.next()) {
                auxiliar.add(new Cliente(resultset.getInt("pk_cliente"),
                        resultset.getString("nome"),
                        ClienteEnderecoDAO.retreaveAll(resultset.getInt("pk_cliente")),
                        new Cpf(resultset.getString("cpf"))));
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
    public static void update(Cliente cliente) {
        if (cliente.getPk() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("UPDATE clientes SET nome=?, cpf=? WHERE pk_cliente=?;");
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf().getCpf());
            stm.setInt(3, cliente.getPk());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Deleta o elemento no banco de dados onde a pk informada for true
     *
     * @param cliente
     * @author marcos
     */
    public static void delete(Cliente cliente) {
        if (cliente.getPk() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM clientes WHERE pk_cliente=?;");
            stm.setInt(1, cliente.getPk());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
