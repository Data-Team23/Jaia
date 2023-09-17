package com.dataTeam.jaia.jaia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartamentoService implements IDepartamentoService {
    
    @Autowired
    private DepartamentoRepository departRepo;

    @Transactional
    @Override
    public Departamento novoDepartamento(Departamento departamento){
        if(departamento == null||
                departamento.getCod_depart() == null ||
                departamento.getNome_depart() == null ||
                departamento.getNome_depart().isBlank())
            throw new IllegalArgumentException("Departamento com atributos inválidos");

    Departamento departamentoNovo = departRepo.save(departamento);
    return departamentoNovo;
}
    
}
