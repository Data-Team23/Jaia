package com.dataTeam.jaia.jaia.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "solicitacao")
@Getter
@Setter
public class Solicitacao {

    @Id
    private Long id_cli; // Coluna correspondente a id_cli

    @Id
    private Long id_req; // Coluna correspondente a id_req

    @ManyToOne
    @JoinColumn(name = "id_cli", referencedColumnName = "id_cli", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_req", referencedColumnName = "id_req", insertable = false, updatable = false)
    private Requisicao requisicao;
}
