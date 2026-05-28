package unpsjb.labprog.backend.presenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;

import unpsjb.labprog.backend.business.service.CentroDeAtencion_Service;
import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.exception.InvalidDataException;
import unpsjb.labprog.backend.exception.NoOperationException;
import unpsjb.labprog.backend.exception.NotFoundException;
import unpsjb.labprog.backend.model.CentroDeAtencion;
import unpsjb.labprog.backend.Response;

@Controller
@RequestMapping("centros-de-atencion")

public class CentroDeAtencion_Presenter {

    @Autowired
    private CentroDeAtencion_Service centroDeAtencion_Svc;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return Response.ok(centroDeAtencion_Svc.findAll());
    }

    @GetMapping("/nombre")
    public ResponseEntity<Object> findByName(@PathVariable("nombre") String aName) {
        return Response.ok(centroDeAtencion_Svc.findByName(aName));
    }

    @GetMapping("/{nombre}/consultorios")
    public ResponseEntity<Object> getConsultorios(@PathVariable("nombre") String aName) {

        return Response.ok(centroDeAtencion_Svc.getConsultorios(aName));
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody CentroDeAtencion_DTO aCentroDeAtencionDTO) {
        try {
            centroDeAtencion_Svc.save(aCentroDeAtencionDTO);
            return Response.ok(null, "Centro de atención creado");
        }
        catch (InvalidDataException e) {
            return Response.badRequest(e.getMessage());
        }
        catch (Exception e) {
            return Response.conflict(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody CentroDeAtencion_DTO aCentroDeAtencion_DTO) {
        try {
            centroDeAtencion_Svc.save(aCentroDeAtencion_DTO);
            return Response.ok(null, "Centro de atención modificado");    
        }
        catch (NotFoundException e) {
            return Response.notFound(e.getMessage());
        }
        catch (InvalidDataException e) {
            return Response.badRequest(e.getMessage());
        }
        catch (NoOperationException e) {
            return Response.conflict(e.getMessage());
        }
        catch (Exception e) {
            return Response.conflict(e.getMessage());
        }
                
    }


    @DeleteMapping("/test/delete-all")
    public void deleteAll() {
        centroDeAtencion_Svc.deleteAll();
    }

}
