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
import modelo.ClienteDAO;
import modelo.ClienteEnderecoDAO;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class TelaCadastroClienteController implements Initializable {

//    @FXML
//    private ComboBox<Cargo> cargo;
    private int selected = -1;
    private int selectedEndereco = -1;

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
    private TableView<Enderecos> tvClienteEndereco;

    @FXML
    private ListView<Cliente> listvCliente;

    @FXML
    public void voltar(ActionEvent event) {
        Empresa3.trocaTela(Empresa3.TELA_PRINCIPAL);
        limparCliente();
        limparClienteEndereco();
        tvClienteEndereco.getItems().clear();
        selected = -1;
        selectedEndereco = -1;
        listvCliente.getSelectionModel().clearSelection();
    }

    @FXML
    void excluirCliente(ActionEvent event) {
        if (!listvCliente.getSelectionModel().isEmpty()) {
            for (int i = 0; i < listvCliente.getSelectionModel().getSelectedItem().getEnderecos().size(); i++) {
                ClienteEnderecoDAO.delete(listvCliente.getSelectionModel().getSelectedItem().getEnderecos().get(i));
            }
            ClienteDAO.delete(listvCliente.getSelectionModel().getSelectedItem());
            limparCliente();
            limparClienteEndereco();
            tvClienteEndereco.getItems().clear();
            listvCliente.getItems().remove(listvCliente.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void excluirEndereco(ActionEvent event) {
        if (!tvClienteEndereco.getSelectionModel().isEmpty()) {
            tvClienteEndereco.getItems().remove(tvClienteEndereco.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    void protegeSelection(MouseEvent event) {
        if (event.getClickCount() == 1 && tvClienteEndereco.getItems().isEmpty()) {
            listvCliente.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void exibirCliente(MouseEvent event) {
        if (event.getClickCount() == 2) {
            idNome.setText(listvCliente.getSelectionModel().getSelectedItem().getNome());
            idCpf.setText(listvCliente.getSelectionModel().getSelectedItem().getCpf().getCpf());
            tvClienteEndereco.setItems(FXCollections.observableArrayList(ClienteEnderecoDAO.retreaveAll(listvCliente.getSelectionModel().getSelectedItem().getPk())));
            selected = listvCliente.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    void alterarEndereco(MouseEvent event) {
        if (event.getClickCount() == 2) {
            idLogradouro.setText(tvClienteEndereco.getSelectionModel().getSelectedItem().getLogradouro());
            idBairro.setText(tvClienteEndereco.getSelectionModel().getSelectedItem().getBairro());
            idCep.setText(tvClienteEndereco.getSelectionModel().getSelectedItem().getCep());
            idPais.setValue(tvClienteEndereco.getSelectionModel().getSelectedItem().getPais());
            idEstado.setValue(tvClienteEndereco.getSelectionModel().getSelectedItem().getEstado());
            idCidade.setValue(tvClienteEndereco.getSelectionModel().getSelectedItem().getCidade());
            selectedEndereco = tvClienteEndereco.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    void salvarAll(ActionEvent event) {

        if (selected == -1 && validaFormCliente(event) && validaTabEndereco(event)) {
            Cliente aux = new Cliente(idNome.getText(),
                    new Cpf(idCpf.getText()));

            tvClienteEndereco.getItems().forEach((aux2) -> {
                aux.getEnderecos().add(aux2);
            });

            ClienteDAO.create(aux);
            listvCliente.setItems(FXCollections.observableArrayList(ClienteDAO.retreaveAll()));
        } else if (validaFormCliente(event) && validaTabEndereco(event)) {
            Cliente auxiliar = listvCliente.getItems().get(selected);
            auxiliar.setNome(idNome.getText());
            auxiliar.setCpf(new Cpf(idCpf.getText()));

            if (auxiliar.getEnderecos().size() == tvClienteEndereco.getItems().size()) {
                for (int i = 0; i < tvClienteEndereco.getItems().size(); i++) {
                    tvClienteEndereco.getItems().get(i).setFk(auxiliar.getPk());
                    tvClienteEndereco.getItems().get(i).setPk_Enderecos(auxiliar.getEnderecos().get(i).getPk_Enderecos());
                }
                for (int i = 0; i < tvClienteEndereco.getItems().size(); i++) {
                    ClienteEnderecoDAO.update(tvClienteEndereco.getItems().get(i));
                }
            } else {
                auxiliar.getEnderecos().forEach((aux3) -> {
                    ClienteEnderecoDAO.delete(aux3);
                });
                auxiliar.getEnderecos().clear();
                for (int i = 0; i < tvClienteEndereco.getItems().size(); i++) {
                    tvClienteEndereco.getItems().get(i).setFk(auxiliar.getPk());
                    tvClienteEndereco.getItems().get(i).setPk_Enderecos(0);
                    ClienteEnderecoDAO.create(tvClienteEndereco.getItems().get(i));
                }

            }
            ClienteDAO.update(auxiliar);
            listvCliente.setItems(FXCollections.observableArrayList(ClienteDAO.retreaveAll()));
            selected = -1;
        }
        limparCliente();
        limparClienteEndereco();
        tvClienteEndereco.getItems().clear();
    }

    @FXML
    void limparCliente() {
        idNome.setText("");
        idCpf.setText("");
    }

    @FXML
    public boolean validaFormCliente(ActionEvent event) {
        return !idNome.getText().isEmpty() && !idCpf.getText().isEmpty();
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
            tvClienteEndereco.getItems().add(aux);
        } else if (validaFormEndereco(event)) {
            //aqui esta alterando
            tvClienteEndereco.getItems().get(selectedEndereco).setLogradouro(idLogradouro.getText());
            tvClienteEndereco.getItems().get(selectedEndereco).setBairro(idBairro.getText());
            tvClienteEndereco.getItems().get(selectedEndereco).setCep(idCep.getText());
            tvClienteEndereco.getItems().get(selectedEndereco).setPais(idPais.getValue());
            tvClienteEndereco.getItems().get(selectedEndereco).setEstado(idEstado.getValue());
            tvClienteEndereco.getItems().get(selectedEndereco).setCidade(idCidade.getValue());
            tvClienteEndereco.refresh();
            selectedEndereco = -1;
        }
        limparClienteEndereco();
    }

    @FXML
    public void limparClienteEndereco() {
        idLogradouro.setText("");
        idBairro.setText("");
        idCep.setText("");
        idPais.getSelectionModel().clearSelection();
        idEstado.getSelectionModel().clearSelection();
        idCidade.getSelectionModel().clearSelection();
    }

    @FXML
    public boolean validaFormEndereco(ActionEvent event) {
        return !idLogradouro.getText().isEmpty() && !idBairro.getText().isEmpty() && !idCep.getText().isEmpty()
                && !idPais.getValue().isEmpty() && !idEstado.getValue().isEmpty() && !idCidade.getValue().isEmpty();
    }

    @FXML
    public boolean validaTabEndereco(ActionEvent event) {
        return !tvClienteEndereco.getItems().isEmpty();
    }

    @FXML
    void novo(ActionEvent event) {
        if (selected == -1 && validaFormCliente(event) && validaTabEndereco(event)) {
            Cliente aux = new Cliente(idNome.getText(),
                    new Cpf(idCpf.getText()));

            tvClienteEndereco.getItems().forEach((aux2) -> {
                aux.getEnderecos().add(aux2);
            });

            ClienteDAO.create(aux);
            listvCliente.setItems(FXCollections.observableArrayList(ClienteDAO.retreaveAll()));
        }
        limparCliente();
        limparClienteEndereco();
        tvClienteEndereco.getItems().clear();
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
        if (idPais.getSelectionModel().getSelectedIndex() == 0) {
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
        } else {
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
        listvCliente.setItems(FXCollections.observableArrayList(ClienteDAO.retreaveAll()));
        Field[] f = Enderecos.class.getDeclaredFields();

        for (int i = 0; i < f.length; i++) {
            if (f[i].getName().contains("pk_Enderecos") || f[i].getName().contains("fk")) {
                continue;
            }
            TableColumn<Enderecos, String> t = new TableColumn<>(f[i].getName());
            t.setCellValueFactory(new PropertyValueFactory<>(f[i].getName()));
            if (f[i].getName().contains("logradouro")) {
                t.setMinWidth(150);
            }

            tvClienteEndereco.getColumns().add(t);
        }
    }

}
