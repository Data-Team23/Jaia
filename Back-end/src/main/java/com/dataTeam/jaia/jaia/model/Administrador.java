package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administrador")
@Getter
@Setter
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adm")
    private Long id;

    @Column(name = "nome_usuario")
    private String nome_usuario;

    @Column(name = "nome_adm")
    private String nome_adm;

    @Column(name = "senha_adm")
    private String senha_adm;
    
}
