package com.dataTeam.jaia.jaia.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "checklist")
@Getter
@Setter
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_check")
    private Long id;

    @Column(name = "nome_check")
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "checklist_pergunta", joinColumns = @JoinColumn(name = "id_check"), inverseJoinColumns = @JoinColumn(name = "id_pergunta"))
    private List<Pergunta> perguntas;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "item_checklist", joinColumns = @JoinColumn(name = "id_check"), inverseJoinColumns = @JoinColumn(name = "id_depart"))
    private Departamento departamento;

}
