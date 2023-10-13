package com.dataTeam.jaia.jaia.service.Checklist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.repository.ChecklistRepository;

@Service
public class ChecklistService {

    @Autowired
    private ChecklistRepository checkListRepo;

    public Checklist novoChecklist(Checklist checklist){
        return checkListRepo.save(checklist);
    }

    public List<Checklist> buscarTodos(){
        return checkListRepo.findAll();
    }
    
}
