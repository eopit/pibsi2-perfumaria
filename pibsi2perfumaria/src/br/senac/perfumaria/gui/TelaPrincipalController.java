package br.senac.perfumaria.gui;
// <editor-fold defaultstate="collapsed" desc="imports">

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import br.senac.perfumaria.dao.PerfumeDao;
import br.senac.perfumaria.mock.MockPerfume;
import br.senac.perfumaria.mock.MockCliente;
import br.senac.perfumaria.mock.MockVenda;
import br.senac.perfumaria.model.Cliente;
import br.senac.perfumaria.model.Perfume;
import br.senac.perfumaria.model.Venda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
// </editor-fold>

public class TelaPrincipalController implements Initializable {

    public static List<Venda> listaTemporariaV = new ArrayList();
    public boolean editVenda = false;
    public boolean editProd = false;
    public boolean editMode = false;
    public Cliente clienteEdicao;
    public Perfume perfumeEdit;
    public Venda vendaAtualizar;
// <editor-fold defaultstate="collapsed" desc="JavaFx Componentes">
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
    @FXML
    private JFXTextField txt_Prod_Nome;
    @FXML
    private JFXTextField txt_Prod_Qtd;
    @FXML
    private JFXTextField txt_Prod_Marca;
    @FXML
    private JFXDatePicker data_Prod_Validade;
    @FXML
    private JFXTextField txt_Prod_Valor;
    @FXML
    private JFXTextField txt_Prod_mL;
    @FXML
    private JFXTextField txt_Prod_Filtro;
    @FXML
    private TableView<Perfume> tbv_Prod;
    @FXML
    private TableColumn<Perfume, String> col_Prod_Nome;
    @FXML
    private TableColumn<Perfume, String> col_Prod_Marca;
    @FXML
    private TableColumn<Perfume, Integer> col_Prod_Qtd;
    @FXML
    private TableColumn<Perfume, Integer> col_Prod_mLs;
    @FXML
    private TableColumn<Perfume, Double> col_Prod_Valor;
    @FXML
    private TableColumn<Perfume, LocalDate> col_Prod_Data;
    @FXML
    private JFXTextField txt_Venda_CPF;
    @FXML
    private JFXTextField txt_Venda_CN;
    @FXML
    private JFXTextField txt_Venda_Qtd;
    @FXML
    private JFXTextField txt_Venda_ML;
    @FXML
    private TableView<Venda> tbv_Venda;
    @FXML
    private TableColumn<Venda, String> col_Venda_Nome;
    @FXML
    private TableColumn<Venda, String> col_Venda_Marca;
    @FXML
    private TableColumn<Venda, String> col_Venda_ML;
    @FXML
    private TableColumn<Venda, String> col_Venda_Qtd;
    @FXML
    private TableColumn<Venda, String> col_Venda_VN;
    @FXML
    private JFXButton btn_Venda_Cancelar;
    @FXML
    private JFXTextField txt_Venda_ValorF;
    @FXML
    private JFXDatePicker data_Relatorio_Inicial;
    @FXML
    private JFXDatePicker data_Relatorio_Final;
    @FXML
    private TableView<Venda> tbv_Relatorio;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_Cliente;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_NP;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_ML;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_Qtd;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_VU;
    @FXML
    private TableColumn<Venda, String> col_Relatorio_Data;
    @FXML
    private Label valorTotalRelatorio;
    @FXML
    private Label lblError;
    @FXML
    private Label lbErrorCep;
    @FXML
    private Label lbNomeCliente;

// </editor-fold>
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

// <editor-fold defaultstate="collapsed" desc="API CEP">
        //Pega o numero de letras e joga na WEBApi de CEP (VIACEP)
        lbCEP.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                int tamanhoCEP = lbCEP.getText().length();
                ViaCEP viaCep = new ViaCEP();

                try {
                    lbCEP.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
                    lbErrorCep.setText("");
                    viaCep.buscar(lbCEP.getText());
                    lbLogradouro.setText(viaCep.getLogradouro());;
                    lbComplemento.setText(viaCep.getComplemento());
                    lbBairro.setText(viaCep.getBairro());
                    lbCidade.setText(viaCep.getLocalidade());
                    lbUF.setText(viaCep.getUf());
                    //muda a cor dos campos pq deu certo
                    lbLogradouro.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
                    lbBairro.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
                    lbCidade.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
                    lbUF.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");

                } catch (ViaCEPException ex) {
                    lbErrorCep.setText("CEP não encontrado.");
                    lbCEP.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
                    lbErrorCep.setStyle("-fx-text-fill: red;");
                }
            }
        });
