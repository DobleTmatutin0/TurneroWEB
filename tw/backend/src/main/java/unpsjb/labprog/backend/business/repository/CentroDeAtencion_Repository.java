package unpsjb.labprog.backend.business.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unpsjb.labprog.backend.model.CentroDeAtencion;

@Repository

public interface CentroDeAtencion_Repository extends JpaRepository<CentroDeAtencion, Integer> {
    // all default methods

    @Query(
        "SELECT COUNT(ca) > 0 " +
        "FROM CentroDeAtencion ca " +
        "WHERE " +
            "LOWER(ca.nombre) = LOWER(?1) " +
            "AND " +
            "LOWER(ca.direccion) = LOWER(?2)"
    )
    public boolean existsByNameAndAddress(String aName, String anAddress);

    @Query(
        "SELECT COUNT(ca) > 0 " +
        "FROM CentroDeAtencion ca " +
        "WHERE " +
            "LOWER(ca.direccion) = LOWER(?1)"
    )
    public boolean existsByAddress(String anAddress);
}
