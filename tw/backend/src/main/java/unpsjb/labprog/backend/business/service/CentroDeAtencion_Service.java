package unpsjb.labprog.backend.business.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unpsjb.labprog.backend.business.repository.CentroDeAtencion_Repository;
import unpsjb.labprog.backend.dto.CentroDeAtencion_DTO;
import unpsjb.labprog.backend.mapper.CentroDeAtencion_Mapper;
import unpsjb.labprog.backend.model.CentroDeAtencion;
import unpsjb.labprog.backend.model.Consultorio;
import unpsjb.labprog.backend.exception.ConflictException;
import unpsjb.labprog.backend.exception.InvalidDataException;
import unpsjb.labprog.backend.exception.NoOperationException;
import unpsjb.labprog.backend.exception.NotFoundException;

@Service

public class CentroDeAtencion_Service {
    
    @Autowired
    private CentroDeAtencion_Repository centroDeAtencion_Repo;

    public List<CentroDeAtencion_DTO>findAll() {
        List<CentroDeAtencion_DTO> result = new ArrayList<>();
        centroDeAtencion_Repo.findAll().forEach(centro -> result.add(CentroDeAtencion_Mapper.toDTO(centro)));
        return result;
    }
    
    public CentroDeAtencion findById(Integer id) {
        return centroDeAtencion_Repo.findById(id).get();
    }

    public CentroDeAtencion findByName(String aName) {
        return centroDeAtencion_Repo.findByName(aName);
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

    @Transactional
    public CentroDeAtencion save(CentroDeAtencion_DTO aCentroDeAtencionDTO) {
        this.validateBaseFields(aCentroDeAtencionDTO);
        if (aCentroDeAtencionDTO.getId() == null || aCentroDeAtencionDTO.getId() == 0) {
            this.validateDuplicadosCreate(aCentroDeAtencionDTO);
            return centroDeAtencion_Repo.save(this.toEntity(aCentroDeAtencionDTO));
        }
        if (aCentroDeAtencionDTO.getId() > 0) {
            CentroDeAtencion aCentroDeAtencionToUpdate = this.findById(aCentroDeAtencionDTO.getId());

            this.validateNoOperationOnUpdate(aCentroDeAtencionDTO, aCentroDeAtencionToUpdate);
            this.validateDuplicadosUpdate(aCentroDeAtencionDTO, aCentroDeAtencionToUpdate);
            return centroDeAtencion_Repo.save(this.updateEntityFields(aCentroDeAtencionDTO, aCentroDeAtencionToUpdate));            
        }
        
        throw new RuntimeException("Invalid ID");
    }

    @Transactional
    public void deleteAll() {
        centroDeAtencion_Repo.deleteAll();
    }

    public List<Consultorio> getConsultorios(String aCentroName) {
        CentroDeAtencion centro = this.findByName(aCentroName);
        return centro.getConsultorios();
    }



    private void validateBaseFields(CentroDeAtencion_DTO aCentroDeAtencionDTO) {
        if (aCentroDeAtencionDTO.getNombre() == null || aCentroDeAtencionDTO.getNombre().trim().isEmpty()) {
            throw new InvalidDataException("El nombre es requerido");
        }

        if (aCentroDeAtencionDTO.getDireccion() == null || aCentroDeAtencionDTO.getDireccion().trim().isEmpty()) {
            throw new InvalidDataException("La dirección es requerida");
        }

        if (
            aCentroDeAtencionDTO.getCoordenadas() == null ||
            aCentroDeAtencionDTO.getCoordenadas().getLatitud() == null ||
            aCentroDeAtencionDTO.getCoordenadas().getLongitud() == null  ||
            !esNumeroValido(aCentroDeAtencionDTO.getCoordenadas().getLatitud()) ||
            !esNumeroValido(aCentroDeAtencionDTO.getCoordenadas().getLongitud())) {
            throw new InvalidDataException("Las coordenadas son inválidas");
        }
        double latitud = Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLatitud());
        double longitud = Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLongitud());

        if (latitud < -90 || latitud > 90) {
            throw new RuntimeException("Latitud inválida (rango: -90 <= latitud <= 90)");
        }

