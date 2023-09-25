package com.dataTeam.jaia.jaia.service.Departamento;


import com.dataTeam.jaia.jaia.model.Departamento;
import com.dataTeam.jaia.jaia.repository.DepartamentoRepository;
import com.dataTeam.jaia.jaia.service.Departamento.IDepartamentoSerivce;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService implements IDepartamentoSerivce {

    @Autowired
    private DepartamentoRepository departrepo;

    @Transactional
    public Departamento novoDepartamento(Departamento departamento) {
        if(departamento == null ||
                departamento.getNome() == null ||
                departamento.getNome().isBlank()){
            throw new IllegalArgumentException("Departamento com atributos inválidos!");
        }
        return departamento;
    }

    public List<Departamento> buscarTodosDepartamentos() {
        return departrepo.findAll();
    }

    public Departamento buscarPorCod_depart(Long Cod_depart) {
        Optional<Departamento> departamentoOp = departrepo.findById(Cod_depart);
        if(departamentoOp.isEmpty()) {
            throw new IllegalArgumentException("Departamento nao encontrado!");
        }
        return departamentoOp.get();
    }


    public Departamento deleteDepartamento(Long Cod_depart){
        Optional<Departamento> delete = departrepo.findById(Cod_depart);

        if (delete.isPresent()){
            departrepo.findById(Cod_depart);
            return delete.get(); // Retorna o departamento excluído
        } else {
            throw new IllegalArgumentException("Departamento não encontrado.");
        }
    }

    @Override
    public List<Departamento> listarDepartamento() {
        return departrepo.findAll();
    }



    @Transactional
    public Departamento atualizarDepartamento(Long Cod_depart, Departamento atualizarDepartamento) {
        Optional<Departamento> existingDepartmentOptional = departrepo.findById(Cod_depart);

        if (existingDepartmentOptional.isPresent()) {
            Departamento existingDepartment = existingDepartmentOptional.get();

            // Verificar e atualizar apenas campos não nulos
            if (atualizarDepartamento.getNome() != null) {
                existingDepartment.setNome(atualizarDepartamento.getNome());
            }

//            if (atualizarDepartamento.getNome_func() != null) {
//            existingDepartment.setNome_func(atualizarDepartamento.getNome_func());
//

            return departrepo.save(existingDepartment);
        } else {
            throw new EntityNotFoundException("O departamento encontrado não foi encontrado: " + Cod_depart);
        }
    }



}
