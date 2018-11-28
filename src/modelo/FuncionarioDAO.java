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
import controlador.Funcionario;

;

/**
 * insere o Funcionario 'funcionario' no banco de dados
 *
 * @param o objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class FuncionarioDAO {

    public static int create(Funcionario funcionario) {
        if (funcionario.getPk() != 0) {
            throw new RuntimeException("Objeto ja existente");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("insert into funcionarios (fk_cargo,nome,cpf) values (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, funcionario.getFk_Cargo().getpk_Cargo());
            stm.setString(2, funcionario.getNome());
            stm.setString(3, funcionario.getCpf().getCpf());

            stm.execute();

            ResultSet result1 = stm.getGeneratedKeys();
            result1.next();
            int idGerado = result1.getInt(1);
            funcionario.setPk(idGerado);

            if (funcionario.getEnderecos() != null) {
                for (int i = 0; i < funcionario.getEnderecos().size(); i++) {
                    funcionario.getEnderecos().get(i).setFk(idGerado);
                    FuncionarioEnderecoDAO.create(funcionario.getEnderecos().get(i));
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
    public static Funcionario retreave(int pk) {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from funcionarios where pk_funcionario = ?");
            stm.setInt(1, pk);

            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            if (!resultset.next()) {
                throw new RuntimeException("Chave primaria nao encontrada");
            }

            return new Funcionario(pk,
                    resultset.getString("nome"),
                    FuncionarioEnderecoDAO.retreaveAll(resultset.getInt("pk_funcionario")),
                    new Cpf(resultset.getString("cpf")),
                    CargosDAO.retreave(resultset.getInt("fk_cargo"))
            );
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
    public static ArrayList<Funcionario> retreaveAll() {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from funcionarios order by nome");

            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            ArrayList<Funcionario> auxiliar = new ArrayList<>();
            while (resultset.next()) {
                auxiliar.add(new Funcionario(resultset.getInt("pk_funcionario"),
                        resultset.getString("nome"),
                        FuncionarioEnderecoDAO.retreaveAll(resultset.getInt("pk_funcionario")),
                        new Cpf(resultset.getString("cpf")),
                        CargosDAO.retreave(resultset.getInt("fk_cargo"))
                ));
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
     * @param funcionario
     * @author marcos
     */
    public static void update(Funcionario funcionario) {
        if (funcionario.getPk() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("UPDATE funcionarios SET nome=?, cpf=?, fk_cargo=? WHERE pk_funcionario=?;");
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getCpf().getCpf());
            stm.setInt(3,funcionario.getFk_Cargo().getpk_Cargo());
            stm.setInt(4, funcionario.getPk());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Deleta o elemento no banco de dados onde a pk informada for true
     *
     * @param funcionario
     * @author marcos
     */
    public static void delete(Funcionario funcionario) {
        if (funcionario.getPk() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM funcionarios WHERE pk_funcionario=?;");
            stm.setInt(1, funcionario.getPk());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
