package br.senac.perfumaria.model;

import java.time.LocalDate;

public class Cliente {

    //Dados pessoais
    private Integer idCliente;
    private String nome;
    private String sobrenome;
    private LocalDate dataDeNascimento;
    private String cpf;
    private String rg;
    private String genero;
    private String estadoCivil;
    private String telefone;
    private String email;
    
    //Endereco
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    
    
    
    //Metodos para settar/gettar dados
    
        //GET
    
    public Integer getIdCliente() {
        return idCliente;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }
    
    public LocalDate getDataDenascimento() {
        return dataDeNascimento;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public String getRg() {
        return rg;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public String getEstadoCivil() {
        return estadoCivil;
    }    
    
    public String getTelefone() {
        return telefone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getCep() {
        return cep;
    }
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public String getUf() {
        return uf;
    }
    
        //SET
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setNomeCliente(String nome) {
        this.nome = nome;
    }
    
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public void setDataDenascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }    
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }
}
