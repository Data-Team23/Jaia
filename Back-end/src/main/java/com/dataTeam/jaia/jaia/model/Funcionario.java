package com.dataTeam.jaia.jaia.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    public Long Id;

    @Column(name = "cpf_func")
    public Double cpf;

    @Column(name = "nome_func")
    public String nome;

    @Column(name = "email_func")
    public String email;

    @Column(name = "supervisor")
    public String supervisor;


    //  COD_DEPART

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Double getCpf() {
        return cpf;
    }

    public void setCpf(Double cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }



}
