package unpsjb.labprog.backend.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import unpsjb.labprog.backend.model.Consultorio;

@Repository

public interface Consultorio_Repository extends JpaRepository<Consultorio, Integer> {
    // all default methods

    @Query(
        "SELECT c " +
        "FROM Consultorio c " +
        "WHERE c.eliminado = false"
    )
    public List<Consultorio> findAllNotEliminado();

    @Query(
        "SELECT c " +
        "FROM Consultorio c " +
        "WHERE " +
            "c.id = id " +
            "AND " +
            "c.eliminado = false"
    )
    public Consultorio findByIdAndNotEliminado(Integer id);

    @Query(
        "SELECT c " +
        "FROM Consultorio c " +
        "WHERE " +
            "LOWER(c.nombre) = LOWER(nombre) " +
            "AND " +
            "c.eliminado = false"
    )
    public Consultorio findByNameAndNotEliminado(String aName);
}
