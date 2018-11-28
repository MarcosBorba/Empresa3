/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import empresa3.Empresa3;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCadastroCompraController implements Initializable {
    
    @FXML
    public void voltar(ActionEvent event){
       Empresa3.trocaTela(Empresa3.TELA_PRINCIPAL);
    }
    
    @FXML
       public void limpar(){
           //iddescricao.setText("");
           //idnome.setText("");
       }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
