package com.dataTeam.jaia.jaia.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.service.Requisicao.IRequisicaoService;

@RestController
@RequestMapping(value = "/requisicao")
@CrossOrigin
public class RequisicaoController {

    @Autowired
    private IRequisicaoService service;

    @GetMapping
    public List<Requisicao> buscarTodasRequisicoes(){
        return service.buscarTodasRequisicoes();
    }

    @PostMapping("/{clienteId}")
    public Requisicao novaRequisicao(@RequestBody Requisicao requisicao, @PathVariable Long clienteId){
        return service.novaRequisicao(requisicao, clienteId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
    try {
        Requisicao requisicaoEncontrada = service.buscarPorId(id);
        return ResponseEntity.ok(requisicaoEncontrada);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro ao buscar requisicao com id informado.");
    }
}

    @DeleteMapping("/excluir/{id}")
    public Requisicao deletarPorId(@PathVariable Long id){
        return service.deletarPorId(id);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPorId(@PathVariable Long id,@RequestBody Requisicao requisicaoAtualizada) {
        try {
            Requisicao requisicaoAtualizadaResult = service.atualizarPorId(id, requisicaoAtualizada);
            return ResponseEntity.ok(requisicaoAtualizadaResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar a requisição");
        }

    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarRequisicoesPorCliente(@PathVariable Long clienteId) {
        try {
            List<Requisicao> requisicoesDoCliente = service.buscarRequisicoesPorCliente(clienteId);
            return ResponseEntity.ok(requisicoesDoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar requisicoes para o cliente com o id informado.");
        }
    }

}
