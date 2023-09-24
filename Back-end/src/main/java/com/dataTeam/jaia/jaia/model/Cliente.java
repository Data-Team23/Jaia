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

    @Column(name = "email_cli")
    private String email;

<<<<<<< HEAD
    @Column(name = "telefone_cli")
    private Double telefone;

=======
    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "telefone_cli")
    private Double telefone;


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

>>>>>>> 3006b36950b0290ab32895ac1e0717d86785b530
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTelefone() {
        return telefone;
    }

    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }




}
