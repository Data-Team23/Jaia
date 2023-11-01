package com.dataTeam.jaia.jaia.service.Checklist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.ChecklistRepository;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checkListRepo;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    public Checklist novoChecklist(Checklist checklist, Long departamentoId){
        Optional<Departamento> optDepartamento = departamentoRepo.findById(departamentoId);
        if(optDepartamento.isPresent()){
            Departamento departamento = optDepartamento.get();
            checklist.setDepartamento(departamento);
            departamento.getChecklists().add(checklist);
            departamentoRepo.save(departamento);
            checklist = checkListRepo.save(checklist);
        }
        return checklist;
    }

    public List<Checklist> buscarTodos(){
        return checkListRepo.findAll();
    }

    public Checklist buscarPorId(Long id){
        Optional<Checklist> optChecklist = checkListRepo.findById(id);
        if(optChecklist.isEmpty()){
            throw new IllegalArgumentException("Checklist não encontrado!!");
        }
        Checklist checklist = optChecklist.get();
        return checklist;
    }

    public void deletarPorId(Long id){
        checkListRepo.deleteByIdWithCascade(id);
    }
    
}
