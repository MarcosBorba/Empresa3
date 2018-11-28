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
import controlador.VendasItens;

/**
 * insere o VendasItens 'vendasItens' no banco de dados
 *
 * @param o objeto a ser inserido
 * @return a chave primaria gerada
 * @author marcos
 *
 */
public class VendasItensDAO {

    public static int create(VendasItens vendasItens) {
        if (vendasItens.getPk_Item() != 0) {
            throw new RuntimeException("Objeto ja existente");
        } else if (vendasItens.getFk_produto().getPk_Produto() != 0) {
            throw new RuntimeException("Produto ja adicionado");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement(
                    "insert into vendasitens (fk_venda,fk_produto,qtd,valorunitario) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setObject(1, vendasItens.getFk_venda());
            stm.setObject(2, vendasItens.getFk_produto());
            stm.setInt(3, vendasItens.getQuantidadeDeItens());
            stm.setDouble(4, vendasItens.getValorUnitarioProduto());

            stm.execute();

            ResultSet result1 = stm.getGeneratedKeys();
            result1.next();
            int idGerado = result1.getInt(1);
            vendasItens.setPk_Item(idGerado);

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
    public static VendasItens retreave(int pk) {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from vendasitens where pk_item = ?");
            stm.setInt(1, pk);

            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            if (!resultset.next()) {
                throw new RuntimeException("Chave primaria nao encontrada");
            }
            return new VendasItens(resultset.getInt("pk_item"),
                    resultset.getInt("fk_venda"),
                    ProdutosDAO.retreave(resultset.getInt("fk_produto")),
                    resultset.getInt("qtd"),
                    resultset.getDouble("valorunitario"),
                    resultset.getDouble("valor_total"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Seleciona todos elementos de todas pk no banco de dados
     *
     * @param fk_venda
     * @return null
     * @author marcos
     */
    public static ArrayList<VendasItens> retreaveAll(int fk_venda) {
        try {
            Connection conn2 = BancoDados.createConnection();
            PreparedStatement stm = conn2.prepareStatement("select * from vendasitens where fk_venda=?");
            stm.setInt(1, fk_venda);
            stm.executeQuery();

            ResultSet resultset = stm.getResultSet();

            ArrayList<VendasItens> auxiliar = new ArrayList<>();

            while (resultset.next()) {
                auxiliar.add(new VendasItens(resultset.getInt("pk_item"),
                        resultset.getInt("fk_venda"),
                        ProdutosDAO.retreave(resultset.getInt("fk_produto")),
                        resultset.getInt("qtd"),
                        resultset.getDouble("valorunitario"),
                        resultset.getDouble("valor_total")));
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
     * @param vendasItens
     * @author marcos
     */
    public static void update(VendasItens vendasItens) {
        if (vendasItens.getPk_Item() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn
                    .prepareStatement("UPDATE vendasitens SET qtd=?, valorunitario=? WHERE pk_item=?;");
            stm.setInt(1, vendasItens.getQuantidadeDeItens());
            stm.setDouble(2, vendasItens.getValorUnitarioProduto());
            stm.setInt(3, vendasItens.getPk_Item());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Deleta o elemento no banco de dados onde a pk informada for true
     *
     * @param vendaItem
     * @author marcos
     */
    public static void delete(VendasItens vendaItem) {
        if (vendaItem.getPk_Item() == 0) {
            throw new RuntimeException("Objeto nao existe no BD");
        }
        try {
            Connection conn = BancoDados.createConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM vendasitens WHERE pk_item=?;");
            stm.setInt(1, vendaItem.getPk_Item());

            stm.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
