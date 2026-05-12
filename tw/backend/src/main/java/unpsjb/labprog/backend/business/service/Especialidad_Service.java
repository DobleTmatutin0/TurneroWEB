package unpsjb.labprog.backend.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repository.Especialidad_Repository;
import unpsjb.labprog.backend.model.Especialidad;

@Service

public class Especialidad_Service {
    
    @Autowired
    private Especialidad_Repository especialidad_Repo;

    public List<Especialidad> findAll() {
        List<Especialidad> result = new ArrayList<>();
        especialidad_Repo.findAll().forEach(especialidad -> result.add(especialidad));
        return result;
    }

    public Especialidad findById(int id) {
        return especialidad_Repo.findById(id).get();
    }

    public Especialidad findByName(String aName) {
        return especialidad_Repo.findByName(aName);
    }

    public boolean existsByName(String aName) {
        return especialidad_Repo.existsByName(aName);
    }

    @Transactional
    public Especialidad save(Especialidad aEspecialidad) {
        return especialidad_Repo.save(aEspecialidad);
    }



}
