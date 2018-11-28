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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.CargosDAO;
import modelo.FuncionarioDAO;
import modelo.FuncionarioEnderecoDAO;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCadastroFuncionarioController implements Initializable {

//    @FXML
//    private ComboBox<Cargo> cargo;
    private int selected = -1;
    private int selectedEndereco = -1;

    @FXML
    private ComboBox<Cargo> idCargo;

    @FXML
    private Tab tabGeral;

    @FXML
    private Tab tabEndereco;

    @FXML
    private TextField idNome;

    @FXML
    private TextField idCpf;

    @FXML
    private ComboBox<String> idPais;

    @FXML
    private TextField idLogradouro;

    @FXML
    private TextField idBairro;

    @FXML
    private ComboBox<String> idEstado;

    @FXML
    private ComboBox<String> idCidade;

    @FXML
    private TextField idCep;

    @FXML
    private TableView<Enderecos> tvFuncionarioEndereco;

    @FXML
    private ListView<Funcionario> listvFuncionario;

    @FXML
    public void voltar(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_PRINCIPAL);
        limparFuncionario();
        limparFuncionarioEndereco();
        tvFuncionarioEndereco.getItems().clear();
        selected = -1;
        selectedEndereco = -1;
        listvFuncionario.getSelectionModel().clearSelection();
    }

    @FXML
    void excluirFuncionario(ActionEvent event) {
        if (!listvFuncionario.getSelectionModel().isEmpty()) {
            for (int i = 0; i < listvFuncionario.getSelectionModel().getSelectedItem().getEnderecos().size(); i++) {
                FuncionarioEnderecoDAO.delete(listvFuncionario.getSelectionModel().getSelectedItem().getEnderecos().get(i));
            }
            FuncionarioDAO.delete(listvFuncionario.getSelectionModel().getSelectedItem());
            limparFuncionario();
            limparFuncionarioEndereco();
            tvFuncionarioEndereco.getItems().clear();
            listvFuncionario.getItems().remove(listvFuncionario.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void excluirEndereco(ActionEvent event) {
        if (!tvFuncionarioEndereco.getSelectionModel().isEmpty()) {
            tvFuncionarioEndereco.getItems().remove(tvFuncionarioEndereco.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void protegeSelection(MouseEvent event) {
        if (event.getClickCount() == 1 && tvFuncionarioEndereco.getItems().isEmpty()) {
            listvFuncionario.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void exibirFuncionario(MouseEvent event) {
        if (event.getClickCount() == 2) {
            idNome.setText(listvFuncionario.getSelectionModel().getSelectedItem().getNome());
            idCpf.setText(listvFuncionario.getSelectionModel().getSelectedItem().getCpf().getCpf());
            idCargo.setValue(listvFuncionario.getSelectionModel().getSelectedItem().getFk_Cargo());
            tvFuncionarioEndereco.setItems(FXCollections.observableArrayList(FuncionarioEnderecoDAO.retreaveAll(listvFuncionario.getSelectionModel().getSelectedItem().getPk())));
            selected = listvFuncionario.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    void alterarEndereco(MouseEvent event) {
        if (event.getClickCount() == 2) {
            idLogradouro.setText(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getLogradouro());
            idBairro.setText(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getBairro());
            idCep.setText(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getCep());
            idPais.setValue(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getPais());
            idEstado.setValue(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getEstado());
            idCidade.setValue(tvFuncionarioEndereco.getSelectionModel().getSelectedItem().getCidade());
            selectedEndereco = tvFuncionarioEndereco.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    void salvarAll(ActionEvent event) {

        if (selected == -1 && validaFormFuncionario(event) && validaTabEndereco(event)) {
            Funcionario aux = new Funcionario(idNome.getText(),
                    new Cpf(idCpf.getText()),
                    idCargo.getSelectionModel().getSelectedItem());

            tvFuncionarioEndereco.getItems().forEach((aux2) -> {
                aux.getEnderecos().add(aux2);
            });

            FuncionarioDAO.create(aux);
            listvFuncionario.setItems(FXCollections.observableArrayList(FuncionarioDAO.retreaveAll()));
        } else if (validaFormFuncionario(event) && validaTabEndereco(event)) {
            Funcionario auxiliar = listvFuncionario.getItems().get(selected);
            auxiliar.setNome(idNome.getText());
            auxiliar.setCpf(new Cpf(idCpf.getText()));
            auxiliar.setFk_Cargo(idCargo.getSelectionModel().getSelectedItem());

            if (auxiliar.getEnderecos().size() == tvFuncionarioEndereco.getItems().size()) {
                for (int i = 0; i < tvFuncionarioEndereco.getItems().size(); i++) {
                    tvFuncionarioEndereco.getItems().get(i).setFk(auxiliar.getPk());
                    tvFuncionarioEndereco.getItems().get(i).setPk_Enderecos(auxiliar.getEnderecos().get(i).getPk_Enderecos());
                }
                for (int i = 0; i < tvFuncionarioEndereco.getItems().size(); i++) {
                    FuncionarioEnderecoDAO.update(tvFuncionarioEndereco.getItems().get(i));
                }
            } else {
                for (int i = 0; i < auxiliar.getEnderecos().size(); i++) {
                    FuncionarioEnderecoDAO.delete(auxiliar.getEnderecos().get(i));
                }

                auxiliar.getEnderecos().clear();
                for (int i = 0; i < tvFuncionarioEndereco.getItems().size(); i++) {
                    tvFuncionarioEndereco.getItems().get(i).setFk(auxiliar.getPk());
                    tvFuncionarioEndereco.getItems().get(i).setPk_Enderecos(0);
                    FuncionarioEnderecoDAO.create(tvFuncionarioEndereco.getItems().get(i));
                }

            }
            FuncionarioDAO.update(auxiliar);
            listvFuncionario.setItems(FXCollections.observableArrayList(FuncionarioDAO.retreaveAll()));
            selected = -1;
        }
        limparFuncionario();
        limparFuncionarioEndereco();
        tvFuncionarioEndereco.getItems().clear();
    }

    @FXML
    void limparFuncionario() {
        idNome.setText("");
        idCpf.setText("");
        idCargo.setValue(null);
    }

    @FXML
    public boolean validaFormFuncionario(ActionEvent event) {
        return !idNome.getText().isEmpty() && !idCpf.getText().isEmpty() && idCargo.valueProperty() != null;
    }

    @FXML
    void salvarEndereco(ActionEvent event) {
        //aqui esta inserindo
        if (selectedEndereco == -1 && validaFormEndereco(event)) {
            Enderecos aux = new Enderecos(idLogradouro.getText(),
                    idBairro.getText(),
                    idCidade.getValue(),
                    idEstado.getValue(),
                    idPais.getValue(),
                    idCep.getText());
            tvFuncionarioEndereco.getItems().add(aux);
        } else if (validaFormEndereco(event)) {
            //aqui esta alterando
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setLogradouro(idLogradouro.getText());
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setBairro(idBairro.getText());
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setCep(idCep.getText());
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setPais(idPais.getValue());
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setEstado(idEstado.getValue());
            tvFuncionarioEndereco.getItems().get(selectedEndereco).setCidade(idCidade.getValue());
            tvFuncionarioEndereco.refresh();
            selectedEndereco = -1;
        }
        limparFuncionarioEndereco();
    }

    @FXML
    public void limparFuncionarioEndereco() {
        idLogradouro.setText("");
        idBairro.setText("");
        idCep.setText("");
        idPais.setValue(null);
        idEstado.setValue(null);
        idCidade.setValue(null);
    }

    @FXML
    public boolean validaFormEndereco(ActionEvent event) {
        return !idLogradouro.getText().isEmpty() && !idBairro.getText().isEmpty() && !idCep.getText().isEmpty()
                && !idPais.getValue().isEmpty() && !idEstado.getValue().isEmpty() && !idCidade.getValue().isEmpty();
    }

    @FXML
    public boolean validaTabEndereco(ActionEvent event) {
        return !tvFuncionarioEndereco.getItems().isEmpty();
    }

    @FXML
    void novo(ActionEvent event) {
        if (selected == -1 && validaFormFuncionario(event) && validaTabEndereco(event)) {
            Funcionario aux = new Funcionario(idNome.getText(),
                    new Cpf(idCpf.getText()),
                    idCargo.getSelectionModel().getSelectedItem());
            for (int i = 0; i < tvFuncionarioEndereco.getItems().size(); i++) {
                aux.getEnderecos().add(tvFuncionarioEndereco.getItems().get(i));
            }

            FuncionarioDAO.create(aux);
            listvFuncionario.setItems(FXCollections.observableArrayList(FuncionarioDAO.retreaveAll()));
        }
        limparFuncionario();
        limparFuncionarioEndereco();
        tvFuncionarioEndereco.getItems().clear();
    }

    @FXML
    void ActionEstado(ActionEvent event) {
        switch (idPais.getSelectionModel().getSelectedIndex()) {
            case 0:
                idEstado.getItems().clear();
                idEstado.getItems().add("GO");
                idEstado.getItems().add("SP");
                idEstado.getItems().add("MG");
                idEstado.getItems().add("RJ");
                break;
            case 1:
                idEstado.getItems().clear();
                idEstado.getItems().add("LI");
                idEstado.getItems().add("BRA");
                break;
        }

    }

    @FXML
    void ActionCidade(ActionEvent event) {
         if(idPais.getSelectionModel().getSelectedIndex()==0) {
                switch (idEstado.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("buriti");
                        idCidade.getItems().add("morrinhos");
                        idCidade.getItems().add("goiania");
                        idCidade.getItems().add("caldas");
                        break;
                    case 1:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("sao paulo");
                        idCidade.getItems().add("barueri");
                        idCidade.getItems().add("santos");
                        idCidade.getItems().add("osasco");
                        break;
                    case 2:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("Uberlandia");
                        idCidade.getItems().add("Arapora");
                        idCidade.getItems().add("Belo Horizonte");
                        idCidade.getItems().add("Tupaciguara");
                        break;
                    case 3:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("Rio de Janeiro");
                        idCidade.getItems().add("Niteroi");
                        idCidade.getItems().add("Angra dos Reis");
                        idCidade.getItems().add("Cabo Frio");
                        break;
                }
         }else{
                switch (idEstado.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("Cidade nova");
                        idCidade.getItems().add("Cidade Velha");
                        idCidade.getItems().add("Nova veneza");
                        idCidade.getItems().add("Cantareno");
                        break;
                    case 1:
                        idCidade.getItems().clear();
                        idCidade.getItems().add("Rios moles");
                        idCidade.getItems().add("Sovenia");
                        idCidade.getItems().add("Rancho Bonito");
                        idCidade.getItems().add("Boa Ventura");
                        break;
                }
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idPais.getItems().add("Brasil");
        idPais.getItems().add("Portugal");
//        idPais.getItems().add("USA");
//        idPais.getItems().add("Portugal");
//        idPais.getItems().add("Canada");
//        cargo.setItems(FXCollections.observableList(CargosDAO.retreaveAll()));
        listvFuncionario.setItems(FXCollections.observableArrayList(FuncionarioDAO.retreaveAll()));
        idCargo.setItems(FXCollections.observableArrayList(CargosDAO.retreaveAll()));
        Field[] f = Enderecos.class.getDeclaredFields();
          
          for(int i=0;i<f.length;i++){
              if(f[i].getName().contains("pk_Enderecos")||f[i].getName().contains("fk")){
                  continue;
              }
          TableColumn<Enderecos, String> t = new TableColumn<>(f[i].getName());
          t.setCellValueFactory(new PropertyValueFactory<>(f[i].getName()));
          if (f[i].getName().contains("logradouro")) {
                t.setMinWidth(150);
            }
          tvFuncionarioEndereco.getColumns().add(t);
          }
    }

}
