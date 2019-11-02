/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.mock;

import br.senac.perfumaria.model.Cliente;
import br.senac.perfumaria.model.Perfume;
import br.senac.perfumaria.model.Venda;
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
    
    public static Cliente verificarCpf(Cliente cliente){
        if(cliente != null){
            for(Cliente cli : MockCliente.listaCliente){
                if(cli.getCpf().equals(cliente.getCpf()))
                    return cliente = cli;
            }
        }
        return null;
    }
    
    public static Perfume verificarPerfume(String cN,String ml){
        if(!cN.isEmpty())
        {
            for(Perfume perfume : MockPerfume.listaDePerfume){
                if((perfume.getNome().equals(cN) || perfume.getIdProd().toString().equals(cN)) && perfume.getMl().toString().equals(ml))
                    return perfume;
            }
        }
        return  null;
    }
    
  public static Integer buscarId(){
      if(vendaAtiva){
          return idVenda;
      }else{
          idVenda++;
          vendaAtiva = true;
          return idVenda;
      }
  }
}
