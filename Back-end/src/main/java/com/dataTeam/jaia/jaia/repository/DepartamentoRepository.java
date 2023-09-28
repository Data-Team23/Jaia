package com.dataTeam.jaia.jaia.repository;


import com.dataTeam.jaia.jaia.model.Departamento;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<Departamento> findAll();

}
