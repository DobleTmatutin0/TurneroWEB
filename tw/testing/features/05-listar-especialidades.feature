# language: es

Característica: Listar Especialidades

Esquema del escenario: Recuperar todas las especialidades registradas en el sistema
    Dado que existen 19 especialidades registradas en el sistema
    Cuando un usuario del sistema solicita la lista de especialidades
    Entonces rta-test-05: el sistema responde con codigo: <status_code> y mensaje: "<status_text>" 
    Y la lista contiene las siguientes especialidades:
    
    |        nombre             |                      descripcion                                                    |
    | Alergia e Inmunología     | Diagnóstico y tratamiento de enfermedades alérgicas e inmunológicas.                |
    | Anatomía Patológica       | Estudio de tejidos y células para diagnosticar enfermedades.                        |
    | Anestesiología            | Administración de anestesia para procedimientos quirúrgicos y control del dolor.    |
    | Angiología                | Diagnóstico y tratamiento de enfermedades de los vasos sanguíneos y linfáticos.     |
    | Cardiología               | Diagnóstico y tratamiento de enfermedades del corazón y el sistema circulatorio.    |
    | Cirugía Cardiovascular    | Intervenciones quirúrgicas del corazón y grandes vasos sanguíneos.                  |
    | Cirugía General           | Tratamiento quirúrgico de diversas patologías en órganos internos.                  |
    | Cirugía Maxilofacial      | Cirugía de la cara, mandíbula y estructuras asociadas.                              |
    | Cirugía Plástica          | Reconstrucción, reparación y embellecimiento de tejidos y estructuras del cuerpo.   |
    | Cirugía Torácica          | Cirugía del tórax, pulmones y otras estructuras torácicas.                          |
    | Cirugía Vascular          | Diagnóstico y tratamiento quirúrgico de enfermedades de los vasos sanguíneos.       |
    | Clínica Médica            | Atención integral de enfermedades médicas en adultos.                               |
    | Dermatología              | Diagnóstico y tratamiento de enfermedades de la piel, cabello y uñas.               |
    | Diabetología              | Tratamiento y control de la diabetes y sus complicaciones.                          |
    | Emergentología            | Atención médica de urgencias y emergencias.                                         |
    | Endocrinología            | Diagnóstico y tratamiento de trastornos hormonales.                                 |
    | Endoscopía Digestiva      | Exploración y tratamiento de enfermedades del tracto digestivo mediante endoscopía. |
    | Fisiatría                 | Rehabilitación de personas con discapacidades físicas o motoras.                    |
    | Gastroenterología         | Diagnóstico y tratamiento de enfermedades del sistema digestivo.                    |
    


# Historias de Usuario
#    Como usuario del sistema.
#    Quiero poder listar todas las especialidades médicas almacenadas en el sistema.
#    Para obtener una visión general de las especialidades disponibles.
# Criterios de aceptación:
