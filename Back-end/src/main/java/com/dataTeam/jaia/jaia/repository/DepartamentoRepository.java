package com.dataTeam.jaia.jaia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dataTeam.jaia.jaia.model.Departamento;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    //busca pelo Id (código do departamento)
    @Query("SELECT d FROM Departamento d JOIN d.funcionarios f WHERE f.cpf_func = f.supervisor AND d.cod_depart = :codDepart")
    Optional<Departamento> findByCod_depart(Long codDepart);

    
}
