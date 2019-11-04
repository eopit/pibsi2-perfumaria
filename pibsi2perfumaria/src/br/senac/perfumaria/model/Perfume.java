/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Ubkit
 */
public class Perfume {
    
    private String nome;
    private String marca;
    private Integer idProd;
    private Integer ml;

    public Integer getMl() {
        return ml;
    }

    public void setMl(Integer ml) {
        this.ml = ml;
    }

   
    public Integer qtdProd;
    public Double preco;
    public LocalDate data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

   

    public Integer getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(Integer qtdProd) {
        this.qtdProd = qtdProd;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    

    
}
