package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Long id;

    @Column(name = "cnpj_cli")
    private Double cnpj;

    @Column(name = "nome_cli")
    private String nome;

    @Column(name = "senha_cli")
    private String senha;

//    @Column(name = "")
//    private String email;
//
//    @Column(name = "")
//    private String logadouro;

    @Column(name = "telefone_cli")
    private Double telefone;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCnpj() {
        return cnpj;
    }

    public void setCnpj(Double cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getLogadouro() {
//        return logadouro;
//    }
//
//    public void setLogadouro(String logadouro) {
//        this.logadouro = logadouro;
//    }

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }




}
