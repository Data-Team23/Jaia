package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_func")
    private Long id;

    @Column(name = "cpf_func")
    private String cpf;

    @Column(name = "nome_func")
    private String nome;

    @Column(name = "email_func")
    private String email;

    @Column(name = "senha_func")
    private String senha;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "supervisor")
    private Funcionario supervisor;

    
}