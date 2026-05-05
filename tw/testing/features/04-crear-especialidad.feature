# language: es

Característica: Crear Especialidad

Esquema del escenario: Crear una especialidad exitosamente
    Cuando el administrador crea una especialidad con el nombre "<nombre>" y la descripción "<descripcion>"
    Entonces el sistema responde con codigo: <status_code> y mensaje: "<status_text>"

    Ejemplos:
    |        nombre             |                      descripcion                                                        | status_code |            status_text            |
    | Alergia e Inmunología     | Diagnóstico y tratamiento de enfermedades alérgicas e inmunológicas.                    | 200         | Especialidad creada correctamente |
    | Anatomía Patológica       | Estudio de tejidos y células para diagnosticar enfermedades.                            | 200         | Especialidad creada correctamente |
    | Anestesiología            | Administración de anestesia para procedimientos quirúrgicos y control del dolor.        | 200         | Especialidad creada correctamente |
    | Angiología                | Diagnóstico y tratamiento de enfermedades de los vasos sanguíneos y linfáticos.         | 200         | Especialidad creada correctamente |
    | Cardiología               | Diagnóstico y tratamiento de enfermedades del corazón y el sistema circulatorio.        | 200         | Especialidad creada correctamente |
    | Cirugía Cardiovascular    | Intervenciones quirúrgicas del corazón y grandes vasos sanguíneos.                      | 200         | Especialidad creada correctamente |
    | Cirugía General           | Tratamiento quirúrgico de diversas patologías en órganos internos.                      | 200         | Especialidad creada correctamente |
    | Cirugía Maxilofacial      | Cirugía de la cara, mandíbula y estructuras asociadas.                                  | 200         | Especialidad creada correctamente |
    | Cirugía Plástica          | Reconstrucción, reparación y embellecimiento de tejidos y estructuras del cuerpo.       | 200         | Especialidad creada correctamente |
    | Cirugía Torácica          | Cirugía del tórax, pulmones y otras estructuras torácicas.                              | 200         | Especialidad creada correctamente |
    | Cirugía Vascular          | Diagnóstico y tratamiento quirúrgico de enfermedades de los vasos sanguíneos.           | 200         | Especialidad creada correctamente |
    | Clínica Médica            | Atención integral de enfermedades médicas en adultos.                                   | 200         | Especialidad creada correctamente |
    | Dermatología              | Diagnóstico y tratamiento de enfermedades de la piel, cabello y uñas.                   | 200         | Especialidad creada correctamente |
    | Diabetología              | Tratamiento y control de la diabetes y sus complicaciones.                              | 200         | Especialidad creada correctamente |
    | Emergentología            | Atención médica de urgencias y emergencias.                                             | 200         | Especialidad creada correctamente |
    | Endocrinología            | Diagnóstico y tratamiento de trastornos hormonales.                                     | 200         | Especialidad creada correctamente |
    | Endoscopía Digestiva      | Exploración y tratamiento de enfermedades del tracto digestivo mediante endoscopía.     | 200         | Especialidad creada correctamente |
    | Fisiatría                 | Rehabilitación de personas con discapacidades físicas o motoras.                        | 200         | Especialidad creada correctamente |
    | Gastroenterología         | Diagnóstico y tratamiento de enfermedades del sistema digestivo.                        | 200         | Especialidad creada correctamente |
    | Genética Médica           | Estudio de enfermedades hereditarias y trastornos genéticos.                            | 200         | Especialidad creada correctamente |
    | Geriatría                 | Atención médica integral del adulto mayor.                                              | 200         | Especialidad creada correctamente |
    | Ginecología               | Diagnóstico y tratamiento de enfermedades del aparato reproductor femenino.             | 200         | Especialidad creada correctamente |
    | Hematología               | Diagnóstico y tratamiento de enfermedades de la sangre y órganos hematopoyéticos.       | 200         | Especialidad creada correctamente |
    | Hepatología               | Diagnóstico y tratamiento de enfermedades del hígado.                                   | 200         | Especialidad creada correctamente |
    | Infectología              | Estudio, diagnóstico y tratamiento de enfermedades infecciosas.                         | 200         | Especialidad creada correctamente |
    | Medicina del Deporte      | Prevención y tratamiento de lesiones deportivas y mejora del rendimiento.               | 200         | Especialidad creada correctamente |
    | Medicina del Trabajo      | Prevención y tratamiento de enfermedades laborales.                                     | 200         | Especialidad creada correctamente |
    | Medicina Estética         | Procedimientos para mejorar la estética y apariencia física.                            | 200         | Especialidad creada correctamente |
    | Medicina Familiar         | Atención integral de la salud en todas las etapas de la vida.                           | 200         | Especialidad creada correctamente |
    | Medicina Forense          | Aplicación de la medicina en el ámbito legal y judicial.                                | 200         | Especialidad creada correctamente |
    | Medicina General          | Atención primaria y general de la salud.                                                | 200         | Especialidad creada correctamente |
    | Medicina Interna          | Diagnóstico y tratamiento de enfermedades en adultos sin necesidad de cirugía.          | 200         | Especialidad creada correctamente |
    | Medicina Materno-Fetal    | Atención médica a embarazadas y fetos en riesgo.                                        | 200         | Especialidad creada correctamente |
    | Nefrología                | Diagnóstico y tratamiento de enfermedades renales.                                      | 200         | Especialidad creada correctamente |
    | Neonatología              | Atención médica de recién nacidos, especialmente prematuros o enfermos.                 | 200         | Especialidad creada correctamente |
    | Neumonología              | Diagnóstico y tratamiento de enfermedades pulmonares y respiratorias.                   | 200         | Especialidad creada correctamente |
    | Neurocirugía              | Cirugía del cerebro, médula espinal y nervios periféricos.                              | 200         | Especialidad creada correctamente |
    | Neurología                | Diagnóstico y tratamiento de enfermedades del sistema nervioso.                         | 200         | Especialidad creada correctamente |
    | Nutrición                 | Control de la alimentación y nutrición para la salud y prevención de enfermedades.      | 200         | Especialidad creada correctamente |
    | Obstetricia               | Atención médica del embarazo, parto y postparto.                                        | 200         | Especialidad creada correctamente |
    | Odontología               | Cuidado de la salud bucal y dental.                                                     | 200         | Especialidad creada correctamente |
    | Oftalmología              | Diagnóstico y tratamiento de enfermedades de los ojos y visión.                         | 200         | Especialidad creada correctamente |
    | Oncología                 | Diagnóstico y tratamiento del cáncer.                                                   | 200         | Especialidad creada correctamente |
    | Ortopedia y Traumatología | Diagnóstico y tratamiento de enfermedades del sistema musculoesquelético.               | 200         | Especialidad creada correctamente |
    | Otorrinolaringología      | Diagnóstico y tratamiento de enfermedades del oído, nariz y garganta.                   | 200         | Especialidad creada correctamente |
    | Pediatría                 | Atención médica integral de niños y adolescentes.                                       | 200         | Especialidad creada correctamente |
    | Psiquiatría               | Diagnóstico y tratamiento de trastornos mentales y emocionales.                         | 200         | Especialidad creada correctamente |
    | Radiología                | Diagnóstico y tratamiento mediante técnicas de imagen médica.                           | 200         | Especialidad creada correctamente |
    | Reumatología              | Diagnóstico y tratamiento de enfermedades reumáticas y autoinmunes.                     | 200         | Especialidad creada correctamente |
    | Urología                  | Diagnóstico y tratamiento de enfermedades del aparato urinario y reproductor masculino. | 200         | Especialidad creada correctamente |


