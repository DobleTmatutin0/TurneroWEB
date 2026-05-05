# language: es

Característica: Modificar Especialidades

Esquema del escenario: Modificar una especialidad exitosamente
    Dado que la especialidad "<nombre_original>" existe en el sistema con la descripción "<descripcion_original>"
    Cuando el administrador edita la especialidad "<nombre_original>" cambiando su nombre a "<nombre_nuevo>" y su descripción a "<descripcion_nueva>"
    Entonces el sistema responde con el codigo: <status_code> y el mensaje: "<status_text>"

    Ejemplos:
    | nombre_original   | descripcion_original                         | nombre_nuevo     | descripcion_nueva                           | status_code | status_text                           |
    | Cardiología      | Diagnóstico y tratamiento de enfermedades del corazón y el sistema circulatorio.              | Cardiología      | Especialidad avanzada en cardiología.       | 200         | Especialidad editada exitosamente    |
    | Pediatría        | Atención médica integral de niños y adolescentes                   | Pediatría        | Atención integral de la salud infantil     | 200         | Especialidad editada exitosamente    |
    | Neurología       | Diagnóstico y tratamiento de enfermedades del sistema nervioso.                 | Neurociencia     | Diagnóstico avanzado en neurociencia       | 200         | Especialidad editada exitosamente    |

Esquema del escenario: Intentar modificar una especialidad con un nombre duplicado
    Dado que la especialidad "<nombre_original>" existe en el sistema
    Y otra especialidad con el nombre "<nombre_existente>" ya está registrada
    Cuando el administrador intenta cambiar el nombre de "<nombre_original>" a "<nombre_existente>"
    Entonces el sistema responde con el codigo: <status_code>  y el mensaje: "<status_text>"

    Ejemplos:
    | nombre_original | nombre_existente | status_code | status_text                                 |
    | Cardiología     | Pediatría        | 409         | El nombre de la especialidad ya está en uso |
    | Neurología      | Ginecología      | 409         | El nombre de la especialidad ya está en uso |



# Historias de Usuario:
#    Como administrador del sistema.
#    Quiero poder modificar el nombre y/o la descripción de una especialidad existente.
#    Para mantener actualizada la información de las especialidades médicas en el sistema.
# Criterios de aceptación:
#    Se debe permitir modificar una especialidad existente, siempre que el nuevo nombre no duplique otro existente.
#    En caso de error (nombre duplicado o datos faltantes), el sistema debe devolver un mensaje de error claro.
