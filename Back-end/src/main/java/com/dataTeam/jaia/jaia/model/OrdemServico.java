package com.dataTeam.jaia.jaia.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordem_servico")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordem")
    private Long id;

    @Column(name = "nome_ordem")
    private String nome_ordem;

    @Column(name = "tipo_inspecao")
    private String tipo_inspecao;

    @Column(name = "status_ordem")
    private String status_ordem;

    @ManyToOne
    @JoinColumn(name = "id_supervisor")
    private Funcionario id_supervisor;

    @ManyToOne
    @JoinColumn(name = "id_cli")
    private Cliente id_cli;

    @ManyToOne
    @JoinColumn(name = "id_check")
    private Checklist id_check;
    
}