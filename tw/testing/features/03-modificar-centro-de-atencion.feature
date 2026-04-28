# language: es

Característica: Modificar Centro de Atención

Antecedentes:
    Dado que existe un sist de centros de atencion
    Y los siguientes centros de atención han sido registrados:
    |        Nombre          |          Dirección                       | Localidad     | Provincia | Coordenadas      |
    | Centro Médico Integral | Calle 9 de Julio 123, Piso 2, Oficina A  | Puerto Madryn | Chubut    | -42.765, -65.034 |
    | Centro de Salud Rawson | Avenida Libertad 456                     | Rawson        | Chubut    | -43.305, -65.102 |
    | Trelew Salud           | Rivadavia 789, Barrio Centro             | Trelew        | Chubut    | -43.252, -65.308 |

Esquema del escenario: Intentar modificar centro de atención con conflictos
    Cuando el administrador modifica los datos del centro de atención "<NombreOriginal>" con los siguientes atributos:

    nombre "<NombreNuevo>"
    dirección "<DirecciónNueva>"
    localidad "<LocalidadNueva>"
    provincia "<ProvinciaNueva>"
    coordenadas "<CoordenadasNuevas>"

    Entonces el sistema responde con <status_code> y "<status_text>"

    Ejemplos: Modificaciones con conflictos
    | NombreOriginal         | NombreNuevo            | DirecciónNueva               | LocalidadNueva | ProvinciaNueva | CoordenadasNuevas | status_code | status_text                                                |
    | Centro Médico Integral | Centro Médico Integral | Avenida Libertad 456         | Puerto Madryn  | Chubut         | -42.765, -65.034  | 409         | Ya existe un centro de atención con esa dirección          | # Conflicto: Dirección duplicada
    | Centro de Salud Rawson | Centro de Salud Rawson | Avenida Libertad 456         | Rawson         | Chubut         | -43.305, -65.102  | 409         | Ya existe un centro de atención con ese nombre y dirección | # Conflicto: Nombre y dirección duplicados
    | Trelew Salud           | Trelew Salud           | Rivadavia 789, Barrio Centro | Trelew         | Chubut         | abc, xyz          | 400         | Las coordenadas son inválidas                              | # Conflicto: Coordenadas inválidas

Esquema del escenario: Modificar centro de atención exitosamente
    Cuando el administrador modifica los datos del centro de atención "<NombreOriginal>" con los siguientes atributos:
    
    nombre "<NombreNuevo>"
    dirección "<DirecciónNueva>"
    localidad "<LocalidadNueva>"
    provincia "<ProvinciaNueva>"
    coordenadas "<CoordenadasNuevas>"

    Entonces el sistema responde con <status_code> y "<status_text>"

    Ejemplos: Modificaciones exitosas
    | NombreOriginal         | NombreNuevo                    | DirecciónNueva                          | LocalidadNueva | ProvinciaNueva | CoordenadasNuevas | status_code | status_text                   |
    | Centro Médico Integral | Centro Médico Integral         | Calle 9 de Julio 150, Piso 3, Oficina B | Puerto Madryn  | Chubut         | -42.765, -65.034  | 200         | Centro de atención modificado | # Dirección modificada
    | Centro de Salud Rawson | Centro de Salud Dr. Juan Perez | Avenida Libertad 456                    | Rawson         | Chubut         | -43.305, -65.102  | 200         | Centro de atención modificado | # Nombre modificado
    | Trelew Salud           | Trelew Salud                   | Rivadavia 789, Barrio Centro            | Trelew         | Chubut         | -43.255, -65.310  | 200         | Centro de atención modificado | # Coordenadas modificadas


# Historia de Usuario:
#     Como Administrador del sistema,
#     quiero modificar la información de un centro de atención existente (nombre, dirección, etc.)
#     para mantener los datos actualizados y reflejar los cambios en la organización..
# Criterios de aceptación:
#     1. Se debe permitir modificar los campos de nombre, dirección y coordenadas.    
#     2. Si el Centro de Atención ya existe con los mismos datos que se desean guardar, se debe rechazar la edición.