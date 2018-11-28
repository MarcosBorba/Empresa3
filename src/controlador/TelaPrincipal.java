/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import empresa3.Empresa3;

/**
 *
 * @author marcos
 */
public class TelaPrincipal implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void TelaCadastroCargo(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_CARGO);
    }
    
    @FXML
    void TelaCadastroCliente(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_CLIENTE);
    }
    
    @FXML
    void TelaSobre(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_SOBRE);
    }

    @FXML
    void TelaCadastroCompra(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_COMPRA);
    }

    @FXML
    void TelaCadastroFuncionario(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_FUNCIONARIO);
    }
    
    @FXML
    void TelaCadastroVenda(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_VENDA);
    }
    
}
