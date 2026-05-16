# language: es

Característica: Crear Especialidad

Esquema del escenario: Crear una especialidad exitosamente
    Cuando el administrador crea una especialidad con el nombre "<nombre>" y la descripción "<descripcion>"
    Entonces rta-test-04: el sistema responde con codigo: <status_code> y mensaje: "<status_text>"

    Ejemplos:
    |        nombre             |                      descripcion                                                        | status_code |            status_text           |
    | Alergia e Inmunología     | Diagnóstico y tratamiento de enfermedades alérgicas e inmunológicas.                    | 200         | Especialidad creada exitosamente |
    | Anatomía Patológica       | Estudio de tejidos y células para diagnosticar enfermedades.                            | 200         | Especialidad creada exitosamente |
    | Anestesiología            | Administración de anestesia para procedimientos quirúrgicos y control del dolor.        | 200         | Especialidad creada exitosamente |
    | Angiología                | Diagnóstico y tratamiento de enfermedades de los vasos sanguíneos y linfáticos.         | 200         | Especialidad creada exitosamente |
    | Cardiología               | Diagnóstico y tratamiento de enfermedades del corazón y el sistema circulatorio.        | 200         | Especialidad creada exitosamente |
    | Cirugía Cardiovascular    | Intervenciones quirúrgicas del corazón y grandes vasos sanguíneos.                      | 200         | Especialidad creada exitosamente |
    | Cirugía General           | Tratamiento quirúrgico de diversas patologías en órganos internos.                      | 200         | Especialidad creada exitosamente |
    | Cirugía Maxilofacial      | Cirugía de la cara, mandíbula y estructuras asociadas.                                  | 200         | Especialidad creada exitosamente |
    | Cirugía Plástica          | Reconstrucción, reparación y embellecimiento de tejidos y estructuras del cuerpo.       | 200         | Especialidad creada exitosamente |
    | Cirugía Torácica          | Cirugía del tórax, pulmones y otras estructuras torácicas.                              | 200         | Especialidad creada exitosamente |
    | Cirugía Vascular          | Diagnóstico y tratamiento quirúrgico de enfermedades de los vasos sanguíneos.           | 200         | Especialidad creada exitosamente |
    | Clínica Médica            | Atención integral de enfermedades médicas en adultos.                                   | 200         | Especialidad creada exitosamente |
    | Dermatología              | Diagnóstico y tratamiento de enfermedades de la piel, cabello y uñas.                   | 200         | Especialidad creada exitosamente |
    | Diabetología              | Tratamiento y control de la diabetes y sus complicaciones.                              | 200         | Especialidad creada exitosamente |
    | Emergentología            | Atención médica de urgencias y emergencias.                                             | 200         | Especialidad creada exitosamente |
    | Endocrinología            | Diagnóstico y tratamiento de trastornos hormonales.                                     | 200         | Especialidad creada exitosamente |
    | Endoscopía Digestiva      | Exploración y tratamiento de enfermedades del tracto digestivo mediante endoscopía.     | 200         | Especialidad creada exitosamente |
    | Fisiatría                 | Rehabilitación de personas con discapacidades físicas o motoras.                        | 200         | Especialidad creada exitosamente |
    | Gastroenterología         | Diagnóstico y tratamiento de enfermedades del sistema digestivo.                        | 200         | Especialidad creada exitosamente |
    

Esquema del escenario: Intentar crear una especialidad con nombre duplicado
    Cuando el administrador crea una especialidad con el nombre "<nombre>" y la descripción "<descripcion>"
    Entonces rta-test-04: el sistema responde con codigo: <status_code> y mensaje: "<status_text>"

    Ejemplos:
    | nombre            | descripcion                                  | status_code |              status_text                         |
    | Cardiología       | Especialidad que estudia el sistema cardíaco | 409         | Ya existe una especialidad con ese nombre        |
    | Ayurbeda          |                                              | 409         | La descripción de la especialidad es obligatoria |
    | Gastroenterología | Especialidad del sistema digestivo           | 409         | Ya existe una especialidad con ese nombre        |



# Historias de Usuario
#    Como administrador del sistema,
#    quiero poder crear una especialidad,
#    para asociar a los médicos y centros de atención.
# Criterios de aceptación:
#    1. Se debe permitir registrar una nueva especialidad con los siguientes datos obligatorios: Nombre (único en el sistema).Descripción.
#    2. No se debe permitir registrar una especialidad con un nombre vacío o sin descripción.     
#    3. No se debe permitir registrar una especialidad con un nombre ya existente en el sistema.
#    4. En caso de error (nombre duplicado o datos faltantes), el sistema debe devolver un mensaje de error claro.
