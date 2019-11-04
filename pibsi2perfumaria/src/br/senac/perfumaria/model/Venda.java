/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.perfumaria.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Ubkit
 */
public class Venda {
    
    private Perfume perfume;
    
    private Cliente cliente;
    
    private LocalDate data;
    
    private Integer idVenda;
    
    private LocalTime time;

    public Perfume getPerfume() {
        return perfume;
    }

    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }
    
    
    
}
