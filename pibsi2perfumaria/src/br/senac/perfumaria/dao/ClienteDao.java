package br.senac.perfumaria.dao;

import br.senac.perfumaria.db.ConnectionUtils;
import br.senac.perfumaria.model.Cliente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    public static Connection con;
    public static PreparedStatement ps;

    public static boolean inserirCliente(Cliente cliente) throws SQLException {
        try {
            Integer lastIdInserted = 0;
            String sql = "INSERT INTO cliente (NOME,SOBRENOME,RG,CPF,DATA_NASCIMENTO,GENERO,ESTADO_CIVIL,TELEFONE,EMAIL) VALUES (?,?,?,?,?,?,?,?,?);";
            conectarBD(sql);
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setDate(5, Date.valueOf(cliente.getDataDenascimento()));
            ps.setString(6, cliente.getGenero());
            ps.setString(7, cliente.getEstadoCivil());
            ps.setString(8, cliente.getTelefone());
            ps.setString(9, cliente.getEmail());
            ps.execute();
            String sqlSelectId = "SELECT LAST_INSERT_ID();";
            ResultSet result = ps.getGeneratedKeys();

            while (result.next()) {
                lastIdInserted = result.getInt(1);
            }
            System.out.println(lastIdInserted);
            fechaConexaoBD();
            inserirEnderecoCliente(cliente, lastIdInserted);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
        }
    }

    public static boolean inserirEnderecoCliente(Cliente cliente, Integer ID) throws SQLException {
        try {
            String sql = "INSERT INTO endereco_cli (ID_CLI,CEP,LOGRADOURO,COMPLEMENTO,BAIRRO,CIDADE,UF) VALUES (?,?,?,?,?,?,?)";
            conectarBD(sql);
            ps.setInt(1, ID);
            ps.setString(2, cliente.getCep());
            ps.setString(3, cliente.getLogradouro());
            ps.setString(4, cliente.getComplemento());
            ps.setString(5, cliente.getBairro());
            ps.setString(6, cliente.getCidade());
            ps.setString(7, cliente.getUf());
            ps.execute();
            fechaConexaoBD();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
        }
    }

    public static boolean pesquisarCliente(Cliente cliente) throws SQLException {
        Cliente clienteR = new Cliente();
        try {
            String sql = "SELECT * FROM cliente WHERE CPF = ? OR RG = ?";
            conectarBD(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getRg());
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                clienteR.setIdCliente(result.getInt("ID"));
                return false;
            }
            result.close();
            fechaConexaoBD();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("caiu no false");
            fechaConexaoBD();
            return true;
        }
        return true;
    }

    public static List<Cliente> listarClientes(String filtro) throws SQLException {
        List<Cliente> listaDeClientes = new ArrayList<Cliente>();
        try {
            String sql = "SELECT * from cliente inner join endereco_cli on cliente.ID = endereco_cli.ID_CLI WHERE NOME LIKE ? OR CPF LIKE ?";
            conectarBD(sql);
            ps.setString(1, '%' + filtro + '%');
            ps.setString(2, '%' + filtro + '%');
            //vamos tentar converter o o filtro em int 

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Cliente clienteR = new Cliente();
                clienteR.setIdCliente(result.getInt("ID"));
                clienteR.setNomeCliente(result.getString("NOME"));
                clienteR.setSobrenome(result.getString("SOBRENOME"));
                clienteR.setDataDenascimento(result.getDate("DATA_NASCIMENTO").toLocalDate());
                clienteR.setCpf(result.getString("CPF"));
                clienteR.setRg(result.getString("RG"));
                clienteR.setGenero(result.getString("GENERO"));
                clienteR.setEstadoCivil(result.getString("ESTADO_CIVIL"));
                clienteR.setTelefone(result.getString("TELEFONE"));
                clienteR.setEmail(result.getString("EMAIL"));
                //enderecos
                clienteR.setCep(result.getString("CEP"));
                clienteR.setLogradouro(result.getString("LOGRADOURO"));
                clienteR.setComplemento(result.getString("COMPLEMENTO"));
                clienteR.setBairro(result.getString("BAIRRO"));
                clienteR.setCidade(result.getString("CIDADE"));
                clienteR.setUf(result.getString("UF"));
                listaDeClientes.add(clienteR);
            }
            result.close();
            fechaConexaoBD();
            return listaDeClientes;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return listaDeClientes;
        }
    }

    public static boolean atualizarCliente(Cliente cliente) throws SQLException {
        try {
            String sql = "UPDATE cliente SET NOME = ?, SOBRENOME = ?, RG = ?, CPF = ?, DATA_NASCIMENTO = ?, GENERO = ?, ESTADO_CIVIL = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?";
            conectarBD(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSobrenome());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getCpf());
            ps.setDate(5, Date.valueOf(cliente.getDataDenascimento()));
            ps.setString(6, cliente.getGenero());
            ps.setString(7, cliente.getEstadoCivil());
            ps.setString(8, cliente.getTelefone());
            ps.setString(9, cliente.getEmail());
            ps.setInt(10, cliente.getIdCliente());
            ps.execute();
            fechaConexaoBD();
            atualizarEnderecoCliente(cliente, cliente.getIdCliente());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
        }
    }

    public static boolean atualizarEnderecoCliente(Cliente cliente, Integer ID) throws SQLException {
        try {
            String sql = "UPDATE endereco_cli SET CEP = ?, LOGRADOURO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, UF = ? WHERE ID_CLI = ?";
            conectarBD(sql);
            ps.setString(1, cliente.getCep());
            ps.setString(2, cliente.getLogradouro());
            ps.setString(3, cliente.getComplemento());
            ps.setString(4, cliente.getBairro());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getUf());
            ps.setInt(7, ID);
            ps.execute();
            fechaConexaoBD();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
        }
    }

    public static void conectarBD(String sql) throws SQLException {
        try {
            con = null;
            ps = null;
            con = ConnectionUtils.getConnection();
            ps = con.prepareStatement(sql);
            System.out.println("Conectado com o banco");
        } catch (Exception e) {
            System.out.println("Erro ao conectar no banco");
            con.close();
            ps.close();
        }
    }

    public static void fechaConexaoBD() throws SQLException {
        try {
            con.close();
            ps.close();
            System.out.println("conexao do banco fechada");
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexao do banco");
        }
    }

    public static void desativarCliente(Cliente cliente) throws SQLException {
        try {
            String sql = "UPDATE cliente SET ATIVO = 0 WHERE ID = ?";
            conectarBD(sql);
            ps.setInt(1, cliente.getIdCliente());
            ps.execute();
            fechaConexaoBD();
        } catch (Exception e) {
            fechaConexaoBD();
        }
    }
}
