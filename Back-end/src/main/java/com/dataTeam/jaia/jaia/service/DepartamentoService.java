package com.dataTeam.jaia.jaia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DepartamentoService implements IDepartamentoService {
    
    @Autowired
    private DepartamentoRepository departRepo;

    @Transactional
    public Departamento novoDepartamento(Departamento departamento){
        if (departamento == null ||
                departamento.getCod_depart() == null ||
                departamento.getNome_depart() == null ||
                departamento.getNome_depart().isBlank()){
                //departamento.getNome_func() == null ||
                //departamento.getNome_func().isBlank()){
            throw new IllegalArgumentException("Não foi possível cadastrar o novo departamento, tente novamente");
        }
        return departRepo.save(departamento);
    }

    public List<Departamento> listarDepartamento(){
        return departRepo.findAll();
    }

    public Departamento deleteDepartamento(Long cod_depart){
        Optional<Departamento> delete = departRepo.findById(cod_depart);

        if (delete.isPresent()){
            departRepo.deleteById(cod_depart);
            return delete.get(); // Retorna o departamento excluído
        } else {
            throw new IllegalArgumentException("Departamento não encontrado.");
        }
    }

    @Transactional
    public Departamento atualizarDepartamento(Long cod_depart, Departamento atualizarDepartamento) {
    Optional<Departamento> existingDepartmentOptional = departRepo.findById(cod_depart);

    if (existingDepartmentOptional.isPresent()) {
        Departamento existingDepartment = existingDepartmentOptional.get();
        
        // Verificar e atualizar apenas campos não nulos
        if (atualizarDepartamento.getNome_depart() != null) {
            existingDepartment.setNome_depart(atualizarDepartamento.getNome_depart());
        }
        
        /*if (atualizarDepartamento.getNome_func() != null) {
            existingDepartment.setNome_func(atualizarDepartamento.getNome_func());
        }*/

        return departRepo.save(existingDepartment);
    } else {
        throw new EntityNotFoundException("O departamento encontrado não foi encontrado: " + cod_depart);
    }
}

}

    
    

