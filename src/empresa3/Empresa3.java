/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marcos
 */
public class Empresa3 extends Application {
    private static Stage primaryStage;
    private static Scene telaPrincipal;
    private static Scene telaSobre;
    private static Scene telaCadastroCliente;
    private static Scene telaCadastroCargo;
    private static Scene telaCadastroCompra;
    private static Scene telaCadastroFuncionario;
    private static Scene telaConsultaVenda;
    
    public static final int TELA_PRINCIPAL = 0;
    public static final int TELA_SOBRE = 1;
    public static final int TELA_CARGO = 2;
    public static final int TELA_CLIENTE = 3;
    public static final int TELA_COMPRA = 4;
    public static final int TELA_FUNCIONARIO = 5;
    public static final int TELA_VENDA = 6;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        
        telaPrincipal = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaPrincipal.fxml")));
        telaSobre = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaSobre.fxml")));
        telaCadastroCargo = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaCadastroCargo.fxml")));
        telaCadastroCliente = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaCadastroCliente.fxml")));
        telaCadastroCompra = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaCadastroCompra.fxml")));
        telaCadastroFuncionario = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaCadastroFuncionario.fxml")));
        telaConsultaVenda = new Scene(FXMLLoader.load(getClass().getResource("/visao/TelaCadastroVenda.fxml")));
        
        trocaTela(TELA_PRINCIPAL);
        
        stage.show();
    }
    
    public static void trocaTela(int tela){
        switch(tela){
            case TELA_PRINCIPAL:
                primaryStage.setTitle("Tela Principal");
                primaryStage.setScene(telaPrincipal);
                break;
            case TELA_SOBRE:
                primaryStage.setTitle("Tela Sobre");
                primaryStage.setScene(telaSobre);
                break;
            case TELA_CARGO:
                primaryStage.setTitle("Tela Cargo");
                primaryStage.setScene(telaCadastroCargo);
                break;
            case TELA_CLIENTE:
                primaryStage.setTitle("Tela Cliente");
                primaryStage.setScene(telaCadastroCliente);
                break;
            case TELA_COMPRA:
                 primaryStage.setTitle("Tela Compra");
                 primaryStage.setScene(telaCadastroCompra);
                 break;
            case TELA_FUNCIONARIO:
                primaryStage.setTitle("Tela Funcionario");
                primaryStage.setScene(telaCadastroFuncionario);
                break;
            case TELA_VENDA:
                primaryStage.setTitle("Tela Venda");
                primaryStage.setScene(telaConsultaVenda);
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
