package com.dataTeam.jaia.jaia.service.OrdemServico;

import com.dataTeam.jaia.jaia.model.Funcionario;
import com.dataTeam.jaia.jaia.model.OrdemServico;
import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService implements IOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemRepo;

    @Override
    public OrdemServico novaOrdemServico(OrdemServico ordemServico) {
        return ordemRepo.save(ordemServico);
    }

    @Override
    public List<OrdemServico> buscarTodasOrdemServico() {
        List<OrdemServico> ordemServico = ordemRepo.findAll();
        return ordemServico;
    }

    @Override
    public List<OrdemServico> getOrdemServicosBySupervisorId(Long supervisorId) {
        return ordemRepo.findByFkSupervisorId(supervisorId);
    }

    @Override
    public OrdemServico deletarPorId(Long id) {
        Optional<OrdemServico> ordemOptional = ordemRepo.findById(id);
        if (ordemOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        OrdemServico ordemToDelete = ordemOptional.get();

        ordemRepo.deleteByIdWithCascade(ordemToDelete.getId());

        return ordemToDelete;
    }

}
