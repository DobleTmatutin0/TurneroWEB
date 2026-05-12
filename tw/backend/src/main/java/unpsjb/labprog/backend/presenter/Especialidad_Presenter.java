package unpsjb.labprog.backend.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Especialidad aEspecialidad) {
        if (aEspecialidad.getNombre() == null || aEspecialidad.getNombre().trim().isEmpty()) {
            return Response.conflict("El nombre de la especialidad es obligatorio");
        }
        if (aEspecialidad.getDescripcion() == null || aEspecialidad.getDescripcion().trim().isEmpty()) {
            return Response.conflict("La descripción de la especialidad es obligatoria");
        }
        if (!especialidad_Svc.existsByName(aEspecialidad.getNombre())) {
            return Response.conflict("Ya existe una especialidad con ese nombre");
        }

        return Response.ok(null, "Especialidad creada exitosamente");
    }

}
