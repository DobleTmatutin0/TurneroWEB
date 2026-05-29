package unpsjb.labprog.backend.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import unpsjb.labprog.backend.model.Consultorio;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class CentroDeAtencion_DTO {

    private Integer id;
    private String nombre;
    private String direccion;
    private String provincia;
    private String localidad;
    private String telefono;
    private Coordenadas_DTO coordenadas;
    private  List<Consultorio> consultorios;

}
