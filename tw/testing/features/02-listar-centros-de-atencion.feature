# language: es

Característica: Listar Centros de Atención

Antecedentes:
    Dado que existe un sistema de centros de atencion
    Y los siguientes centros de atención han sido registrados: 
    |          Nombre               |           Dirección                     | Localidad     | Provincia | Coordenadas      |
    | Centro Médico Integral        | Calle 9 de Julio 123, Piso 2, Oficina A | Puerto Madryn | Chubut    | -42.765, -65.034 |
    | Centro de Salud Rawson        | Avenida Libertad 456                    | Rawson        | Chubut    | -43.305, -65.102 |
    | Trelew Salud                  | Rivadavia 789, Barrio Centro            | Trelew        | Chubut    | -43.252, -65.308 |
    | Clinica Veterinaria del Golfo | Almirante Brown 456                     | Puerto Madryn | Chubut    | -42.789, -65.021 |
    | Centro Médico Esperanza       | Belgrano 753                            | Trelew        | Chubut    | -43.272, -65.311 |
    | Clinica Rawson                | Mariano Moreno 525                      | Rawson        | Chubut    | -43.310, -65.112 |
    | Centro de Rehabilitación      | Hipólito Yrigoyen 852                   | Puerto Madryn | Chubut    | -42.755, -65.044 |
    | Instituto Médico Patagonia    | San Martín 1025, 1er Piso               | Trelew        | Chubut    | -43.248, -65.301 |
    | Centro Odontológico Rawson    | Gobernador Gallina 789                  | Rawson        | Chubut    | -43.322, -65.123 |
    | Centro Médico del Este        | Avenida Fontana 987                     | Puerto Madryn | Chubut    | -42.777, -65.011 |

Escenario: Listar todos los centros de atención
    Cuando el usuario solicita la lista de centros de atención
    Entonces el sistema responde con status_code 200 y status_text "OK" 
    Y la lista contiene los siguientes centros de atención:
    |          Nombre               |           Dirección                     | Localidad     | Provincia | Coordenadas      |
    | Centro Médico Integral        | Calle 9 de Julio 123, Piso 2, Oficina A | Puerto Madryn | Chubut    | -42.765, -65.034 |
    | Centro de Salud Rawson        | Avenida Libertad 456                    | Rawson        | Chubut    | -43.305, -65.102 |
    | Trelew Salud                  | Rivadavia 789, Barrio Centro            | Trelew        | Chubut    | -43.252, -65.308 |
    | Clinica Veterinaria del Golfo | Almirante Brown 456                     | Puerto Madryn | Chubut    | -42.789, -65.021 |
    | Centro Médico Esperanza       | Belgrano 753                            | Trelew        | Chubut    | -43.272, -65.311 |
    | Clinica Rawson                | Mariano Moreno 525                      | Rawson        | Chubut    | -43.310, -65.112 |
    | Centro de Rehabilitación      | Hipólito Yrigoyen 852                   | Puerto Madryn | Chubut    | -42.755, -65.044 |
    | Instituto Médico Patagonia    | San Martín 1025, 1er Piso               | Trelew        | Chubut    | -43.248, -65.301 |
    | Centro Odontológico Rawson    | Gobernador Gallina 789                  | Rawson        | Chubut    | -43.322, -65.123 |
    | Centro Médico del Este        | Avenida Fontana 987                     | Puerto Madryn | Chubut    | -42.777, -65.011 |



    # {
    #     nombre: "<nombre>",
    #     direccion: "<direccion>",
    #     localidad: "<localidad>",
    #     provincia: "<provincia>",
    #     coordenadas: {
    #         latitud: coordenadas.split(',')[0].trim(),
    #         longitud: coordenadas.split(',')[1].trim()
    #     }
    # }


# Historia de usuario:
#     Como usuario del sistema, quiero ver una lista de Centros de Atención,
#     para poder elegir uno para la asignación de turnos.
# Criterios de aceptación:    
#     1. Se debe devolver una lista de todos los Centros de Atención existentes.
#     2. La lista debe estar paginada para facilitar la navegación.