/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.dao;

import br.senac.perfumaria.db.ConnectionUtils;
import br.senac.perfumaria.model.Perfume;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.time.ZoneId;
import java.sql.Date;

/**
 *
 * @author Ubkit
 */
public class PerfumeDao {
     public static boolean inserirPerfume(Perfume perfume){
              try 
              {
                Connection con = ConnectionUtils.getConnection();
                String sql = "INSERT INTO PERFUME (NOME,MARCA,ML,QTD_PROD,PRECO,VALIDADE) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, perfume.getNome());
                ps.setString(2, perfume.getMarca());
                ps.setInt(3, (int)perfume.getMl());
                ps.setInt(4, (int)perfume.getQtdProd());
                ps.setDouble(5, perfume.preco);
                ps.setDate(6, Date.valueOf(perfume.getData()));
                ps.execute();
                return true;
              } catch (Exception e) 
              {
              e.printStackTrace();
              return false;
              }
          }
}
