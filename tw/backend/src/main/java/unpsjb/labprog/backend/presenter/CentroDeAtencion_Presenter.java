package unpsjb.labprog.backend.presenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import unpsjb.labprog.backend.model.CentroDeAtencion;
import unpsjb.labprog.backend.business.service.CentroDeAtencion_Service;
import unpsjb.labprog.backend.Response;

@Controller
@RequestMapping("CentroDeAtencion")

public class CentroDeAtencion_Presenter {

    @Autowired
    private CentroDeAtencion_Service centroDeAtencion_Svc;

    @PostMapping("")
    public ResponseEntity<Object> agregarConsultorio(CentroDeAtencion aCentroDeAtencion) {
        if (centroDeAtencion_Svc.findByName(aCentroDeAtencion.getNombre())) {
            return Response.error("Ya existe un centro de atención con ese nombre y dirección");
        }

        if (aCentroDeAtencion.getId() != 0) {
            return Response.error("Un centro de atencion nuevo no debe tener un ID asignado de forma manual");
        }
        return Response.ok(centroDeAtencion_Svc.save(aCentroDeAtencion), "Centro de atención creado");
    }

    

}
