package unpsjb.labprog.backend.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.model.Consultorio;
import unpsjb.labprog.backend.business.repository.Consultorio_Repository;

@Service

public class Consultorio_Service {
    
    @Autowired
    private Consultorio_Repository consultorio_Repo;

    public List<Consultorio> findAll() {
        List<Consultorio> result = new ArrayList<>();
        consultorio_Repo.findAllNotEliminado().forEach(consultorio -> result.add(consultorio));  
        return result; 
    }

    public Consultorio findById(Integer id) {
        return consultorio_Repo.findByIdAndNotEliminado(id);
    }

    public Consultorio findByName(String aName) {
        return consultorio_Repo.findByNameAndNotEliminado(aName);
    }

    @Transactional
    public Consultorio save(Consultorio aNewConsultorio) {
        return consultorio_Repo.save(aNewConsultorio);
    }

}
