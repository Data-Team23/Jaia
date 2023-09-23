package com.dataTeam.jaia.jaia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.service.FuncionarioService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "/funcionarios")
@CrossOrigin
public class FuncionarioController {

    @Autowired(required=true)
    private FuncionarioService funcionarioService;

  
    @PostMapping("/adicionar")
    public Funcionario adicionarFuncionario(@RequestBody Funcionario funcionario){

        return funcionarioService.novoFuncionario(funcionario);

    }

   
    @DeleteMapping("/deletar/{cpf}")
    public void deletarFuncionario(@PathVariable Integer cpf){
        funcionarioService.deletarFuncionario(cpf);

    }

    @GetMapping("/listarporcpf/{cpf}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorCpf(@PathVariable Integer cpf) {
        Funcionario funcionario = funcionarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(funcionario);
    }


    @GetMapping("/listar")
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.buscarTodosFuncionarios();
        
    }

    @PutMapping("/atualizar/{cpf}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
        @PathVariable Integer cpf, 
        @RequestBody Funcionario novoFuncionario
    ) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(cpf, novoFuncionario);
        
        if (funcionarioAtualizado != null) {
            return ResponseEntity.ok(funcionarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
}
