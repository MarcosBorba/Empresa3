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
import modelo.CargosDAO;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCadastroCargosController implements Initializable {

    private int selected = -1;

    @FXML
    private TextField idnome;

    @FXML
    private TextField iddescricao;

    @FXML
    private TableView<Cargo> tvCargo;

    @FXML
    public void voltar(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_PRINCIPAL);
    }

    @FXML
    public void salvar(ActionEvent event) {
        if (selected == -1 && validaForm(event)) {
            Cargo aux = new Cargo(idnome.getText(),
                    iddescricao.getText());
            CargosDAO.create(aux);
            tvCargo.getItems().add(aux);
        } else if (validaForm(event)) {
            tvCargo.getItems().get(selected).setNome(idnome.getText());
            tvCargo.getItems().get(selected).setDescricao(iddescricao.getText());
            tvCargo.refresh();
            CargosDAO.update(tvCargo.getItems().get(selected));
            selected = -1;
        }
        /*CargosDAO.create(new Cargo(idnome.getText(),
            iddescricao.getText()));*/
        limpar();
    }

    @FXML
    public void limpar() {
        iddescricao.setText("");
        idnome.setText("");
    }

    @FXML
    public void excluir(ActionEvent event) {
        CargosDAO.delete(tvCargo.getSelectionModel().getSelectedItem());
        tvCargo.getItems().remove(tvCargo.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public boolean validaForm(ActionEvent event) {
        return !idnome.getText().isEmpty() && !iddescricao.getText().isEmpty();
    }

    @FXML
    public void alterar(MouseEvent event) {
        if (event.getClickCount() == 2) {
            idnome.setText(tvCargo.getSelectionModel().getSelectedItem().getNome());
            iddescricao.setText(tvCargo.getSelectionModel().getSelectedItem().getDescricao());
            selected = tvCargo.getSelectionModel().getSelectedIndex();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //esse passo ensina como as colunas devem receber os atributos do objeto cargo
        //A propriedade nome deve coincidir com o nome do atributo da classe cargo
//        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//        
//        colStatus.setVisible(false);
        Field[] f = Cargo.class.getDeclaredFields();

        for (int i = 0; i < f.length; i++) {
            if (f[i].getName().contains("pk_Cargo")) {
                continue;
            }
            TableColumn<Cargo, String> t = new TableColumn<>(f[i].getName());
            t.setCellValueFactory(new PropertyValueFactory<>(f[i].getName()));
            tvCargo.getColumns().add(t);
        }

        tvCargo.setItems(FXCollections.observableArrayList(CargosDAO.retreaveAll()));
    }

}
