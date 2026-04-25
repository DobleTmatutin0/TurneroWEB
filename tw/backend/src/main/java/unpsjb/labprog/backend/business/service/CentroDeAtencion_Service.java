package unpsjb.labprog.backend.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repository.CentroDeAtencion_Repository;
import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.model.CentroDeAtencion;

@Service

public class CentroDeAtencion_Service {
    
    @Autowired
    private CentroDeAtencion_Repository centroDeAtencion_Repo;

    @Transactional
    public CentroDeAtencion save(CentroDeAtencion_DTO aCentroDeAtencion) {

        Point point = new Point(
            aCentroDeAtencion.getCoordenadas().getLatitud(),
            aCentroDeAtencion.getCoordenadas().getLongitud()
        );

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

    

}