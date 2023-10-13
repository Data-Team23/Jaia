package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "checklist_pergunta")
@Getter
@Setter
public class ChecklistPergunta {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_check")
    private Checklist checklist;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pergunta")
    private Pergunta pergunta;
}
