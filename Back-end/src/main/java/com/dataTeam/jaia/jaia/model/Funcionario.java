package com.dataTeam.jaia.jaia.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_func")
    private Long id;

    @Column(name = "cpf_func", unique = true)
    private String cpf;

    @Column(name = "nome_func")
    private String nome;

    @Column(name = "senha_func")
    private String senha;

    @Column(name = "email_func", unique = true)
    private String email;

    @Column(name = "telefone_func")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "supervisor")
    private Funcionario supervisor;

    
}