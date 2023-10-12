package com.dataTeam.jaia.jaia.controller;

import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoService;
import com.dataTeam.jaia.jaia.service.Funcionario.IFuncionarioService;
import com.dataTeam.jaia.jaia.service.OrdemServico.IOrdemServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordem-servico")
@CrossOrigin
public class OrdemServicoController {

    // @Autowired
    // private IOrdemServicoService service;

    // @GetMapping
    // public List<OrdemServico> buscartodos() {
    //     return service.buscarTodasOrdemServico();
    // }

    // @PostMapping
    // public OrdemServico novaOrdemServico(@RequestBody OrdemServico ordemServico) {
    //     return service.novaOrdemServico(ordemServico);
    // }

    // @GetMapping(value = "/{id}")
    // public OrdemServico buscarPorId(@PathVariable("id") Long id) {
    //     return service.buscarPorId(id);
    // }

}
