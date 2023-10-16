package com.dataTeam.jaia.jaia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dataTeam.jaia.jaia.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    List<Requisicao> findAll();

    Optional<Requisicao> findById(Long id);

}
