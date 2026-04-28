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
    public CentroDeAtencion save(CentroDeAtencion_DTO aCentroDeAtencion) {

        double latitud = Double.parseDouble(aCentroDeAtencion.getCoordenadas().getLatitud());
        double longitud = Double.parseDouble(aCentroDeAtencion.getCoordenadas().getLongitud());

        if (latitud < -90 || latitud > 90) {
            throw new RuntimeException("Latitud inválida (rango: -90 <= latitud <= 90)");
        }

        if (longitud < -180 || longitud > 180) {
            throw new RuntimeException("Longitud inválida (rango: -180 <= longitud <= 180)");
        }

        Point point = new Point(latitud, longitud);

        CentroDeAtencion CentroDeAtencionToSave = new CentroDeAtencion();
        CentroDeAtencionToSave.setNombre(aCentroDeAtencion.getNombre());
        CentroDeAtencionToSave.setLocalidad(aCentroDeAtencion.getLocalidad());
        CentroDeAtencionToSave.setDireccion(aCentroDeAtencion.getDireccion());
        CentroDeAtencionToSave.setCoordenadas(point);
        CentroDeAtencionToSave.setTelefono(aCentroDeAtencion.getTelefono());

        return centroDeAtencion_Repo.save(CentroDeAtencionToSave);
    }

    public boolean findByNameAndAddress(String aName, String anAddress) {
        return centroDeAtencion_Repo.existsByNameAndAddress(aName, anAddress);
    }

    public boolean findByAddress(String anAddress) {
        return centroDeAtencion_Repo.existsByAddress(anAddress);
    }

    public void deleteAll() {
        centroDeAtencion_Repo.deleteAll();
    }

}