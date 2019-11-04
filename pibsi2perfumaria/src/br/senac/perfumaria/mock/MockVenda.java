/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.mock;

import br.senac.perfumaria.model.Cliente;
import br.senac.perfumaria.model.Perfume;
import br.senac.perfumaria.model.Venda;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ubkit
 */
public class MockVenda {

    public static List<Venda> listaDeVendas = new ArrayList();

    public static Integer idVenda = 0;

    public static Boolean vendaAtiva = false;

    public static Cliente verificarCpf(Cliente cliente) {
        if (cliente != null) {
            for (Cliente cli : MockCliente.listaCliente) {
                if (cli.getCpf().equals(cliente.getCpf())) {
                    return cliente = cli;
                }
            }
        }
        return null;
    }

    public static Perfume verificarPerfume(String cN, String ml) {
        if (!cN.isEmpty()) {
            for (Perfume perfume : MockPerfume.listaDePerfume) {
                if ((perfume.getNome().equals(cN) || perfume.getIdProd().toString().equals(cN)) && perfume.getMl().toString().equals(ml)) {
                    Perfume perfumeRetorno = new Perfume();
                    perfumeRetorno.setNome(perfume.getNome());
                    perfumeRetorno.setMarca(perfume.getMarca());
                    perfumeRetorno.setQtdProd(perfume.getQtdProd());
                    perfumeRetorno.setMl(perfume.getMl());
                    perfumeRetorno.setData(perfume.getData());
                    perfumeRetorno.setIdProd(perfume.getIdProd());
                    perfumeRetorno.setPreco(perfume.getPreco());
                    return perfumeRetorno;
                }

            }
        }
        return null;
    }

    public static void FinalizarCompra(List<Venda> listaVendaT) {
        for (Venda venda : listaVendaT) {
            listaDeVendas.add(venda);
        }
        for (Venda item : listaVendaT) {
            for (Perfume perfume : MockPerfume.listaDePerfume) {
                if (item.getPerfume().getIdProd() == perfume.getIdProd()) {
                    perfume.setQtdProd(perfume.getQtdProd() - item.getPerfume().getQtdProd());
                }
            }
        }
        vendaAtiva = false;
    }

    public static Integer buscarId() {
        if (vendaAtiva) {
            return idVenda;
        } else {
            idVenda++;
            vendaAtiva = true;
            return idVenda;
        }
    }

    //Lista todos os clientes
    public static List<Venda> listar()
            throws Exception {
        //Retorna a lista de clientes
        return listaDeVendas;
    }

    public static List<Venda> procurar(LocalDate dataInicial, LocalDate dataFinal) throws Exception {
        List<Venda> listaResultado = new ArrayList<Venda>();

        if (dataInicial != null && dataFinal != null && !listaDeVendas.isEmpty()) {
            for (Venda vendaNew : listaDeVendas) {
                if (vendaNew != null && vendaNew.getData() != null) {
                    int resultadoPrimeiraData = dataInicial.compareTo(vendaNew.getData());
                    int resultadoSegundaData = dataFinal.compareTo(vendaNew.getData());
                    System.out.println(resultadoPrimeiraData);
                    if (resultadoPrimeiraData >= 0 && resultadoSegundaData <= 0) {
                        System.out.println("ENTROU NO IF DAS DATAS!");
                        listaResultado.add(vendaNew);
                    }
                }
            }
        }
        return listaResultado;
    }
}
