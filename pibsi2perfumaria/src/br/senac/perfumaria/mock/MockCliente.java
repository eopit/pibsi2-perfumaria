package br.senac.perfumaria.mock;

import br.senac.perfumaria.gui.TelaPrincipalController;
import br.senac.perfumaria.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class MockCliente {

    private static int numeroDeClientes = 0;

    private static List<Cliente> listaCliente = new ArrayList<Cliente>();

    public static void inserir(Cliente cliente) throws Exception {
        cliente.setIdCliente(numeroDeClientes++);
        listaCliente.add(cliente);
    }

    public static void atualizar(Cliente clienteProcura) throws Exception {
        if (clienteProcura != null && clienteProcura.getIdCliente() != null && !listaCliente.isEmpty()) {
            for (Cliente clienteLi : listaCliente) {

                if (clienteLi != null && clienteLi.getIdCliente() == clienteProcura.getIdCliente()) {
                    clienteLi.setNomeCliente(clienteProcura.getNome());
                    clienteLi.setSobrenome(clienteProcura.getSobrenome());
                    clienteLi.setDataDenascimento(clienteProcura.getDataDenascimento());
                    clienteLi.setCpf(clienteProcura.getCpf());
                    clienteLi.setRg(clienteProcura.getRg());
                    clienteLi.setGenero(clienteProcura.getGenero());
                    clienteLi.setEstadoCivil(clienteProcura.getEstadoCivil());
                    clienteLi.setTelefone(clienteProcura.getTelefone());
                    clienteLi.setEmail(clienteProcura.getEmail());
                    clienteLi.setCep(clienteProcura.getCep());
                    clienteLi.setLogradouro(clienteProcura.getLogradouro());
                    clienteLi.setComplemento(clienteProcura.getComplemento());
                    clienteLi.setBairro(clienteProcura.getBairro());
                    clienteLi.setCidade(clienteProcura.getCidade());
                    clienteLi.setUf(clienteProcura.getUf());
                    break;
                }
            }
        }
    }

    public static void excluir(Integer id) throws Exception {
        if (id != null && !listaCliente.isEmpty()) {
            for (int i = 0; i < listaCliente.size(); i++) {
                Cliente clienteLi = listaCliente.get(i);
                if (clienteLi != null && clienteLi.getIdCliente()== id) {
                    listaCliente.remove(i);
                    break;
                }
            }
        }
    }
    
    
    //Lista todos os clientes
    public static List<Cliente> listar()
            throws Exception {       
        //Retorna a lista de clientes
        return listaCliente;
    }

    //Procura um cliente no mock, de acordo com o nome
    //ou com o sobrenome, passado como parâmetro
    public static List<Cliente> procurar(String valor)
            throws Exception {
        List<Cliente> listaResultado = new ArrayList<Cliente>();
        
        if (valor != null && !listaCliente.isEmpty()) {
            for (Cliente clienteLi : listaCliente) {
                if (clienteLi != null && clienteLi.getNome() != null &&
                    clienteLi.getSobrenome() != null) {
                    if (clienteLi.getNome().contains(valor)
                        || clienteLi.getSobrenome().contains(valor)
                        || clienteLi.getCpf().contains(valor)) {
                        listaResultado.add(clienteLi);
                    }
                }
            }
        }
        
        //Retorna a lista de clientes encontrados
        return listaResultado;
    }

    //Obtém um cliente da lista
    public static Cliente obter(Integer id)
            throws Exception {
        if (id != null && !listaCliente.isEmpty()) {
            for (int i = 0; i < listaCliente.size(); i++) {
                if (listaCliente.get(i) != null && listaCliente.get(i).getIdCliente()== id) {
                    return listaCliente.get(i);
                }                
            }
        }
        return null;
    }

}
