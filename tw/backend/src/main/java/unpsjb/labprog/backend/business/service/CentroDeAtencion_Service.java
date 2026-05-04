package unpsjb.labprog.backend.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repository.CentroDeAtencion_Repository;
import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.mapper.CentroDeAtencion_Mapper;
import unpsjb.labprog.backend.model.CentroDeAtencion;

@Service

public class CentroDeAtencion_Service {
    
    @Autowired
    private CentroDeAtencion_Repository centroDeAtencion_Repo;

    public List<CentroDeAtencion_DTO>findAll() {
        List<CentroDeAtencion_DTO> result = new ArrayList<>();
        centroDeAtencion_Repo.findAll().forEach(centro -> result.add(CentroDeAtencion_Mapper.toDTO(centro)));
        return result;
    }    

    @Transactional
    public CentroDeAtencion save(CentroDeAtencion aCentroDeAtencion) {
        return centroDeAtencion_Repo.save(aCentroDeAtencion);
    }

    public CentroDeAtencion findByNameAndAddress(String aName, String anAddress) {
        return centroDeAtencion_Repo.findByNameAndAddress(aName, anAddress);
    }

    public CentroDeAtencion findByAddress(String anAddress) {
        return centroDeAtencion_Repo.findByAddress(anAddress);
    }

    public boolean existsByNameAndAddress(String aName, String anAddress) {
        return centroDeAtencion_Repo.existsByNameAndAddress(aName, anAddress);
    }

    public boolean existsByAddress(String anAddress) {
        return centroDeAtencion_Repo.existsByAddress(anAddress);
    }

    public void deleteAll() {
        centroDeAtencion_Repo.deleteAll();
    }

}