/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import empresa3.Empresa3;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.VendasDAO;
import modelo.VendasItensDAO;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCadastroVendaController implements Initializable {

    @FXML
    private TableView<Vendas> tvVenda;

    @FXML
    private TableView<VendasItens> tvVendaItem;
    
    @FXML
    private TextField idDate;
    
    @FXML
    void voltar(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_PRINCIPAL);
        tvVenda.getItems().clear();
        tvVendaItem.getItems().clear();
        tvVenda.getSelectionModel().clearSelection();
        idDate.setText("");
        tvVenda.setItems(FXCollections.observableArrayList(VendasDAO.retreaveAll()));
    }
    
     @FXML
    void filtrarVendas(ActionEvent event) {
        tvVenda.getItems().clear();
        tvVendaItem.getItems().clear();
        tvVenda.setItems(FXCollections.observableArrayList(VendasDAO.retreaveDate(idDate.getText())));
    }

    @FXML
    void mostraItens(MouseEvent event) {
        tvVendaItem.getColumns().clear();
        Field[] v = VendasItens.class.getDeclaredFields();

        for (int i = 0; i < v.length; i++) {
            if (v[i].getName().contains("pk_Item") || v[i].getName().contains("fk_venda")) {
                continue;
            }
            TableColumn<VendasItens, String> t = new TableColumn<>(v[i].getName());
            t.setCellValueFactory(new PropertyValueFactory<>(v[i].getName()));
            if (v[i].getName().contains("fk_produto")) {
                t.setText("Produto");
            }
            if (v[i].getName().contains("quantidadeDeItens")) {
                t.setText("Quantidade de Itens");
            }
            if (v[i].getName().contains("valorUnitarioProduto")) {
                t.setText("Valor Unitário");
            }
            t.setMinWidth(139.5);
            tvVendaItem.getColumns().add(t);
        }
        tvVendaItem.setItems(FXCollections.observableArrayList(VendasItensDAO.retreaveAll(tvVenda.getSelectionModel().getSelectedItem().getPk_Vendas())));

        for (int i = 0; i < tvVendaItem.getItems().size(); i++) {
            tvVendaItem.getItems().get(i).setValor_Total(tvVendaItem.getItems().get(i).getQuantidadeDeItens()*tvVendaItem.getItems().get(i).getValorUnitarioProduto());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Field[] v = Vendas.class.getDeclaredFields();

        for (int i = 0; i < v.length; i++) {
            if (v[i].getName().contains("numero") || v[i].getName().contains("vendasItens") || v[i].getName().contains("financeiroEntradas")) {
                continue;
            }
            TableColumn<Vendas, String> t = new TableColumn<>(v[i].getName());
            t.setCellValueFactory(new PropertyValueFactory<>(v[i].getName()));
            if (v[i].getName().contains("pk_Vendas")) {
                t.setText("Nº Venda");
            }
            t.setMinWidth(139.5);
            tvVenda.getColumns().add(t);
        }
        tvVenda.setItems(FXCollections.observableArrayList(VendasDAO.retreaveAll()));
    }

}
