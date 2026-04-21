package unpsjb.labprog.backend.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repository.CentroDeAtencion_Repository;
import unpsjb.labprog.backend.model.CentroDeAtencion;

@Service

public class CentroDeAtencion_Service {
    
    @Autowired
    private CentroDeAtencion_Repository centroDeAtencion_Repo;

    @Transactional
    public CentroDeAtencion save(CentroDeAtencion aCentroDeAtencion) {
        return centroDeAtencion_Repo.save(aCentroDeAtencion);
    }

    public boolean findByName(String aName) {
        return centroDeAtencion_Repo.existsByName(aName);
    }

    

}