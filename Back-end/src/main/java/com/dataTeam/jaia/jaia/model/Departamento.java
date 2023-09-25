package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;


@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @Column(name = "cod_depart")
    private Long cod_depart;

    @Column(name = "nome_depart")
    private String nome;


    public Long getCod_depart() {
        return cod_depart;
    }

    public void setId(Long cod_depart) {
        this.cod_depart = cod_depart;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}
