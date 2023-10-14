package com.dataTeam.jaia.jaia.repository;

import com.dataTeam.jaia.jaia.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    List<Departamento> findAll();

    Optional<Departamento> findByCodDepart(Long codDepart);

}
