package com.dataTeam.jaia.jaia.service.Requisicao;

import java.util.List;

import com.dataTeam.jaia.jaia.model.Requisicao;

public interface IRequisicaoService {

    public Requisicao novaRequisicao (Requisicao requisicao);

    public List<Requisicao> buscarTodasRequisicoes();

    public Requisicao buscarPorId(Long Id);

    public Requisicao deletarPorId(Long Id);

    public Requisicao atualizarPorId(Long id, Requisicao requisicaoAtualizada);

}
