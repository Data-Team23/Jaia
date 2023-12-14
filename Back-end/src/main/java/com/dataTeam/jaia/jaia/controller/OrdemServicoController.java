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

import com.dataTeam.jaia.jaia.DTO.OrdemServicoDTO;
import com.dataTeam.jaia.jaia.model.Checklist;
import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.repository.ChecklistRepository;
import com.dataTeam.jaia.jaia.repository.FuncionarioRepository;
import com.dataTeam.jaia.jaia.repository.OrdemServicoRepository;
import com.dataTeam.jaia.jaia.repository.RequisicaoRepository;
import com.dataTeam.jaia.jaia.service.OrdemServico.IOrdemServicoService;

@RestController
@RequestMapping(value = "/ordem-servico")
@CrossOrigin
public class OrdemServicoController {

    @Autowired
    private IOrdemServicoService service;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @GetMapping
    public List<OrdemServico> buscartodos() {
        return service.buscarTodasOrdemServico();
    }

    @GetMapping("/{id}")
    public OrdemServico buscarOrdemServicoPorId(@PathVariable Long id) {
        return ordemServicoRepository.findById(id).orElse(null);
    }

    @PostMapping("/criar")
    public OrdemServico criarOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        Funcionario supervisor = funcionarioRepository.findById(ordemServicoDTO.getIdSupervisor())
                .orElseThrow(() -> new RuntimeException("Supervisor não encontrado"));

        Checklist checklist = checklistRepository.findById(ordemServicoDTO.getIdChecklist())
                .orElseThrow(() -> new RuntimeException("Checklist não encontrado"));

        Requisicao requisicao = requisicaoRepository.findById(ordemServicoDTO.getIdReq())
                .orElseThrow(() -> new RuntimeException("Requisição não encontrado"));

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setNome_ordem(ordemServicoDTO.getNome_ordem());
        ordemServico.setTipo_inspecao(ordemServicoDTO.getTipo_inspecao());
        ordemServico.setStatus_ordem(ordemServicoDTO.getStatus_ordem());
        ordemServico.setFkSupervisor(supervisor);
        ordemServico.setId_check(checklist);
        ordemServico.setId_req(requisicao);

        return ordemServicoRepository.save(ordemServico);
    }

    @DeleteMapping("/{id}")
    public OrdemServico excluirOrdemServico(@PathVariable Long id) {
        return service.deletarPorId(id);
    }

    @PutMapping("/{id}")
    public String atualizarOrdemServico(@PathVariable Long id, @RequestBody OrdemServicoDTO ordemServicoDTO) {
        if (ordemServicoRepository.existsById(id)) {
            OrdemServico ordemServico = ordemServicoRepository.getOne(id);
            ordemServico.setNome_ordem(ordemServicoDTO.getNome_ordem());
            ordemServico.setTipo_inspecao(ordemServicoDTO.getTipo_inspecao());
            ordemServico.setStatus_ordem(ordemServicoDTO.getStatus_ordem());
            ordemServicoRepository.save(ordemServico);
            return "Ordem de Serviço com ID: " + id + " atualizada com sucesso.";
        } else {
            return "Ordem de Serviço com ID: " + id + " não encontrada.";
        }
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<?> buscarOrdemPorSupervisor(@PathVariable Long supervisorId) {
        try {
            List<OrdemServico> ordemdoSupervisor = service.getOrdemServicosBySupervisorId(supervisorId);
            return ResponseEntity.ok(ordemdoSupervisor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar Ordem de serviço para o supervisor com o id informado.");
        }
    }

}
