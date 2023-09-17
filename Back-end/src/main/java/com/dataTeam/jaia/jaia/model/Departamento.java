package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "cod_dapart")
    private Long cod_depart;

    @Column(name = "nome_depart", unique = true)
    private String nome_depart;


    public Long getCod_depart() {
        return cod_depart;
    }

    public void setCod_depart(Long cod_depart) {
        this.cod_depart = cod_depart;
    }


    public String getNome_depart() {
        return nome_depart;
    }

    public void setNome_depart(String nome_depart) {
        this.nome_depart = nome_depart;
    }

    public Departamento(Long cod_depart, String nome_depart){
        this.cod_depart = cod_depart;
        this.nome_depart = nome_depart;
    }

}
