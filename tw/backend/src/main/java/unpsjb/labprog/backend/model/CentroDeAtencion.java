package unpsjb.labprog.backend.model;

import org.springframework.data.geo.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity

@Getter
@Setter
@NoArgsConstructor

@Table(
    name = "centro_de_atencion",
    uniqueConstraints =
        @UniqueConstraint(columnNames = {"nombre", "direccion"})
)

public class CentroDeAtencion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(unique = true, nullable = false)
    @NotNull(message = "El nombre es requerido")
    private String nombre;
    
    @Column(unique = true, nullable = false)
    @NotNull(message = "La dirección es requerida")
    private String direccion;

    private String localidad;

    @Column(unique = true)
    private String telefono;

    // Buscar como validar coordenadas.
    private Point coordenadas;

}