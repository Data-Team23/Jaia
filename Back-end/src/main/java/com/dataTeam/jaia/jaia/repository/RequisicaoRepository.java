package com.dataTeam.jaia.jaia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataTeam.jaia.jaia.model.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    List<Requisicao> findAll();
    Optional<Requisicao> findById(Long id);

    List<Requisicao> findByFkClienteId(Long clienteId);

}
