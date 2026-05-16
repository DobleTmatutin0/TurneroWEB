# language: es

Característica: Eliminar Especialidades

Esquema del escenario: Eliminar una especialidad exitosamente
    Dado que la especialidad "<nombre>" existe en el sistema
    Cuando el administrador elimina la especialidad "<nombre>"
    Entonces el sistema responde con el codigo: <status_code> y mensaje: "<status_text>"

    Ejemplos:
    | nombre                 | status_code | status_text                        |
    | Terapia Intensiva      | 200         | Especialidad eliminada exitosamente |              
    | Medicina Estética      | 200         | Especialidad eliminada exitosamente |
    | Medicina del Dolor     | 200         | Especialidad eliminada exitosamente |
    | Cirugía Reconstructiva | 200         | Especialidad eliminada exitosamente |
    | Medicina Paliativa     | 200         | Especialidad eliminada exitosamente |



# Historias de Usuario:
#    Como administrador del sistema.
#    Quiero poder eliminar una especialidad médica del sistema.
#    Para mantener una lista precisa y evitar información obsoleta o redundante.
# Criterios de aceptación:
#    1. Se debe permitir eliminar una especialidad, solo si no está asignada a médicos o consultorios.