        if (longitud < -180 || longitud > 180) {
            throw new RuntimeException("Longitud inválida (rango: -180 <= longitud <= 180)");
        }
        for (Consultorio consultorio : aCentroDeAtencionDTO.getConsultorios()) {
            this.validateConsultorio(consultorio);
        } 
    }

    private void validateDuplicadosCreate(CentroDeAtencion_DTO aCentroDeAtencionDTO) {

        if (this.existsByNameAndAddress(aCentroDeAtencionDTO.getNombre(), aCentroDeAtencionDTO.getDireccion())) {
            throw new RuntimeException("Ya existe un centro de atención con ese nombre y dirección");        
        }
        
        if (this.existsByAddress(aCentroDeAtencionDTO.getDireccion())) {
            throw new RuntimeException("Ya existe un centro de atención con esa dirección");
        }

        // checkeo de consultorios repetidos dentro de un centro
        Set<Integer> numerosConsultorios = new HashSet<>();
        Set<String> nombresConsultorios = new HashSet<>();
        for(Consultorio consultorio : aCentroDeAtencionDTO.getConsultorios()) {
            if (!numerosConsultorios.add(consultorio.getNumero())) {
                throw new ConflictException("El número de consultorio ya está registrado");
            }
            if (!nombresConsultorios.add(consultorio.getNombre())) {
                throw new ConflictException("El nombre del consultorio ya está registrado");
            } 
        }
        
    }

    private CentroDeAtencion toEntity(CentroDeAtencion_DTO aCentroDeAtencionDTO) {

        double latitud = Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLatitud());
        double longitud = Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLongitud());
        Point point = new Point(latitud, longitud);

        CentroDeAtencion centroDeAtencionToSave = new CentroDeAtencion();

        centroDeAtencionToSave.setNombre(aCentroDeAtencionDTO.getNombre());
        centroDeAtencionToSave.setProvincia(aCentroDeAtencionDTO.getProvincia());
        centroDeAtencionToSave.setLocalidad(aCentroDeAtencionDTO.getLocalidad());
        centroDeAtencionToSave.setDireccion(aCentroDeAtencionDTO.getDireccion());
        centroDeAtencionToSave.setCoordenadas(point);
        centroDeAtencionToSave.setTelefono(aCentroDeAtencionDTO.getTelefono());

        return centroDeAtencionToSave;
    }

    private void validateDuplicadosUpdate(CentroDeAtencion_DTO aCentroDeAtencionDTO, CentroDeAtencion aCentroToUpdate) {

        if (aCentroToUpdate == null) {
           throw new NotFoundException("El centro de atención no existe");
        }

        CentroDeAtencion centerFoundByNameAndAddres = this.findByNameAndAddress(aCentroDeAtencionDTO.getNombre(), aCentroDeAtencionDTO.getDireccion());
        if (centerFoundByNameAndAddres != null && centerFoundByNameAndAddres.getId() != aCentroToUpdate.getId()) {
            throw new RuntimeException("Ya existe un centro de atención con ese nombre y dirección");        
        }
        
        CentroDeAtencion centerFoundByAddress = this.findByAddress(aCentroDeAtencionDTO.getDireccion());
        if (centerFoundByAddress!= null && centerFoundByAddress.getId() != aCentroToUpdate.getId()) {
            throw new RuntimeException("Ya existe un centro de atención con esa dirección");
        }
    }

    private void validateNoOperationOnUpdate(CentroDeAtencion_DTO aCentroDeAtencionDTO, CentroDeAtencion aCentroToUpdate) {
        // checkea que el objeto que se va a actualizar realmente este cambiando algo, si no se cambia nada
        // no hay operacion real NoOp (no operation)
        boolean mismosDatos =
            aCentroToUpdate.getNombre().equals(aCentroDeAtencionDTO.getNombre()) &&
            aCentroToUpdate.getDireccion().equals(aCentroDeAtencionDTO.getDireccion()) &&
            aCentroToUpdate.getLocalidad().equals(aCentroDeAtencionDTO.getLocalidad()) &&
            aCentroToUpdate.getProvincia().equals(aCentroDeAtencionDTO.getProvincia()) &&
            aCentroToUpdate.getCoordenadas().getX() == Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLatitud()) &&
            aCentroToUpdate.getCoordenadas().getY() == Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLongitud());
        if (mismosDatos) {
            throw new NoOperationException("Ya existe un centro de atención con ese nombre y dirección");
        }
    }

    private CentroDeAtencion updateEntityFields(CentroDeAtencion_DTO aCentroDeAtencionDTO, CentroDeAtencion aCentroToUpdate) {

        aCentroToUpdate.setNombre(aCentroDeAtencionDTO.getNombre());
        aCentroToUpdate.setDireccion(aCentroDeAtencionDTO.getDireccion());
        aCentroToUpdate.setProvincia(aCentroDeAtencionDTO.getProvincia());
        aCentroToUpdate.setLocalidad(aCentroDeAtencionDTO.getLocalidad());
        aCentroToUpdate.setTelefono(aCentroDeAtencionDTO.getTelefono());

        Point point = new Point(
            Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLatitud()),
            Double.parseDouble(aCentroDeAtencionDTO.getCoordenadas().getLongitud())
        );

        aCentroToUpdate.setCoordenadas(point);
        
        return aCentroToUpdate;
    }

    private boolean esNumeroValido(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validateConsultorio(Consultorio aConsultorio) {
        if (aConsultorio.getId() <= 0) {
            throw new InvalidDataException("Invalid ID");
        }
        if (aConsultorio.getNombre() == null || aConsultorio.getNombre().trim().equals("")) {
            throw new InvalidDataException("El nombre del consultorio es obligatorio");
        }
        if (!nombreValido(aConsultorio.getNombre())) {
           throw new InvalidDataException("El nombre del consultorio contiene caracteres inválidos");
        }
        if (aConsultorio.getNumero() <= 0) {
            throw new InvalidDataException("El numero del consultorio no es valido");
        }
    }

    private void validateConsultoriosRepetidos(
        List<Consultorio> someConsultoriosFromCenterDTO,
        List<Consultorio> someConsultoriosFromDbCenter
    ) {
        for (Consultorio consultorioFromDTO: someConsultoriosFromCenterDTO) {
            boolean numeroDuplicado = someConsultoriosFromDbCenter.stream().anyMatch(consultorioFromDb ->
                consultorioFromDb.getNumero().equals(consultorioFromDTO.getNumero())
                &&
                consultorioFromDb.getId() != consultorioFromDTO.getId()
            );
            if (numeroDuplicado) {
                throw new ConflictException("El número de consultorio ya está registrado");
            }

            boolean nombreDuplicado = someConsultoriosFromDbCenter.stream().anyMatch(consultorioFromDb ->
                consultorioFromDb.getNombre().equalsIgnoreCase(consultorioFromDTO.getNombre())
                &&
                consultorioFromDb.getId() != consultorioFromDTO.getId()
            );
            if (nombreDuplicado) {
                throw new ConflictException("El nombre del consultorio ya está registrado");
            }
        }
    }

    private boolean nombreValido(String nombre) {
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }
    //  Consultorio #Especial  | 409         | El nombre del consultorio contiene caracteres no permitidos |
}