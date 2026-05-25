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
    public ResponseEntity<Object> create(@RequestBody CentroDeAtencion_DTO aCentroDeAtencion) {
        if (aCentroDeAtencion.getNombre() == null || aCentroDeAtencion.getNombre().trim().isEmpty()) {
            return Response.badRequest("El nombre es requerido");
        }

        if (aCentroDeAtencion.getDireccion() == null || aCentroDeAtencion.getDireccion().trim().isEmpty()) {
            return Response.badRequest("La dirección es requerida");
        }

        if (
        aCentroDeAtencion.getCoordenadas() == null ||
        aCentroDeAtencion.getCoordenadas().getLatitud() == null ||
        aCentroDeAtencion.getCoordenadas().getLongitud() == null  ||
        !esNumeroValido(aCentroDeAtencion.getCoordenadas().getLatitud()) ||
        !esNumeroValido(aCentroDeAtencion.getCoordenadas().getLongitud())) {
            return Response.badRequest("Las coordenadas son inválidas");
        }

        if (this.centroDeAtencion_Svc.existsByNameAndAddress(aCentroDeAtencion.getNombre(), aCentroDeAtencion.getDireccion())) {
            return Response.conflict("Ya existe un centro de atención con ese nombre y dirección");        
        }
        
        if (centroDeAtencion_Svc.existsByAddress(aCentroDeAtencion.getDireccion())) {
            return Response.conflict("Ya existe un centro de atención con esa dirección");
        }
        
        try {
            double latitud = Double.parseDouble(aCentroDeAtencion.getCoordenadas().getLatitud());
            double longitud = Double.parseDouble(aCentroDeAtencion.getCoordenadas().getLongitud());

            if (latitud < -90 || latitud > 90) {
                throw new RuntimeException("Latitud inválida (rango: -90 <= latitud <= 90)");
            }

            if (longitud < -180 || longitud > 180) {
                throw new RuntimeException("Longitud inválida (rango: -180 <= longitud <= 180)");
            }

            Point point = new Point(latitud, longitud);

            CentroDeAtencion centroDeAtencionToSave = new CentroDeAtencion();

            centroDeAtencionToSave.setId(0);
            centroDeAtencionToSave.setNombre(aCentroDeAtencion.getNombre());
            centroDeAtencionToSave.setProvincia(aCentroDeAtencion.getProvincia());
            centroDeAtencionToSave.setLocalidad(aCentroDeAtencion.getLocalidad());
            centroDeAtencionToSave.setDireccion(aCentroDeAtencion.getDireccion());
            centroDeAtencionToSave.setCoordenadas(point);
            centroDeAtencionToSave.setTelefono(aCentroDeAtencion.getTelefono());
            
            centroDeAtencion_Svc.save(centroDeAtencionToSave);

            return Response.ok(null, "Centro de atención creado");
        } catch (Exception e) {
            return Response.badRequest(e.getMessage());
        }
                
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody CentroDeAtencion_DTO aCentroDeAtencion_DTO) {
        if (aCentroDeAtencion_DTO.getId() <= 0) {
            return Response.badRequest(
                aCentroDeAtencion_DTO,
                "Invalid ID"
            );
        }
        if (aCentroDeAtencion_DTO.getNombre() == null || aCentroDeAtencion_DTO.getNombre().trim().isEmpty()) {
            return Response.badRequest("El nombre es requerido");
        }

        if (aCentroDeAtencion_DTO.getDireccion() == null || aCentroDeAtencion_DTO.getDireccion().trim().isEmpty()) {
            return Response.badRequest("La dirección es requerida");
        }

        if (
        aCentroDeAtencion_DTO.getCoordenadas() == null ||
        aCentroDeAtencion_DTO.getCoordenadas().getLatitud() == null ||
        aCentroDeAtencion_DTO.getCoordenadas().getLongitud() == null  ||
        !esNumeroValido(aCentroDeAtencion_DTO.getCoordenadas().getLatitud()) ||
        !esNumeroValido(aCentroDeAtencion_DTO.getCoordenadas().getLongitud())) {
            return Response.badRequest("Las coordenadas son inválidas");
        }

        CentroDeAtencion existing = centroDeAtencion_Svc.findById(aCentroDeAtencion_DTO.getId());

        // NO OP
        boolean mismosDatos =
            existing.getNombre().equals(aCentroDeAtencion_DTO.getNombre()) &&
            existing.getDireccion().equals(aCentroDeAtencion_DTO.getDireccion()) &&
            existing.getLocalidad().equals(aCentroDeAtencion_DTO.getLocalidad()) &&
            existing.getProvincia().equals(aCentroDeAtencion_DTO.getProvincia()) &&
            existing.getCoordenadas().getX() == Double.parseDouble(aCentroDeAtencion_DTO.getCoordenadas().getLatitud()) &&
            existing.getCoordenadas().getY() == Double.parseDouble(aCentroDeAtencion_DTO.getCoordenadas().getLongitud());

        if (mismosDatos) {
            return Response.conflict("Ya existe un centro de atención con ese nombre y dirección");
        }

        CentroDeAtencion updatedCentroDeAtencion = centroDeAtencion_Svc.findById(aCentroDeAtencion_DTO.getId());
        if (updatedCentroDeAtencion == null) {
           return Response.badRequest("El centro de atención no existe");
        }

        CentroDeAtencion centerFoundByNameAndAddres = this.centroDeAtencion_Svc.findByNameAndAddress(aCentroDeAtencion_DTO.getNombre(), aCentroDeAtencion_DTO.getDireccion());
        if (centerFoundByNameAndAddres != null && centerFoundByNameAndAddres.getId() != updatedCentroDeAtencion.getId()) {
            return Response.conflict("Ya existe un centro de atención con ese nombre y dirección");        
        }
        
        CentroDeAtencion centerFoundByAddress = this.centroDeAtencion_Svc.findByAddress(aCentroDeAtencion_DTO.getDireccion());
        if (centerFoundByAddress!= null && centerFoundByAddress.getId() != updatedCentroDeAtencion.getId()) {
            return Response.conflict("Ya existe un centro de atención con esa dirección");
        }

        updatedCentroDeAtencion.setNombre(aCentroDeAtencion_DTO.getNombre());
        updatedCentroDeAtencion.setDireccion(aCentroDeAtencion_DTO.getDireccion());
        updatedCentroDeAtencion.setProvincia(aCentroDeAtencion_DTO.getProvincia());
        updatedCentroDeAtencion.setLocalidad(aCentroDeAtencion_DTO.getLocalidad());
        updatedCentroDeAtencion.setTelefono(aCentroDeAtencion_DTO.getTelefono());

        double latitud = Double.parseDouble(aCentroDeAtencion_DTO.getCoordenadas().getLatitud());
        double longitud = Double.parseDouble(aCentroDeAtencion_DTO.getCoordenadas().getLongitud());

        if (latitud < -90 || latitud > 90) {
            throw new RuntimeException("Latitud inválida (rango: -90 <= latitud <= 90)");
        }

        if (longitud < -180 || longitud > 180) {
            throw new RuntimeException("Longitud inválida (rango: -180 <= longitud <= 180)");
        }

        Point point = new Point(latitud, longitud);

        updatedCentroDeAtencion.setCoordenadas(point);
        
        return Response.ok(
            centroDeAtencion_Svc.save(updatedCentroDeAtencion),
            "Centro de atención modificado"
        );
    }


    @DeleteMapping("/test/delete-all")
    public void deleteAll() {
        centroDeAtencion_Svc.deleteAll();
    }

    private boolean esNumeroValido(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
