package unpsjb.labprog.backend.presenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import unpsjb.labprog.backend.business.service.CentroDeAtencion_Service;
import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.Response;

@Controller
@RequestMapping("centros-de-atencion")

public class CentroDeAtencion_Presenter {

    @Autowired
    private CentroDeAtencion_Service centroDeAtencion_Svc;

    @PostMapping("")
    public ResponseEntity<Object> agregarConsultorio(@RequestBody CentroDeAtencion_DTO aCentroDeAtencion) {
        if (aCentroDeAtencion.getNombre() == null || aCentroDeAtencion.getNombre().trim().isEmpty()) {
            return Response.badRequest("El nombre es requerido");
        }

        if (aCentroDeAtencion.getDireccion() == null || aCentroDeAtencion.getDireccion().trim().isEmpty()) {
            return Response.badRequest("La dirección es requerida");
        }

        if (aCentroDeAtencion.getCoordenadas() == null || aCentroDeAtencion.getCoordenadas().getClass() != Double.class) {
            return Response.badRequest("Las coordenadas son inválidas");
        }

        if (this.centroDeAtencion_Svc.findByNameAndAddress(aCentroDeAtencion.getNombre(), aCentroDeAtencion.getDireccion())) {
            return Response.conflict("Ya existe un centro de atención con ese nombre y dirección");        
        }
        
        if (aCentroDeAtencion.getId() != 0) {
            return Response.badRequest("Un centro de atencion nuevo no debe tener un ID asignado de forma manual");
        }
        
        return Response.ok(centroDeAtencion_Svc.save(aCentroDeAtencion), "Centro de atención creado");            
                
    }

    

}
