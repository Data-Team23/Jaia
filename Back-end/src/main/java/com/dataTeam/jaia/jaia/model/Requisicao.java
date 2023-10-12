package com.dataTeam.jaia.jaia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "requisicao")
@Getter
@Setter
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_req")
    private Long id;

    @Column(name = "nome_req")
    private String cpf;

    @Column(name = "inspecao_req")
    private String inspecao;

    @Column(name = "descricao_req")
    private String descricao;

    @Column(name = "status_req")
    private String status;

    @Column(name = "data_abertura_req")
    private LocalDateTime data_abertura;
    
}
