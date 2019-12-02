package br.senac.perfumaria.dao;

import br.senac.perfumaria.db.ConnectionUtils;
import br.senac.perfumaria.model.Perfume;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PerfumeDao {

    public static Connection con;
    public static PreparedStatement ps;
    public static boolean inserirPerfume(Perfume perfume) throws SQLException {
        try {           
            String sql = "INSERT INTO perfume (NOME,MARCA,ML,QTD_PROD,PRECO,VALIDADE) VALUES (?,?,?,?,?,?)";
            conectarBD(sql);
            ps.setString(1, perfume.getNome());
            ps.setString(2, perfume.getMarca());
            ps.setInt(3, (int) perfume.getMl());
            ps.setInt(4, (int) perfume.getQtdProd());
            ps.setDouble(5, perfume.preco);
            ps.setDate(6, Date.valueOf(perfume.getData()));
            ps.execute();
            fechaConexaoBD();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
        }
    }
    
    
    public static Perfume pesquisarPerfume(Perfume perfume)throws SQLException{
                Perfume perfumeR = new Perfume();
                try {
                String sql = "SELECT * FROM perfume WHERE NOME = ? AND ML = ?";
                conectarBD(sql);
                ps.setString(1, perfume.getNome());
                ps.setInt(2, perfume.getMl());
                ResultSet result = ps.executeQuery();
                while(result.next())
                {                
                    perfumeR.setIdProd(result.getInt("ID"));
                    System.out.println(perfume.getIdProd());
                    perfumeR.setNome(result.getString("NOME"));
                    perfumeR.setMarca(result.getString("MARCA"));
                    perfumeR.setMl(result.getInt("ML"));
                    perfumeR.setQtdProd(result.getInt("QTD_PROD"));
                    perfumeR.setPreco(result.getDouble("PRECO"));
                    perfumeR.setData(result.getDate("VALIDADE").toLocalDate());
                }
                result.close();
                fechaConexaoBD();
                return perfumeR;
            } catch (Exception e) {
                e.printStackTrace();
                fechaConexaoBD();
                return perfumeR;
            }
    }
    
    public static List<Perfume> listarPerfumes(String filtro) throws SQLException{
        List<Perfume> listaDePerfumes = new ArrayList<Perfume>();
        try {
            String sql = "SELECT * FROM perfume WHERE NOME LIKE ? OR MARCA LIKE ? OR PRECO LIKE ?";
            conectarBD(sql);
            ps.setString(1, '%'+filtro+'%');
            ps.setString(2, '%'+filtro+'%');
            //vamos tentar converter o o filtro em int 
            boolean converteu = false;
            try {
                int fconvert = Integer.valueOf(filtro);
                converteu = true;
            } catch (Exception e) {
                converteu = false;
            }
            if(converteu){
                ps.setInt(3,Integer.valueOf(filtro)); 
            }else{
                System.out.println(filtro);
                ps.setInt(3,0); 
            }  
            ResultSet result =  ps.executeQuery();
            while(result.next()){
                    Perfume perfumeR = new Perfume();
                    perfumeR.setIdProd(result.getInt("ID"));
                    perfumeR.setNome(result.getString("NOME"));
                    perfumeR.setMarca(result.getString("MARCA"));
                    perfumeR.setMl(result.getInt("ML"));
                    perfumeR.setQtdProd(result.getInt("QTD_PROD"));
                    perfumeR.setPreco(result.getDouble("PRECO"));
                    perfumeR.setData(result.getDate("VALIDADE").toLocalDate()); 
                    listaDePerfumes.add(perfumeR);
            }
            result.close();
            fechaConexaoBD();
            return listaDePerfumes;
        } catch (Exception e) {
            e.printStackTrace();
             fechaConexaoBD();
            return listaDePerfumes;
        }
    }
    
    public static boolean atualizarPerfume(Perfume perfume) throws SQLException{
        try {
            String sql = "UPDATE perfume SET NOME = ?, MARCA = ?, ML = ?, QTD_PROD = ?, PRECO = ?, VALIDADE = ?, ATIVO = ? WHERE ID = ?";
            conectarBD(sql);
            ps.setString(1, perfume.getNome());
            ps.setString(2, perfume.getMarca());
            ps.setInt(3, perfume.getMl());
            ps.setInt(4, perfume.getQtdProd());
            ps.setDouble(5, perfume.getPreco());
            ps.setDate(6, Date.valueOf(perfume.getData()));
            ps.setBoolean(7, true);
            ps.setInt(8, perfume.getIdProd());
            ps.execute();
            fechaConexaoBD();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            fechaConexaoBD();
            return false;
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
