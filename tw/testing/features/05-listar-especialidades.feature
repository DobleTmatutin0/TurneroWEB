# language: es

Característica: Listar Especialidades

Esquema del escenario: Recuperar todas las especialidades registradas en el sistema
    Dado que existen 19 especialidades registradas en el sistema
    Cuando un usuario del sistema solicita la lista de especialidades
    Entonces rta-test-05: el sistema responde con status_code 200 y status_text OK
    Y la lista contiene las siguientes especialidades:
    
    |        nombre             |                      descripcion                                                        |
    | Alergia e Inmunología     | Diagnóstico y tratamiento de enfermedades alérgicas e inmunológicas.                    |
    | Anatomía Patológica       | Estudio de tejidos y células para diagnosticar enfermedades.                            |
    | Anestesiología            | Administración de anestesia para procedimientos quirúrgicos y control del dolor.        |
    | Angiología                | Diagnóstico y tratamiento de enfermedades de los vasos sanguíneos y linfáticos.         |
    | Cardiología               | Diagnóstico y tratamiento de enfermedades del corazón y el sistema circulatorio.        |
    | Cirugía Cardiovascular    | Intervenciones quirúrgicas del corazón y grandes vasos sanguíneos.                      |
    | Cirugía General           | Tratamiento quirúrgico de diversas patologías en órganos internos.                      |
    | Cirugía Maxilofacial      | Cirugía de la cara, mandíbula y estructuras asociadas.                                  |
    | Cirugía Plástica          | Reconstrucción, reparación y embellecimiento de tejidos y estructuras del cuerpo.       |
    | Cirugía Torácica          | Cirugía del tórax, pulmones y otras estructuras torácicas.                              |
    | Cirugía Vascular          | Diagnóstico y tratamiento quirúrgico de enfermedades de los vasos sanguíneos.           |
    | Clínica Médica            | Atención integral de enfermedades médicas en adultos.                                   |
    | Dermatología              | Diagnóstico y tratamiento de enfermedades de la piel, cabello y uñas.                   |
    | Diabetología              | Tratamiento y control de la diabetes y sus complicaciones.                              |
    | Emergentología            | Atención médica de urgencias y emergencias.                                             |
    | Endocrinología            | Diagnóstico y tratamiento de trastornos hormonales.                                     |
    | Endoscopía Digestiva      | Exploración y tratamiento de enfermedades del tracto digestivo mediante endoscopía.     |
    | Fisiatría                 | Rehabilitación de personas con discapacidades físicas o motoras.                        |
    | Gastroenterología         | Diagnóstico y tratamiento de enfermedades del sistema digestivo.                        |
    | Genética Médica           | Estudio de enfermedades hereditarias y trastornos genéticos.                            |
    | Geriatría                 | Atención médica integral del adulto mayor.                                              |
    | Ginecología               | Diagnóstico y tratamiento de enfermedades del aparato reproductor femenino.             |
    | Hematología               | Diagnóstico y tratamiento de enfermedades de la sangre y órganos hematopoyéticos.       |
    | Hepatología               | Diagnóstico y tratamiento de enfermedades del hígado.                                   |
    | Infectología              | Estudio, diagnóstico y tratamiento de enfermedades infecciosas.                         |
    | Medicina del Deporte      | Prevención y tratamiento de lesiones deportivas y mejora del rendimiento.               |
    | Medicina del Trabajo      | Prevención y tratamiento de enfermedades laborales.                                     |
    | Medicina Estética         | Procedimientos para mejorar la estética y apariencia física.                            |
    | Medicina Familiar         | Atención integral de la salud en todas las etapas de la vida.                           |
    | Medicina Forense          | Aplicación de la medicina en el ámbito legal y judicial.                                |
    | Medicina General          | Atención primaria y general de la salud.                                                |
    | Medicina Interna          | Diagnóstico y tratamiento de enfermedades en adultos sin necesidad de cirugía.          |
    | Medicina Materno-Fetal    | Atención médica a embarazadas y fetos en riesgo.                                        |
    | Nefrología                | Diagnóstico y tratamiento de enfermedades renales.                                      |
    | Neonatología              | Atención médica de recién nacidos, especialmente prematuros o enfermos.                 |
    | Neumonología              | Diagnóstico y tratamiento de enfermedades pulmonares y respiratorias.                   |
    | Neurocirugía              | Cirugía del cerebro, médula espinal y nervios periféricos.                              |
    | Neurología                | Diagnóstico y tratamiento de enfermedades del sistema nervioso.                         |
    | Nutrición                 | Control de la alimentación y nutrición para la salud y prevención de enfermedades.      |
    | Obstetricia               | Atención médica del embarazo, parto y postparto.                                        |
    | Odontología               | Cuidado de la salud bucal y dental.                                                     |
    | Oftalmología              | Diagnóstico y tratamiento de enfermedades de los ojos y visión.                         |
    | Oncología                 | Diagnóstico y tratamiento del cáncer.                                                   |
    | Ortopedia y Traumatología | Diagnóstico y tratamiento de enfermedades del sistema musculoesquelético.               |
    | Otorrinolaringología      | Diagnóstico y tratamiento de enfermedades del oído, nariz y garganta.                   |
    | Pediatría                 | Atención médica integral de niños y adolescentes.                                       | 
    | Psiquiatría               | Diagnóstico y tratamiento de trastornos mentales y emocionales.                         |
    | Radiología                | Diagnóstico y tratamiento mediante técnicas de imagen médica.                           |
    | Reumatología              | Diagnóstico y tratamiento de enfermedades reumáticas y autoinmunes.                     |
    | Urología                  | Diagnóstico y tratamiento de enfermedades del aparato urinario y reproductor masculino. |
    | Terapia Intensiva         | Tratamiento médico en unidades de cuidados intensivos.                                  |
    | Medicina Esteticista      | Procedimientos médicos estéticos y rejuvenecimiento.                                    |
    | Medicina del Dolor        | Tratamiento del dolor crónico.                                                          |
    | Cirugía Reconstructiva    | Procedimientos quirúrgicos para reparar tejidos.                                        |
    | Medicina Paliativa        | Atención médica para pacientes con enfermedades graves.                                 |


# Historias de Usuario
#    Como usuario del sistema.
#    Quiero poder listar todas las especialidades médicas almacenadas en el sistema.
#    Para obtener una visión general de las especialidades disponibles.
# Criterios de aceptación:
