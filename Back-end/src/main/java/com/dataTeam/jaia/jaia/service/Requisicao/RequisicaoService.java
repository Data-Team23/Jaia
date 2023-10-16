package com.dataTeam.jaia.jaia.service.Requisicao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dataTeam.jaia.jaia.model.Requisicao;
import com.dataTeam.jaia.jaia.repository.RequisicaoRepository;

@Service
public class RequisicaoService implements IRequisicaoService {


    @Autowired
    private RequisicaoRepository requiRepo;

    @Transactional
    public Requisicao novaRequisicao(Requisicao requisicao) {
        return requiRepo.save(requisicao);
    }



    public List<Requisicao> buscarTodasRequisicoes(){
        List<Requisicao> requisicoes = requiRepo.findAll();
        return requisicoes;
    }

    public Requisicao buscarPorId(Long id) {
        Optional<Requisicao> requisicaoOp = requiRepo.findById(id);
        if (requisicaoOp.isEmpty()) {
            throw new IllegalArgumentException("Usuario nao encontrado!");
        }
        Requisicao requisicao = requisicaoOp.get();
        return requisicao;
    }


    public Requisicao deletarPorId(Long id) {
        Optional<Requisicao> requisicaoOp = requiRepo.findById(id);
        if (requisicaoOp.isEmpty()) {
            throw new IllegalArgumentException("Requisição não encontrado!");
        }
        Requisicao requisicaoToDelete = requisicaoOp.get();
        
        requiRepo.delete(requisicaoToDelete);

        return requisicaoToDelete;
    }

    @Transactional
    public Requisicao atualizarPorId(Long id, Requisicao requisicaoAtualizada) {
        Optional<Requisicao> requisicaoExistente = requiRepo.findById(id);
        if (requisicaoExistente.isEmpty()) {
            try {
                throw new Exception("Requisicao com id " + id + " não encontrada.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        Requisicao requisicao = requisicaoExistente.get();
        
        requisicao.setNome(requisicaoAtualizada.getNome());
        requisicao.setData_abertura(requisicaoAtualizada.getData_abertura());
        requisicao.setDescricao(requisicaoAtualizada.getDescricao());
        requisicao.setInspecao(requisicaoAtualizada.getInspecao());
        requisicao.setId(id);

        return requiRepo.save(requisicao);
    }



    
}
