package unpsjb.labprog.backend.mapper;

import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.dto.Coordenadas_DTO;
import unpsjb.labprog.backend.model.CentroDeAtencion;

public class CentroDeAtencion_Mapper {
    public static CentroDeAtencion_DTO toDTO(CentroDeAtencion aCentroDeAtencion) {
        CentroDeAtencion_DTO result = new CentroDeAtencion_DTO();

        result.setNombre(aCentroDeAtencion.getNombre());
        result.setDireccion(aCentroDeAtencion.getDireccion());
        result.setLocalidad(aCentroDeAtencion.getLocalidad());
        result.setTelefono(aCentroDeAtencion.getTelefono());

        if (aCentroDeAtencion.getCoordenadas() != null) {
            Coordenadas_DTO coordDTO = new Coordenadas_DTO();
            coordDTO.setLatitud(Double.toString(aCentroDeAtencion.getCoordenadas().getX()));
            coordDTO.setLongitud(Double.toString(aCentroDeAtencion.getCoordenadas().getY()));
            
            result.setCoordenadas(coordDTO);
        }

        return result;
    }
}
