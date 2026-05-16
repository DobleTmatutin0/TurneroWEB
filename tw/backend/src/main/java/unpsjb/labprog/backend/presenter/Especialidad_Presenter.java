package unpsjb.labprog.backend.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import unpsjb.labprog.backend.Response;
import unpsjb.labprog.backend.business.service.Especialidad_Service;
import unpsjb.labprog.backend.model.Especialidad;

@Controller
@RequestMapping("especialidad")

public class Especialidad_Presenter {
    @Autowired
    private Especialidad_Service especialidad_Svc;
    
    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        return Response.ok(especialidad_Svc.findAll());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Object> findByName(@PathVariable("nombre") String aName) {
        Especialidad result = especialidad_Svc.findByName(aName);

        if (result != null) {
            return Response.ok(result);
        }
        return Response.notFound();
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Especialidad anEspecialidad) {
        if (anEspecialidad.getNombre() == null || anEspecialidad.getNombre().trim().isEmpty()) {
            return Response.conflict("El nombre de la especialidad es obligatorio");
        }
        if (anEspecialidad.getDescripcion() == null || anEspecialidad.getDescripcion().trim().isEmpty()) {
            return Response.conflict("La descripción de la especialidad es obligatoria");
        }
        if (especialidad_Svc.existsByName(anEspecialidad.getNombre())) {
            return Response.conflict("Ya existe una especialidad con ese nombre");
        }

        especialidad_Svc.save(anEspecialidad);
        return Response.ok(null, "Especialidad creada exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Especialidad anEspecialidad) {
        if (anEspecialidad.getNombre() == null || anEspecialidad.getNombre().trim().isEmpty()) {
            return Response.conflict("El nombre de la especialidad es obligatorio");
        }
        if (anEspecialidad.getDescripcion() == null || anEspecialidad.getDescripcion().trim().isEmpty()) {
            return Response.conflict("La descripción de la especialidad es obligatoria");
        }
        if (!especialidad_Svc.existsByName(anEspecialidad.getNombre())) {
            return Response.conflict("El nombre de la especialidad ya está en uso");
        }

        especialidad_Svc.save(anEspecialidad);
        return Response.ok(null, "Especialidad editada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        especialidad_Svc.delete(id);
        return Response.ok("Especialidad eliminada exitosamente");
    }
}
