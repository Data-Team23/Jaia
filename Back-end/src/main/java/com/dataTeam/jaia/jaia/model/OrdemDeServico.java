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
@Table(name = "ordem_servico")
@Getter
@Setter
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordem")
    private Long id;

    @Column(name = "nome_ordem")
    private String nome;

    @Column(name = "tipo_inspecao")
    private String tipo;

    @Column(name = "status_ordem")
    private String status;
    
}
