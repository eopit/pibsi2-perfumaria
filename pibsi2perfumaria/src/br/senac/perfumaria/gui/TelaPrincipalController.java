package br.senac.perfumaria.gui;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import br.senac.perfumaria.mock.MockCliente;
import br.senac.perfumaria.model.Cliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TelaPrincipalController implements Initializable {

    public boolean editMode = false;
    public int qtdError = 0;
    public Cliente clienteEdicao;

    private JFXPasswordField lbPasswordTeste;
    @FXML
    private JFXTextField lbNome;
    @FXML
    private JFXTextField lbSobrenome;
    @FXML
    private JFXDatePicker lbDataDeNascimento;
    @FXML
    private JFXTextField lbCPF;
    @FXML
    private JFXTextField lbRG;
    @FXML
    private JFXComboBox<String> comboGenero;
    @FXML
    private JFXComboBox<String> comboEstadoCivil;
    @FXML
    private JFXTextField lbTelefone;
    @FXML
    private JFXTextField lbEmail;
    @FXML
    private JFXTextField lbCEP;
    @FXML
    private JFXTextField lbLogradouro;
    @FXML
    private JFXTextField lbComplemento;
    @FXML
    private JFXTextField lbBairro;
    @FXML
    private JFXTextField lbCidade;
    @FXML
    private JFXTextField lbUF;
    @FXML
    private JFXTextField lbBusca;
    @FXML
    private JFXButton btSalvarCliente;
    @FXML
    private TableView<Cliente> tabelaCliente;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colSobrenome;
    @FXML
    private TableColumn<Cliente, LocalDate> colDataDeNascimento;
    @FXML
    private TableColumn<Cliente, String> colCPF;
    @FXML
    private TableColumn<Cliente, String> colRG;
    @FXML
    private TableColumn<Cliente, String> colGenero;
    @FXML
    private TableColumn<Cliente, String> colEstadoCivil;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, String> colCEP;
    @FXML
    private TableColumn<Cliente, String> colLogradouro;
    @FXML
    private TableColumn<Cliente, String> colComplemento;
    @FXML
    private TableColumn<Cliente, String> colBairro;
    @FXML
    private TableColumn<Cliente, String> colCidade;
    @FXML
    private TableColumn<Cliente, String> colUF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Popula as comboBoxs da tab de Cadastro de Cliente.
        //Combobox de Genero
        comboGenero.getItems().add("Masculino");
        comboGenero.getItems().add("Feminino");
        comboGenero.getItems().add("Indefinido");

        //Combobox do Estado Civil
        comboEstadoCivil.getItems().add("Solteiro");
        comboEstadoCivil.getItems().add("Casado");
        comboEstadoCivil.getItems().add("Separado");
        comboEstadoCivil.getItems().add("Divorciado");
        comboEstadoCivil.getItems().add("Viúvo");

        //Pega o numero de letras e joga na WEBApi de CEP (VIACEP)
        lbCEP.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                int tamanhoCEP = lbCEP.getText().length();
                ViaCEP viaCep = new ViaCEP();

                try {
                    lbCEP.setStyle("-fx-text-fill: #23ff00;");
                    viaCep.buscar(lbCEP.getText());
                    lbLogradouro.setText(viaCep.getLogradouro());;
                    lbComplemento.setText(viaCep.getComplemento());
                    lbBairro.setText(viaCep.getBairro());
                    lbCidade.setText(viaCep.getLocalidade());
                    lbUF.setText(viaCep.getUf());
                } catch (ViaCEPException ex) {
                    qtdError++;
                    lbCEP.setStyle("-fx-text-fill: red;");
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Serve para deixar o campo somente numerico!
        lbCEP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbCEP.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //Serve para deixar o campo somente numerico!
        lbCPF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbCPF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //Serve para deixar o campo somente numerico!
        lbRG.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbRG.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //Serve para configurar as colunas da table do Cliente
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colSobrenome.setCellValueFactory(new PropertyValueFactory("sobrenome"));
        colDataDeNascimento.setCellValueFactory(new PropertyValueFactory("dataDeNascimento"));
        colCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colRG.setCellValueFactory(new PropertyValueFactory("rg"));
        colGenero.setCellValueFactory(new PropertyValueFactory("genero"));
        colEstadoCivil.setCellValueFactory(new PropertyValueFactory("estadoCivil"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colCEP.setCellValueFactory(new PropertyValueFactory("cep"));
        colLogradouro.setCellValueFactory(new PropertyValueFactory("logradouro"));
        colComplemento.setCellValueFactory(new PropertyValueFactory("complemento"));
        colBairro.setCellValueFactory(new PropertyValueFactory("bairro"));
        colCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colUF.setCellValueFactory(new PropertyValueFactory("uf"));

    }

    private void getPasswordTeste(ActionEvent event) {
        System.out.println(lbPasswordTeste.getText());
    }

    @FXML
    private void btEditarNaTable(ActionEvent event) {
        Cliente cliente = tabelaCliente.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            editMode = true;
            clienteEdicao = cliente;

            lbNome.setText(clienteEdicao.getNome());
            lbSobrenome.setText(clienteEdicao.getSobrenome());
            lbDataDeNascimento.setValue(clienteEdicao.getDataDenascimento());
            lbCPF.setText(clienteEdicao.getCpf());
            lbRG.setText(clienteEdicao.getRg());
            comboGenero.setValue(clienteEdicao.getGenero());
            comboEstadoCivil.setValue(clienteEdicao.getEstadoCivil());
            lbTelefone.setText(clienteEdicao.getTelefone());
            lbEmail.setText(clienteEdicao.getEmail());
            lbCEP.setText(clienteEdicao.getCep());
            lbLogradouro.setText(clienteEdicao.getLogradouro());
            lbComplemento.setText(clienteEdicao.getComplemento());
            lbBairro.setText(clienteEdicao.getBairro());
            lbCidade.setText(clienteEdicao.getCidade());
            lbUF.setText(clienteEdicao.getUf());

            lbNome.requestFocus();

            btSalvarCliente.setText("Atualizar");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro ao atualizar");
            alert.setContentText("Não há cliente selecionado, é necessário selecionar um cliente.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btExcluirNaTable(ActionEvent event) {
        Cliente cliente = tabelaCliente.getSelectionModel().getSelectedItem();

        if (cliente != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exclusão de cliente");
            alert.setContentText("Deseja mesmo excluir o cliente " + cliente.getNome());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                //exclui
                try {
                    excluirCliente(cliente);
                    btProcurarNaTable(event);
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alertError = new Alert(AlertType.ERROR);
                    alertError.setTitle("Erro ao excluir cliente");
                    alertError.setContentText("Ocorreu um erro ao excluir o cliente, tente novamente ou consulte o administrador do sistema.");
                    alertError.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro ao excluir cliente");
            alert.setContentText("Não há cliente selecionado");
            alert.showAndWait();
        }
    }

    @FXML
    private void btProcurarNaTable(ActionEvent event) {
        tabelaCliente.getItems().clear();

        List resultado = listarNaTabela();

        if (resultado != null) {
            tabelaCliente.setItems(FXCollections.observableArrayList(resultado));
        }
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        limparCampoCliente();
    }

    @FXML
    private void salvarCliente(ActionEvent event) {
        if (!editMode) {

            Cliente cliente = new Cliente();

            cliente.setNomeCliente(lbNome.getText());
            cliente.setSobrenome(lbSobrenome.getText());
            cliente.setDataDenascimento(lbDataDeNascimento.getValue());
            cliente.setCpf(lbCPF.getText());
            cliente.setRg(lbRG.getText());
            cliente.setGenero(comboGenero.getValue());
            cliente.setEstadoCivil(comboEstadoCivil.getValue());
            cliente.setTelefone(lbTelefone.getText());
            cliente.setEmail(lbEmail.getText());
            cliente.setCep(lbCEP.getText());
            cliente.setLogradouro(lbLogradouro.getText());
            cliente.setComplemento(lbComplemento.getText());
            cliente.setBairro(lbBairro.getText());
            cliente.setCidade(lbCidade.getText());
            cliente.setUf(lbUF.getText());

            //insere na classe mock
            inserirCliente(cliente);

            limparCampoCliente();
            btProcurarNaTable(event);

        } else {

            clienteEdicao.setCpf(lbCPF.getText());
            clienteEdicao.setNomeCliente(lbNome.getText());
            clienteEdicao.setSobrenome(lbSobrenome.getText());
            clienteEdicao.setDataDenascimento(lbDataDeNascimento.getValue());
            clienteEdicao.setCpf(lbCPF.getText());
            clienteEdicao.setRg(lbRG.getText());
            clienteEdicao.setGenero(comboGenero.getValue());
            clienteEdicao.setEstadoCivil(comboEstadoCivil.getValue());
            clienteEdicao.setTelefone(lbTelefone.getText());
            clienteEdicao.setEmail(lbEmail.getText());
            clienteEdicao.setCep(lbCEP.getText());
            clienteEdicao.setLogradouro(lbLogradouro.getText());
            clienteEdicao.setComplemento(lbComplemento.getText());
            clienteEdicao.setBairro(lbBairro.getText());
            clienteEdicao.setCidade(lbCidade.getText());
            clienteEdicao.setUf(lbUF.getText());

            //Manda pro mock atualizar
            atualizarCliente(clienteEdicao);

            limparCampoCliente();
        }
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void testeCep(KeyEvent event) {

    }

    private void limparCampoCliente() {
        btSalvarCliente.setText("Salvar");
        editMode = false;
        clienteEdicao = null;
        lbNome.setText("");
        lbSobrenome.setText("");
        lbDataDeNascimento.setValue(null);
        lbCPF.setText("");
        lbRG.setText("");
        comboGenero.setValue(null);
        comboEstadoCivil.setValue(null);
        lbTelefone.setText("");
        lbEmail.setText("");
        lbCEP.setText("");
        lbLogradouro.setText("");
        lbComplemento.setText("");
        lbBairro.setText("");
        lbCidade.setText("");
        lbUF.setText("");
    }

    private List listarNaTabela() {
        List resultado;

        try {
            //Se há dados para pesquisa, faz uma busca pelo valor no mock
            //Caso contrário, faz a listagem
            if (lbBusca.getText().equals("")) {
                resultado = MockCliente.listar();
            } else {
                resultado = MockCliente.procurar(lbBusca.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        }
        return resultado;
    }

    public void inserirCliente(Cliente cliente) {
        try {
            MockCliente.inserir(cliente);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso ao inserir");
            alert.setContentText("O cliente " + cliente.getNome() + " foi inserido com êxito");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro ao inserir cliente");
            alert.setContentText("Ocorreu um erro ao inserir o cliente " + cliente.getNome());
            alert.showAndWait();
        }
    }

    private void excluirCliente(Cliente cliente) throws Exception {
        MockCliente.excluir(cliente.getIdCliente());
    }

    private void atualizarCliente(Cliente clienteEdicao) {
        try {
            MockCliente.atualizar(clienteEdicao);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Cliente atualizado.");
            alert.setContentText("Os dados do cliente " + clienteEdicao.getNome() + " foram atualizados com êxito");
            alert.showAndWait();
            limparCampoCliente();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Ocorreu um erro ao atualizar o cliente");
            alert.showAndWait();
        }
    }
}
