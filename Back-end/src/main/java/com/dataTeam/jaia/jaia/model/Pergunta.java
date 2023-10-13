package com.dataTeam.jaia.jaia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pergunta")
@Getter
@Setter
public class Pergunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pergunta")
    private Long id;

    @Column(name = "pergunta")
    private String pergunta;

    @Column(name = "comentario_pergunta")
    private String comentario;

    @Column(name = "status_pergunta")
    private String status;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "checklist_pergunta", 
               joinColumns = @JoinColumn(name = "id_pergunta"),
               inverseJoinColumns = @JoinColumn(name = "id_check"))
    private List<Checklist> checklists;

}
