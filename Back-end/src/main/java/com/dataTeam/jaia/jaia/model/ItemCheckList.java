package com.dataTeam.jaia.jaia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item_checklist")
@Getter
@Setter
public class ItemCheckList {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_depart")
    private Departamento departamento;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_check")
    private Checklist checklist;

    @Column(name = "status_check")
    private String statusCheck;

}



