/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.dao;

import br.senac.perfumaria.db.ConnectionUtils;
import br.senac.perfumaria.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ubkit
 */
public class VendaDao {
    public static Connection con;
    public static PreparedStatement ps;
    public static void inserirVenda(Venda venda){
        try {
            
        } catch (Exception e) {
        }
    }
       public static void conectarBD(String sql) throws SQLException{
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
     public static void fechaConexaoBD() throws SQLException{
        try {
            con.close();
            ps.close();
          System.out.println("conexao do banco fechada");
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexao do banco");
        } 
     }
}