Esquema del escenario: Intentar crear una especialidad con nombre duplicado
    Cuando el administrador crea una especialidad con el nombre "<nombre>" y la descripción "<descripcion>"
    Entonces el sistema responde con codigo: <status_code> y mensaje: "<status_text>"

    Ejemplos:
    | nombre            | descripcion                                  | status_code |              status_text                         |
    | Cardiología       | Especialidad que estudia el sistema cardíaco | 409         | Ya existe una especialidad con ese nombre        |
    | Ayurbeda          |                                              | 409         | La descripción de la especialidad es obligatoria |
    | Gastroenterología | Especialidad del sistema digestivo           | 409         | Ya existe una especialidad con ese nombre        |


Característica: Agregar Especialidades

Esquema del escenario: Agregar una nueva especialidad exitosamente
    Cuando el administrador crea una nueva especialidad con el nombre "<nombre>" y la descripción "<descripcion>"
    Entonces el sistema responde con el codigo <status_code> y el mensaje "status_text"

    Ejemplos:
    |       nombre           |                  descripcion                           | status_code |          status_text             |
    | Terapia Intensiva      | Tratamiento médico en unidades de cuidados intensivos  | 200         | Especialidad creada exitosamente |
    | Medicina Esteticista   | Procedimientos médicos estéticos y rejuvenecimiento    | 200         | Especialidad creada exitosamente |
    | Medicina del Dolor     | Tratamiento del dolor crónico                          | 200         | Especialidad creada exitosamente |
    | Cirugía Reconstructiva | Procedimientos quirúrgicos para reparar tejidos        | 200         | Especialidad creada exitosamente |
    | Medicina Paliativa     | Atención médica para pacientes con enfermedades graves | 200         | Especialidad creada exitosamente |



# Historias de Usuario
#    Como administrador del sistema,
#    quiero poder crear una especialidad,
#    para asociar a los médicos y centros de atención.
# Criterios de aceptación:
#    1. Se debe permitir registrar una nueva especialidad con los siguientes datos obligatorios: Nombre (único en el sistema).Descripción.
#    2. No se debe permitir registrar una especialidad con un nombre vacío o sin descripción.     
#    3. No se debe permitir registrar una especialidad con un nombre ya existente en el sistema.
#    4. En caso de error (nombre duplicado o datos faltantes), el sistema debe devolver un mensaje de error claro.
