package com.dataTeam.jaia.jaia.controller;

import com.dataTeam.jaia.jaia.model.Cliente;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoService;
import com.dataTeam.jaia.jaia.service.Funcionario.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@CrossOrigin
public class FuncionarioController {

    @Autowired
    private IFuncionarioService service;

    @Autowired
    IDepartamentoService departService;

    @GetMapping
    public List<Funcionario> buscartodos() {
        return service.buscarTodosFuncionario();
    }

    @PostMapping
    public Funcionario novoFuncionario(@RequestBody Funcionario funcionario) {
        return service.novoFuncionario(funcionario);
    }

    @GetMapping(value = "/{id}")
    public Funcionario buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping(value = "/excluir/{id}")
    public Funcionario deletarPorId(@PathVariable Long id) {
        return service.deletarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarClientePorCnpj(@PathVariable Long id,
            @RequestBody Funcionario funcionarioAtualizado) {
        try {
            Funcionario funcionarioAtualizadoResult = service.atualizarFuncionarioPorId(id, funcionarioAtualizado);
            return ResponseEntity.ok(funcionarioAtualizadoResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar o funcionario");
        }
    }

}
