package com.dataTeam.jaia.jaia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Pergunta;
import com.dataTeam.jaia.jaia.service.Pergunta.PerguntaService;

@RestController
@RequestMapping(value = "/pergunta")
@CrossOrigin
public class PerguntaController {

    @Autowired
    private PerguntaService service;

    @GetMapping
    public List<Pergunta> buscarTodas(){
        return service.buscarTodas();
    }

    @PostMapping("/{checkListId}")
    public Pergunta novaPergunta(@RequestBody Pergunta pergunta, @PathVariable("checkListId") Long checkListId){
        return service.novaPergunta(pergunta, checkListId);
    }
    
}
