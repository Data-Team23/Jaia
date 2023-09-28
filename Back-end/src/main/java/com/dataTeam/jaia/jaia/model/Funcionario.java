package com.dataTeam.jaia.jaia.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Funcionario")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private Long id;

    @Column(name = "cpf")
    private Double cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "supervisor")
    private String supervisor;

    @ManyToOne
    @JoinColumn(name="FK_DEPARTAMENTO_COD_DEP")
    private Departamento departamento;

}
