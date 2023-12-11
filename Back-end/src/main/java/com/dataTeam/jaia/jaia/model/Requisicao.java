package com.dataTeam.jaia.jaia.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "requisicao")
@Getter
@Setter
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_req")
    private Long id;

    @Column(name = "nome_req")
    private String nome;

    @Column(name = "inspecao_req")
    private String inspecao;

    @Column(name = "descricao_req")
    private String descricao;

    @Column(name = "status_req")
    private String status;

    @Column(name = "data_abertura_req")
    private LocalDateTime data_abertura;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id")
    private Cliente fkCliente;

    // @ManyToMany
    // @JoinTable(
    //     name = "solicitacao",
    //     joinColumns = @JoinColumn(name = "id_req"),
    //     inverseJoinColumns = @JoinColumn(name = "id_cli")
    // )
    // @JsonManagedReference
    // private List<Cliente> clientes;
 
}
