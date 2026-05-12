package unpsjb.labprog.backend.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.backend.model.Especialidad;

@Repository

public interface Especialidad_Repository extends JpaRepository<Especialidad, Integer> {
    // all default methods
    
    @Query(
        "SELECT COUNT(e) > o " +
        "FROM Especialidad e " +
        "WHERE " +
            "LOWER(e.nombre) = LOWER(?1)"
    )
    public boolean existsByName(String aName);
     
    @Query("SELECT e FROM Especialidad e WHERE LOWER(e.nombre) = LOWER(?1)")
    public Especialidad findByName(String aName);


} 
