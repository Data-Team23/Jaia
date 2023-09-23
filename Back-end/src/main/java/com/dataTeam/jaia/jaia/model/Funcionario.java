package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @Column(name = "cpf_func")
    private Integer cpf_func;

    @Column(name = "nome_func")
    private String nome_func;

    @Column(name = "email_func")
    private String email_func;

    //relacionar com departamento
    @ManyToOne
    @JoinColumn(name = "cod_depart")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "supervisor", referencedColumnName = "cpf_func")
    private Funcionario supervisor;

    // Construtores, getters e setters

    public Integer getCpf_func() {
        return cpf_func;
    }

    public void setCpf_func(Integer cpf_func) {
        this.cpf_func = cpf_func;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getEmail_func() {
        return email_func;
    }

    public void setEmail_func(String email_func) {
        this.email_func = email_func;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Funcionario getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Funcionario supervisor) {
        this.supervisor = supervisor;
    }

}