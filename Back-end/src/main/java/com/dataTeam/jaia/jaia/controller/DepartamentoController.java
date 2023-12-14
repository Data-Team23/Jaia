package com.dataTeam.jaia.jaia.controller;

import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
@CrossOrigin
public class DepartamentoController {

        @Autowired
        private IDepartamentoService service;

        @GetMapping
        public List<Departamento> buscartodos() {
                return service.buscarTodosDepartamentos();
        }

        @PostMapping
        public Departamento criarDepartamento(@RequestBody Departamento departamento) {
                return service.criarDepartamento(departamento);
        }

        @GetMapping("/{codDepart}")
        public ResponseEntity<?> buscarPorCodDepart(@PathVariable Long codDepart) {
                try {
                        Departamento departamentoEncontrado = service.buscarPorIdDepart(codDepart);
                        return ResponseEntity.ok(departamentoEncontrado);
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body("Erro interno ao buscar departamento");
                }
        }

        @DeleteMapping("/excluir/{idDepart}")
        public ResponseEntity<String> excluirDepartamentoPorID(@PathVariable Long idDepart) {
                try {
                service.excluirDepartamentoPorIdDepart(idDepart);
                return ResponseEntity.ok("Departamento excluído com sucesso");
                } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir o departamento.");
                }
        }

        @PutMapping("/atualizar/{idDepart}")
        public ResponseEntity<?> atualizarDepartamentoPorIdDepart(@PathVariable Long idDepart,
                @RequestBody Departamento departamentoAtualizado) {
                try {
                Departamento departamentoAtualizadoResult = service.atualizarDepartamentoPorIdDepart(idDepart, departamentoAtualizado);
                return ResponseEntity.ok(departamentoAtualizadoResult);
                } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar o departamento");
                }
        }

}
