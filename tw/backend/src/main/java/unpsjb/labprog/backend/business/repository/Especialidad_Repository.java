package unpsjb.labprog.backend.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import unpsjb.labprog.backend.model.Especialidad;

@Repository

public interface Especialidad_Repository extends JpaRepository<Especialidad, Integer> {
    // all default methods
    
    @Query(
        "SELECT COUNT(e) > 0 " +
        "FROM Especialidad e " +
        "WHERE " +
            "LOWER(e.nombre) = LOWER(?1)" +
            "AND e.eliminado = false"
    )
    public boolean existsByName(String aName);

    @Query(
        "SELECT e " +
        "FROM Especialidad e " +
        "WHERE e.eliminado = false"
    )
    public List<Especialidad> findAllNotEliminado();

    @Query(
        "SELECT e " +
        "FROM Especialidad e " +
        "WHERE " +
            "LOWER(e.nombre) = LOWER(?1) " +
            "AND e.eliminado = false"
    )
    public Especialidad findByNameAndEliminadoFalse(String aName);

    @Query(
        "SELECT e " +
        "FROM Especialidad e " +
        "WHERE " +
            "e.id = ?1 " +
            "AND e.eliminado = false"
    )
    public Especialidad findByIdAndEliminadoFalse(int id);
} 
