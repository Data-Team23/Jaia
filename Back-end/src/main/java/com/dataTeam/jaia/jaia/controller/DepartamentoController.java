package com.dataTeam.jaia.jaia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.service.IDepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
@CrossOrigin
public class DepartamentoController {

    @Autowired
    private IDepartamentoService service;

    @PostMapping
    public Departamento novoDepartamento(@RequestBody Departamento departamento) {
        return service.novoDepartamento(departamento);
    }

}
