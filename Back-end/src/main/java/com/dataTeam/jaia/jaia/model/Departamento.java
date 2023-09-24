package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "departamento")
public class Departamento {


//private Funcionario funcionario;

    @Id
    @Column(name = "cod_depart")
    private Long cod_depart;

    @Column(name = "nome_depart", unique = true)
    private String nome_depart;

     //@OneToMany(mappedBy = "departamento")
    //private List<Funcionario> funcionarios;


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

    /*public String getNome_func() {
        return funcionario.getNome_func();
    }


    public void setNome_func(String nome){
        funcionario.setNome_func(nome);
    }*/
    

}




    


