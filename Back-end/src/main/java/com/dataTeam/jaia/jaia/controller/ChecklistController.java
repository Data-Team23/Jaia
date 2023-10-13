package com.dataTeam.jaia.jaia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Pergunta;
import com.dataTeam.jaia.jaia.service.Checklist.ChecklistService;
import com.dataTeam.jaia.jaia.service.Pergunta.PerguntaService;

@RestController
@RequestMapping(value = "/checklist")
@CrossOrigin
public class ChecklistController {

    @Autowired
    private ChecklistService service;

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping
    public List<Checklist> buscarTodos(){
        return service.buscarTodos();
    }

    @PostMapping
    public Checklist novoChecklist(@RequestBody Checklist checklist){
        for (Pergunta pergunta : checklist.getPerguntas()) {
            pergunta = perguntaService.criarPergunta(pergunta);
        }
        return service.novoChecklist(checklist);
    }
    
}
