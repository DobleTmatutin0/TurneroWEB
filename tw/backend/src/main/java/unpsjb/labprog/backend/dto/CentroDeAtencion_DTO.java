package unpsjb.labprog.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class CentroDeAtencion_DTO {
    
    private int id;    
    private String nombre;
    private String direccion;
    private String localidad;
    private String telefono;
    private Coordenadas_DTO coordenadas;

}
