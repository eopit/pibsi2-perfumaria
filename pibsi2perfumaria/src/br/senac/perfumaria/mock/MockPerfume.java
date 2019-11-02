/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.mock;

import br.senac.perfumaria.model.Perfume;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Ubkit
 */

public class MockPerfume {
    
    public static List<Perfume> listaDePerfume = new ArrayList();
    private static Integer idPerfume = 0; 
    
    
    
    public static void inserirPerfume(Perfume perfumeNovo)
    {
        Boolean inserir = true;
        for (Perfume perfume : listaDePerfume) {
            if(perfume.nome.equals(perfumeNovo.nome) && perfume.ml == perfumeNovo.ml)
               inserir = false;
        }
        if(inserir){
            idPerfume++;
            perfumeNovo.setIdProd(idPerfume); 
            listaDePerfume.add(perfumeNovo);
        }else{
        }
    }
    
    public static List<Perfume> listarPerfumes(String filtro)throws Exception{
        
             
        if(!filtro.trim().equals("")){
           List<Perfume> listaDePerfumesFiltrado = new ArrayList();
            
                for(Perfume perfume: listaDePerfume){
                    if(perfume.getNome().contains(filtro) || perfume.getMarca().contains(filtro) || String.valueOf(perfume.getMl()).equals(filtro))
                    listaDePerfumesFiltrado.add(perfume);
                }
               return  listaDePerfumesFiltrado;
        }else{
              return listaDePerfume;
        }
      }
    
    public static void excluirPerfume(Perfume perfumeExcluir){
        if(perfumeExcluir.getIdProd() != null)
        {
            for(int i = 0; i < listaDePerfume.size(); i++){
                Perfume perfume = listaDePerfume.get(i);
                if(perfume.getIdProd() == perfumeExcluir.getIdProd())
                    listaDePerfume.remove(i);
            }
        }
    }
    
    public static void alterarPerfume(Perfume perfumeAlterar){
        if(perfumeAlterar.getIdProd() != null){
               for(Perfume perfume : listaDePerfume){
                   if(perfume.getIdProd() == perfumeAlterar.getIdProd()){
                       perfume.setNome(perfumeAlterar.getNome());
                       perfume.setMarca(perfumeAlterar.getMarca());
                       perfume.setQtdProd(perfumeAlterar.getQtdProd());
                       perfume.setMl(perfumeAlterar.getMl());
                       perfume.setPreco(perfumeAlterar.getPreco());
                       perfume.setData(perfumeAlterar.getData());
                   }
               }
        }
    }
    public static void exibeSucesso(String acao){
    Alert alerta= new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Sucesso");
    alerta.setContentText("Perfume "+acao+" com sucesso");
    alerta.showAndWait();
    }
}

    

