package com.dataTeam.jaia.jaia.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cli")
    private Long id;

    @Column(name = "cnpj_cli")
    private String cnpj;

    @Column(name = "nome_cli")
    private String nome;

    @Column(name = "senha_cli")
    private String senha;

    @Column(name = "email_cli")
    private String email;

    @Column(name = "end_cli")
    private String endereco;

    @Column(name = "telefone_cli")
    private String telefone;

}
