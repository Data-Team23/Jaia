package com.dataTeam.jaia.jaia.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dataTeam.jaia.jaia.model.Departamento;

public interface IDepartamentoService {

    //add departamento
    ResponseEntity<Departamento> novoDepartamento(Departamento departamento);

    //delete departamento
    ResponseEntity<String> deleteDepartamento(Long id);

    //listar departamento
    List<Departamento> listarDepartamento();

    //update departamento
    Departamento atualizarDepartamento(Long id, Departamento atualizarDepartamento);
}