// </editor-fold>        
// <editor-fold defaultstate="collapsed" desc="Mascaras">
        //Serve para deixar o campo somente numerico!

        //CAMPO CEP (NUMERICO)
        lbCEP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbCEP.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //CAMPO CPF (NUMERICO)
        lbCPF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbCPF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //CAMPO RG (NUMERICO)
        lbRG.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbRG.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //CAMPO TELEFONE
        lbTelefone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    lbTelefone.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Populando Colunas tabela">
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
// </editor-fold>

        col_Prod_Nome.setCellValueFactory(new PropertyValueFactory("nome"));
        col_Prod_Marca.setCellValueFactory(new PropertyValueFactory("marca"));
        col_Prod_Qtd.setCellValueFactory(new PropertyValueFactory("qtdProd"));
        col_Prod_Valor.setCellValueFactory(new PropertyValueFactory("preco"));
        col_Prod_Data.setCellValueFactory(new PropertyValueFactory("data"));
        col_Prod_mLs.setCellValueFactory(new PropertyValueFactory("ml"));

//
        col_Venda_Nome.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getNome()));
                return sp;
            }
        });

        col_Venda_Marca.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getMarca()));
                return sp;
            }
        });

        col_Venda_Qtd.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getQtdProd()));
                return sp;
            }
        });

        col_Venda_ML.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getMl()));
                return sp;
            }
        });
        col_Venda_VN.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getPreco()));
                return sp;
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Cliente">
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
    public void limparCampos(ActionEvent event) {
        limparCampoCliente();
    }

    @FXML
    private void salvarCliente(ActionEvent event) {
        String camposRestantes = "O(s) campo(s) ";

        if (lbNome.getText().equals("")) {
            camposRestantes += "Nome, ";
            lbNome.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbNome.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbSobrenome.getText().equals("")) {
            camposRestantes += "Sobrenome, ";
            lbSobrenome.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbSobrenome.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbDataDeNascimento.getValue() == null) {
            camposRestantes += "Data de Nascimento, ";
            lbDataDeNascimento.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbDataDeNascimento.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbCPF.getText().equals("")) {
            camposRestantes += "CPF, ";
            lbCPF.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbCPF.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbRG.getText().equals("")) {
            camposRestantes += "RG, ";
            lbRG.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbRG.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (comboGenero.getSelectionModel().isEmpty()) {
            camposRestantes += "Genero, ";
            comboGenero.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            comboGenero.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (comboEstadoCivil.getSelectionModel().isEmpty()) {
            camposRestantes += "Estado Civil, ";
            comboEstadoCivil.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            comboEstadoCivil.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbTelefone.getText().equals("")) {
            camposRestantes += "Telefone, ";
            lbTelefone.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbTelefone.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbEmail.getText().equals("")) {
            camposRestantes += "E-mail, ";
            lbEmail.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbEmail.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbCEP.getText().equals("")) {
            camposRestantes += "CEP, ";
            lbCEP.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbCEP.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbLogradouro.getText().equals("")) {
            camposRestantes += "Logradouro, ";
            lbLogradouro.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbLogradouro.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbBairro.getText().equals("")) {
            camposRestantes += "Bairro, ";
            lbBairro.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbBairro.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbCidade.getText().equals("")) {
            camposRestantes += "Cidade, ";
            lbCidade.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbCidade.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (lbUF.getText().equals("")) {
            camposRestantes += "UF, ";
            lbUF.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            lbUF.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (camposRestantes.equals("O(s) campo(s) ")) {
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
        } else { //SE TIVER CAMPO VAZIO
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar");
            alert.setContentText(camposRestantes + " falta(m) ser preenchido(s)!");
            alert.showAndWait();
        }
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    //NAO DELETAR!
    @FXML
    private void testeCep(KeyEvent event) {

    }

    public void limparCampoCliente() {
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
    // </editor-fold>   

    @FXML
    private void btn_Prod_FIltrar(ActionEvent event) {
        tbv_Prod.getItems().clear();
        try {
            tbv_Prod.setItems(FXCollections.observableArrayList(PerfumeDao.listarPerfumes(txt_Prod_Filtro.getText())));
        } catch (Exception e) {
            System.out.println("Erro ao lista");
        }
    }

    @FXML
    private void btn_Prod_Salvar(ActionEvent event) {

        String camposRestantesProd = "O(s) campo(s) ";

        if (txt_Prod_Nome.getText().equals("")) {
            camposRestantesProd += "Nome, ";
            txt_Prod_Nome.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            txt_Prod_Nome.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (txt_Prod_Marca.getText().equals("")) {
            camposRestantesProd += "Marca, ";
            txt_Prod_Marca.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            txt_Prod_Marca.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (txt_Prod_Valor.getText().equals("")) {
            camposRestantesProd += "Valor, ";
            txt_Prod_Valor.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            txt_Prod_Valor.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (txt_Prod_mL.getText().equals("")) {
            camposRestantesProd += "mLs, ";
            txt_Prod_mL.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            txt_Prod_mL.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (txt_Prod_Qtd.getText().equals("")) {
            camposRestantesProd += "Quantidade, ";
            txt_Prod_Qtd.setStyle("-jfx-focus-color: red; -fx-text-fill: red;");
        } else {
            txt_Prod_Qtd.setStyle("-jfx-focus-color: 23ff00; -fx-text-fill: white;");
        }

        if (data_Prod_Validade.getValue() == null) {
            camposRestantesProd += "Data de Nascimento, ";
        }

        if (camposRestantesProd.equals("O(s) campo(s) ")) {
            if (editProd == false) {
                salvarProd();
            } else {
                alterarProd();
            }
        } else { //SE TIVER CAMPO VAZIO
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar");
            alert.setContentText(camposRestantesProd + " falta(m) ser preenchido(s)!");
            alert.showAndWait();
        }
    }

    @FXML
    private void btn_Prod_Limpar(ActionEvent event) {
        limparCamposProd();
    }

    @FXML
    private void btn_Prod_Alterar(ActionEvent event) {
        popularCamposEditar();
    }

    @FXML
    private void btn_Prod_Excluir(ActionEvent event) {
        excluirProd();
    }

    public void limparCamposProd() {
        txt_Prod_Marca.setText("");
        txt_Prod_Qtd.setText("");
        txt_Prod_Nome.setText("");
        txt_Prod_Valor.setText("");
        txt_Prod_mL.setText("");
        data_Prod_Validade.setValue(null);
    }

    public void salvarProd() {
        try {
            Perfume perfume = new Perfume();
            perfume.setNome(txt_Prod_Nome.getText());
            perfume.setMarca(txt_Prod_Marca.getText());
            perfume.setPreco(Double.valueOf(txt_Prod_Valor.getText()));
            perfume.setMl(Integer.valueOf(txt_Prod_mL.getText()));
            perfume.setData(data_Prod_Validade.getValue());
            perfume.setQtdProd(Integer.valueOf(txt_Prod_Qtd.getText()));
            //verificando se o perfume ja esta cadastrado
            Perfume perfumeVerificado = PerfumeDao.pesquisarPerfume(perfume);
            Boolean result = false;
            //caso o id dele retorne null iremos inserir o perfume
            if(perfumeVerificado.getIdProd() == null){
                 result = PerfumeDao.inserirPerfume(perfume);
            }else{
                 result = false;
            }
            if (result) {
                limparCamposProd();
                exibeSucesso("inserir");
            } else {
                exibeAlerta("inserir");
            }
        } catch (Exception e) {
            exibeAlerta("inserir");
        }
    }

    public void alterarProd() {
        try {
            perfumeEdit.setNome(txt_Prod_Nome.getText());
            perfumeEdit.setMarca(txt_Prod_Marca.getText());
            perfumeEdit.setPreco(Double.valueOf(txt_Prod_Valor.getText()));
            perfumeEdit.setMl(Integer.valueOf(txt_Prod_mL.getText()));
            perfumeEdit.setData(data_Prod_Validade.getValue());
            perfumeEdit.setQtdProd(Integer.valueOf(txt_Prod_Qtd.getText()));
            Boolean teste = PerfumeDao.atualizarPerfume(perfumeEdit);
            tbv_Prod.getItems().clear();
            perfumeEdit = null;
            editProd = false;
            limparCamposProd();
            exibeSucesso("atualizar");
        } catch (Exception e) {
            exibeAlerta("atualizar");
        }
    }

    public void excluirProd() {
        Perfume perfume = tbv_Prod.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Excluir");
        alerta.setContentText("Deseja mesmo excluir o " + perfume.getNome() + "?");
        Optional<ButtonType> result = alerta.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                MockPerfume.excluirPerfume(perfume);
                tbv_Prod.getItems().clear();
                tbv_Prod.setItems(
                        FXCollections.observableArrayList(
                                MockPerfume.listarPerfumes(txt_Prod_Filtro.getText())
                        ));
                exibeSucesso("excluir");
                editProd = false;
            } catch (Exception e) {
                exibeAlerta("excluir");
            }
        }
    }

    public void popularCamposEditar() {
        perfumeEdit = tbv_Prod.getSelectionModel().getSelectedItem();
        txt_Prod_Nome.setText(perfumeEdit.getNome().toString());
        txt_Prod_Marca.setText(perfumeEdit.getMarca().toString());
        txt_Prod_Qtd.setText(perfumeEdit.getQtdProd().toString());
        txt_Prod_mL.setText(perfumeEdit.getMl().toString());
        txt_Prod_Valor.setText(perfumeEdit.getPreco().toString());
        data_Prod_Validade.setValue(perfumeEdit.getData());
        editProd = true;
    }

    public static void exibeAlerta(String func) {
        String mensagem = "";
        if (func.equalsIgnoreCase("inserir")) {
            mensagem = "Erro ao inserir perfume";
        } else if (func.equalsIgnoreCase("excluir")) {
            mensagem = "Erro ao excluir o pefume ";
        } else if (func.equalsIgnoreCase("atualizar")) {
            mensagem = "Erro para atualizar o produto ";
        }
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void exibeSucesso(String func) {
        String mensagem = "";
        if (func.equalsIgnoreCase("inserir")) {
            mensagem = "Perfume inserido com sucesso";
        } else if (func.equalsIgnoreCase("excluir")) {
            mensagem = "Perfume excluido com sucesso";
        } else if (func.equalsIgnoreCase("atualizar")) {
            mensagem = "Perfume atualizado com sucesso ";
        }
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void btn_Venda_Add(ActionEvent event) {
        if (!editVenda) {
            try {
                addItemVenda();
                btn_Prod_FIltrar(event);
            } catch (Exception e) {
                e.printStackTrace();
                exibeAlertaVenda("Erro ao inserir esse produto");
            }

        } else {
            try {
                boolean result = atualizarProdutoVenda(vendaAtualizar.getPerfume().getIdProd());
                if (result) {
                    recarregarTabelaVenda();
                    exibeSucessoVenda("Produto atualizado com sucesso");
                    editVenda = false;
                    limparCamposVenda();
                }
            } catch (Exception e) {
                exibeAlertaVenda("Erro ao atualizar o produto\nAlteração do produto descartada");
                editVenda = false;
                limparCamposVenda();
            }
        }
    }

    @FXML
    private void btn_Venda_Atualizar(ActionEvent event) {
        if (!editVenda) {
            try {
                Venda venda = new Venda();
                venda = tbv_Venda.getSelectionModel().getSelectedItem();
                txt_Venda_CN.setText(venda.getPerfume().getNome());
                txt_Venda_ML.setText(venda.getPerfume().getMl().toString());
                txt_Venda_Qtd.setText(venda.getPerfume().getQtdProd().toString());
                vendaAtualizar = venda;
                editVenda = true;
            } catch (Exception e) {
                exibeAlertaVenda("Verifique se você selecionou algum produto ");
            }
        } else {
            exibeAlertaVenda("Você não pode atualizar um produto em quanto não finalizar a alteração do anteriro");
        }
    }

    @FXML
    private void btn_Venda_Excluir(ActionEvent event) {
        try {

            Venda venda = tbv_Venda.getSelectionModel().getSelectedItem();

            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Excluir");
            alerta.setContentText("Deseja mesmo excluir o Pefume " + venda.getPerfume().getNome()
                    + "\nde " + venda.getPerfume().getMl() + " mLs " + " ?");
            Optional<ButtonType> result = alerta.showAndWait();

            if (result.get() == ButtonType.OK) {
                excluirProdVenda(venda);
                recarregarTabelaVenda();
                exibeSucessoVenda("Produto excluido com sucesso");
            }
        } catch (Exception e) {
            exibeAlertaVenda("Erro ao apagar produto");
        }
    }

    @FXML
    private void btn_Venda_FinalizarV(ActionEvent event) {
        if (!editVenda) {
            try {
                MockVenda.FinalizarCompra(listaTemporariaV);
                listaTemporariaV.clear();
                tbv_Venda.getItems().clear();
                limparCamposVenda();
                txt_Venda_CPF.setText("");
                txt_Venda_ValorF.setText("");
                txt_Venda_CPF.setEditable(true);
                exibeSucessoVenda("Venda finalizada com sucesso!");
            } catch (Exception e) {
                listaTemporariaV.clear();
                tbv_Venda.getItems().clear();
                limparCamposVenda();
                txt_Venda_CPF.setText("");
                txt_Venda_ValorF.setText("");
                txt_Venda_CPF.setEditable(true);
                exibeAlertaVenda("Erro ao finalizar compra");
            }
        } else {
            exibeAlertaVenda("Você não pode efetuar uma venda editando um produto."
                    + "\nSalve as alterações para poder proseguir na compra");
        }
    }

    public void addItemVenda() {
        Venda vendaItem = new Venda();
        Cliente clienteV = new Cliente();
        clienteV.setCpf(txt_Venda_CPF.getText());
        //func para ver se tem algum cliente com esse CPF
        clienteV = MockVenda.verificarCpf(clienteV);
        if (clienteV != null) {
            txt_Venda_CPF.setEditable(false);
            //verificando se o produto digitado esta no estoque
            Perfume perfumeV = new Perfume();
            perfumeV = MockVenda.verificarPerfume(txt_Venda_CN.getText(), txt_Venda_ML.getText());
            if (perfumeV != null) {
                //verificando a qtd de produtos
                if (perfumeV.getQtdProd() >= Integer.valueOf(txt_Venda_Qtd.getText())) {
                    Boolean temProd = false;
                    //verificar se o produto ja esta no carrinho
                    for (Venda ltvenda : listaTemporariaV) {
                        if (ltvenda.getPerfume().getNome().equals(perfumeV.getNome())
                                || ltvenda.getPerfume().getIdProd().toString().equals(perfumeV.getIdProd())) {
                            temProd = true;
                        }
                    }
                    if (temProd == false) {
                        vendaItem.setIdVenda(MockVenda.buscarId());
                        vendaItem.setData(LocalDate.now());
                        vendaItem.setTime(LocalTime.now());
                        vendaItem.setCliente(clienteV);
                        vendaItem.setPerfume(perfumeV);
                        vendaItem.getPerfume().setQtdProd(Integer.valueOf(txt_Venda_Qtd.getText()));
                        listaTemporariaV.add(vendaItem);
                        tbv_Venda.getItems().clear();
                        tbv_Venda.setItems(FXCollections.observableArrayList(listaTemporariaV));
                        double valorFinal = 0;
                        //calculando o valor total
                        for (Venda perfumeVF : listaTemporariaV) {
                            valorFinal = valorFinal + (perfumeVF.getPerfume().getQtdProd() * perfumeVF.getPerfume().getPreco());
                        }
                        txt_Venda_ValorF.setText(String.valueOf(valorFinal));
                        txt_Venda_CN.setText("");
                        txt_Venda_ML.setText("");
                        txt_Venda_Qtd.setText("");
                    } else {
                        exibeAlertaVenda("Esse produto ja está na venda");
                    }
                } else {
                    exibeAlertaVenda("Quantidade maior do que a quantia do estoque");
                }
            } else {
                exibeAlertaVenda("Erro ao buscar pelo perfume, verifique o codigo ou o nome digitado");
            }
        } else {
            exibeAlertaVenda("Erro ao buscar cliente na base de dados, verifique o CPF digitado");
        }

    }

    public void excluirProdVenda(Venda item) {
        for (int i = 0; i < listaTemporariaV.size(); i++) {
            Venda itemVenda = listaTemporariaV.get(i);
            if (itemVenda == item) {
                listaTemporariaV.remove(i);
            }
        }
    }

    public boolean atualizarProdutoVenda(Integer id) {
        vendaAtualizar.setPerfume(MockVenda.verificarPerfume(txt_Venda_CN.getText(),
                txt_Venda_ML.getText()));
        int contador = 0;
        if (vendaAtualizar != null) {
            if (vendaAtualizar.getPerfume().getQtdProd() >= Integer.valueOf(txt_Venda_Qtd.getText())) {
                vendaAtualizar.getPerfume().setQtdProd(Integer.valueOf(txt_Venda_Qtd.getText()));
                for (int i = 0; i < listaTemporariaV.size(); i++) {
                    Venda itemAtualizar = listaTemporariaV.get(i);
                    if (itemAtualizar.getPerfume().getIdProd() == id) {
                        contador++;
                    }
                }
                //usando o contador para ver se o produto que vai ser alterado já esta 
                //na lista temporaria
                if (contador == 1) {
                    for (Venda itemVenda : listaTemporariaV) {
                        if (itemVenda.getPerfume().getIdProd() == id) {
                            itemVenda.setPerfume(vendaAtualizar.getPerfume());
                            return true;
                        }
                    }
                } else {
                    exibeAlertaVenda("Esse produto ja esta presente na lista de vendas");
                    return false;
                }
            }
        }
        exibeAlertaVenda("Não tem um perfume com esses dados");
        return false;
    }

    public void recarregarTabelaVenda() {
        tbv_Venda.getItems().clear();
        tbv_Venda.setItems(FXCollections.observableArrayList(listaTemporariaV));
        double valorTotal = 0;
        for (Venda venda : listaTemporariaV) {
            valorTotal = valorTotal + (venda.getPerfume().getPreco() * venda.getPerfume().getQtdProd());
        }
        txt_Venda_ValorF.setText(String.valueOf(valorTotal));
    }

    public void exibeP(Venda vs) {
        String teste = "Nome: " + vs.getPerfume().getNome() + "\n qtd: " + vs.getPerfume().getQtdProd() + "\n data: " + vs.getData();

        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setContentText(teste);
        alerta.showAndWait();
    }

    public void exibeAlertaVenda(String contexto) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setContentText(contexto);
        alerta.showAndWait();
    }

    public void limparCamposVenda() {
        txt_Venda_ML.setText("");
        txt_Venda_Qtd.setText("");
        txt_Venda_CN.setText("");
    }

    public void exibeSucessoVenda(String contexto) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Sucesso");
        alerta.setContentText(contexto);
        alerta.showAndWait();
    }

    @FXML
    private void btn_Relatorio_GR(ActionEvent event) {
        tbv_Relatorio.getItems().clear();
        List resultadoRelatorio = listarRelatorio();

        if (resultadoRelatorio != null) {
            tbv_Relatorio.setItems(FXCollections.observableArrayList(resultadoRelatorio));
        }

        col_Relatorio_Cliente.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getCliente().getCpf()));
                return sp;
            }
        });

        col_Relatorio_NP.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getNome()));
                return sp;
            }
        });

        col_Relatorio_ML.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getMl()));
                return sp;
            }
        });

        col_Relatorio_Qtd.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getQtdProd()));
                return sp;
            }
        });

        col_Relatorio_VU.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getPerfume().getPreco()));
                return sp;
            }
        });

        col_Relatorio_Data.setCellValueFactory(new Callback<CellDataFeatures<Venda, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Venda, String> data) {
                StringProperty sp = new SimpleStringProperty();
                sp.setValue(String.valueOf(data.getValue().getData()) + " " + data.getValue().getTime());
                return sp;
            }
        });

    }

    private List listarRelatorio() {
        List<Venda> resultadoRelatorio = new ArrayList();

        //FAZER UM PRA PROCURA DAS DATAS, AQUI ESTA LISTANDO TODOS SEM FILTRAR
        try {
            if (data_Relatorio_Inicial.getValue() == null && data_Relatorio_Final.getValue() == null) {
                resultadoRelatorio = MockVenda.listar();
            } else {
                LocalDate inicial = data_Relatorio_Inicial.getValue();
                LocalDate termino = data_Relatorio_Final.getValue();
                resultadoRelatorio = MockVenda.procurar(inicial, termino);
            }
            //Reseta o valor para 0.0;
            Double precoFinal = 0.0;
            for (Venda venda : resultadoRelatorio) {
                precoFinal += venda.getPerfume().getPreco() * venda.getPerfume().getQtdProd();
            }

            valorTotalRelatorio.setText(precoFinal.toString() + " reais");
        } catch (Exception ex) {
            ex.printStackTrace();
            resultadoRelatorio = null;
        }
        return resultadoRelatorio;
    }

    @FXML
    private void btn_Relatorio_Limpar(ActionEvent event) {
        data_Relatorio_Inicial.setValue(null);
        data_Relatorio_Final.setValue(null);
    }

    @FXML
    private void btn_Venda_Procurar_Cliente(ActionEvent event) {
        try {
            //achar cliente aqui

            if (!MockCliente.procurarPorCPF(lbNomeCliente.getText()).equals("Cliente não encontrado")) {
                MockCliente.procurarPorCPF(lbNomeCliente.getText());
            } else {
                lbNomeCliente.setText("Cliente não encontrado");
            }
        } catch (Exception ex) {

        }
    }
}
