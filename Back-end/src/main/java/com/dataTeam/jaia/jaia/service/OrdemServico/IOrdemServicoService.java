package com.dataTeam.jaia.jaia.service.OrdemServico;

import com.dataTeam.jaia.jaia.model.OrdemServico;

import java.util.List;

public interface IOrdemServicoService {

    public OrdemServico novaOrdemServico(OrdemServico ordemServico);

    public List<OrdemServico> buscarTodasOrdemServico();

    public List<OrdemServico> getOrdemServicosBySupervisorId(Long supervisorId);

    public OrdemServico deletarPorId(Long ordemId);

}
