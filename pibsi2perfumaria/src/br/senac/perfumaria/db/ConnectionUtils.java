/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Ubkit
 */
public class ConnectionUtils {
    
    public static Connection getConnection()throws Exception{
        
        String URLDB = "jdbc:mysql://localhost:3306/banco_pi";
        Properties prop = new Properties();
        
        prop.put("user", "root");
        prop.put("password","Rafa8808");
        prop.put("serverTimezone", "UTC");
        prop.put("useSSL","false");
        
        return DriverManager.getConnection(URLDB, prop);
    }
    
    
    
    
}
