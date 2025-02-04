### Laboratorio de Programación y lenguajes
### Trabajo Práctico
# Sistema de Turnos Web de Atención Ambulatoria

> Este laboratorio está diseñado para desarrollarse sobre sistemas **Linux**. Es recomendable que los estudiantes se familiaricen con este entorno para cumplir con los requisitos de la asignatura.
>
> Opciones para quienes no cuenten con Linux en sus equipos:
> 1. (Recomendada) Instalar cualquier distribución Linux en modo dual-boot.
> 2. Utilizar una máquina virtual con Linux.

> En la raíz de este directorio encontrarás el script `labprog` para facilitar la ejecución de comandos. En el presente instructivo se indicará en cada paso si corresponde utilizar este script.

## Setup
1. Instalar `Git`
2. Instalar `Docker` y `Docker Compose`
3. Iniciar servicio docker `sudo systemctl start docker`
    > Este comando puede variar según la distro de Linux utilizada.
4. Clonar este repositorio con sus submódulos `git clone --recursive <repo_url>`
5. Ir al directorio clonado `cd <repo_dir>`
6. Instalar las dependencias del cliente `docker-compose -f base/docker-compose.yml --project-directory . run client ./install.sh` o `./labprog install`

### Levantar los servicios
Desde la carpeta raíz:

```docker-compose -f base/docker-compose.yml --project-directory . up -d```

o

```./labprog up```

Para verificar su funcionamiento, ingresar a `http://localhost:8080` para ver la página de bienvenida del servidor, y acceder a `http://localhost:4200` para ver la aplicación.

### Conectarse al servidor

Para acceder al servidor **back-end**, ejecutar:

```docker exec -it turnero-server bash```

o

```./labprog bash server```

### Conectarse al cliente
Para conectarse al servidor **front-end** de Angular, ejecutar:

```docker exec -it turnero-client bash```

o

```./labprog bash client```

### Detener los servicios

Para detener los servicios configurados en Docker Compose:

```docker-compose -f base/docker-compose.yml --project-directory . down```

o

```./labprog down```

---

## Problema planteado
El presente sistema busca optimizar la gestión de turnos ambulatorios en centros de atención médica. Se requiere una herramienta que permita a los centros organizar sus consultorios, médicos y agendas, además de gestionar a los pacientes y sus turnos, facilitando una operación eficiente y sin conflicto de horarios.

## Objetivo de la Solución
El sistema brindará a los centros de atención ambulatoria una plataforma para la asignación eficiente de turnos médicos. La aplicación permitirá la administración de los consultorios, médicos, pacientes y configuraciones de agendas, con el objetivo de gestionar los turnos de manera automática y sin conflictos de horarios.

## La solución requiere

### Administración de la Información del Sistema
#### Administración de Centros de Atención
El sistema contará con un módulo de administración de los distintos centros de atención. Para cada centro, se registrarán sus datos y los consultorios asociados.

#### Administración de Consultorios
Cada consultorio estará configurado con su disponibilidad horaria y su asignación a médicos específicos según sus especialidades. La gestión de agendas será fundamental para evitar conflictos y optimizar la atención a los pacientes.

#### Administración de Médicos
El sistema permitirá gestionar los médicos, incluyendo sus especialidades y las horas en las que estarán disponibles en cada consultorio.

#### Administración de Pacientes
Los pacientes se podrán registrar en el sistema, con la posibilidad de indicar su obra social y gestionar sus turnos de manera eficiente.

### Gestión de Turnos y Configuración de Agendas
#### Configuración de Agendas
Cada consultorio tendrá una configuración de agenda donde se definirá su disponibilidad y la especialidad del médico asignado. Se podrán definir las franjas horarias y los días en los que estará activo cada consultorio, permitiendo una asignación de turnos sin slots fijos.

#### Generación Automática de Agenda
El sistema debe generar automáticamente la agenda de cada médico en función de su disponibilidad y de la disponibilidad de consultorios. En caso de que no exista disponibilidad inmediata en el consultorio solicitado, el sistema ofrecerá al usuario opciones de reprogramación, permitiendo re-agendar los turnos para lograr que la agenda cumpla con los parámetros de disponibilidad definidos por cada médico.

#### Asignación de Turnos
Los pacientes podrán reservar turnos basados en las disponibilidades definidas en la configuración de cada consultorio. La asignación será **slotless**, permitiendo flexibilidad y evitando la saturación del sistema de turnos.

### Control y Auditoría
#### Registro de Modificaciones
Para la administración de turnos, se mantendrá un registro histórico de las modificaciones y ajustes realizados, permitiendo la trazabilidad de cada cambio. 

#### Gestión de Auditoría de Turnos
El sistema tendrá herramientas para la corrección de turnos y permitirá ajustes sin perder la coherencia en la información de los turnos.

### Reportes de Gestión
La solución generará reportes de utilidad para los centros de atención, que incluirán:
- Reportes de cantidad de turnos por día y consultorio.
- Distribución de turnos por especialidad y médico.
- Estadísticas sobre la cantidad de turnos cancelados y reasignados.

## Criterio de Satisfacción

Además de cumplir con los requerimientos funcionales anteriores, el desarrollo de la aplicación deberá garantizar las siguientes premisas:

### Funcionalidades Clave

1. **Gestión de Centros de Atención**
   - Permitir la creación, modificación y eliminación de centros de atención con información relevante como nombre, dirección y teléfono.
   - Cada centro de atención debe poder asociarse con múltiples consultorios, pacientes y médicos.

2. **Gestión de Consultorios y Configuración de Agendas**
   - Los consultorios deben ser configurables en cada centro de atención, permitiendo establecer un número único, su capacidad y los períodos de uso.
   - Configuración de agendas en función de la disponibilidad de cada médico y las especialidades de cada consultorio. 
   - El sistema debe generar las agendas de acuerdo con la disponibilidad de consultorios, especialidad requerida y disponibilidad de médicos.
   - En caso de no encontrar disponibilidad, el sistema debe ofrecer opciones de re-agendamiento para asegurar el cumplimiento de los parámetros de disponibilidad de los médicos.

3. **Gestión de Pacientes**
   - Los pacientes deben estar registrados en el sistema con sus datos personales y asociados a una obra social específica si aplica.
   - Permitir la modificación de la información de cada paciente y su vinculación con una obra social.

4. **Gestión de Médicos y Especialidades**
   - Registro de médicos, indicando sus datos personales y la especialidad en la que se desempeñan.
   - Administración de la disponibilidad horaria de cada médico, especificando los días y horas en que pueden atender.
   - Asignación de médicos a consultorios específicos según su especialidad y disponibilidad, reflejada en la configuración de agendas de los consultorios.
   - Posibilidad de asociar a cada médico con múltiples obras sociales que aceptan.

5. **Asignación de Turnos (Slotless)**
   - Generación de turnos para pacientes en función de la disponibilidad de consultorios y médicos.
   - Los turnos no estarán preasignados a franjas horarias fijas, sino que se gestionarán en un esquema slotless, optimizando la disponibilidad.
   - Asignación dinámica de turnos basados en el horario y tiempo estimado para cada paciente según la especialidad.
   - En caso de falta de disponibilidad, el sistema ofrecerá opciones de reprogramación de turnos y sugerencias de consultorios alternativos si están disponibles.

6. **Estado de Turnos**
   - Un turno puede tener los siguientes estados: `Programado`, `Confirmado`, `Cancelado`, `Reagendado`.
   - El sistema debe registrar cambios de estado de cada turno y notificar a los pacientes sobre los cambios.
   - Soporte para la cancelación y reprogramación de turnos cuando el consultorio o el médico no estén disponibles.

7. **Sistema de Reagendamiento y Optimización de Disponibilidad**
   - Implementación de un sistema que verifique la disponibilidad de los recursos (consultorios, médicos) y re-agende automáticamente si no se puede cumplir con el turno original.
   - La reprogramación debe basarse en la disponibilidad de consultorios, médicos y preferencias de los pacientes.

8. **Reportes y Gestión de Información**
   - Generación de reportes sobre:
      - Cantidad de turnos atendidos por centro de atención, médico y consultorio.
      - Distribución de turnos por especialidad y por estado (programado, confirmado, cancelado, reagendado).
      - Disponibilidad general de consultorios y agendas de médicos.
   - Soporte para exportación de reportes en formato CSV o PDF para su análisis y archivo.

### Requerimientos Técnicos

1. **Persistencia de Datos**
   - Uso de **JPA** para la persistencia del modelo de datos.
   - Las consultas a la base de datos deben realizarse mediante JPQL.
   - Implementación de transacciones ACID en los procesos clave de generación de agenda, asignación y reprogramación de turnos.

2. **Diseño Orientado a Objetos**
   - Separación en capas siguiendo el patrón **MVC** (Modelo-Vista-Controlador).
   - Aplicación de principios **SOLID**:
     - **SRP**: Clases con responsabilidad única, como `CentroDeAtencion`, `Medico`, `Paciente`, y `Consultorio`.
     - **OCP**: Posibilidad de extender funcionalidad sin modificar el código base.
     - **LSP**: Uso de clases base como `Persona` para `Paciente` y `Medico`.
     - **ISP** y **DIP**: Segregación de interfaces y dependencia en abstracciones donde sea necesario.

3. **Stack Tecnológico Requerido**
   - **Angular** para la capa frontend.
   - **JAX-RS** para la capa de servicios RESTful.
   - **Java EE** para controladores y lógica de negocio.
   - **PostgreSQL** como motor de base de datos.

4. **Pruebas y Validación de Funcionalidades**
   - Pruebas funcionales que verifiquen los casos de uso descritos.
   - Simulaciones de carga de datos para evaluar la generación y reprogramación de turnos en condiciones de alta demanda.
   - Validación del comportamiento del sistema en escenarios de reprogramación automática y en casos de conflictos de disponibilidad.

## Criterio de satisfacción
### Stack Tecnológico
El desarrollo de la aplicación deberá cumplir los siguientes requisitos:
* Usar JPA para la persistencia de datos, con consultas en JPQL.
* Implementar el sistema siguiendo el patrón **MVC**.
* Asegurar transacciones **ACID** en la base de datos.
* Implementar los principios **SOLID** en la estructura de clases y la organización de código.

#### Stack Requerido
* **AngularJS** para la interfaz de cliente web.
* **JAX-RS** para servicios REST en el backend.
* **JavaEE** para controladores y servicios.
* **JPA** para la capa de persistencia.
* **PostgreSQL** para la base de datos.

### Ejemplo de uso y validaciones
La aplicación debe implementar los casos de uso detallados a continuación:
* Configuración de centros de atención, consultorios, médicos y agendas.
* Registro de pacientes y asignación de turnos.
* Generación de reportes con datos de los turnos.

Pruebas de validación:
* Cargar múltiples médicos y pacientes en el sistema, con al menos 10 pacientes y 5 médicos.
* Asignar turnos a los pacientes sin conflictos de horarios.
* Generar reportes de turnos y verificar su coherencia.

## Forma de entrega
1. El proyecto será individual, aunque es posible colaborar con compañeros.
2. La entrega del proyecto incluirá:
   * Código completo en un repositorio Git.
   * Bitácora de desarrollo detallada en formato Markdown o Wiki.
   * Documentación de diseño y patrones utilizados.
   * Instrucciones o scripts para la compilación y despliegue.
3. Documentación del código y guía de uso.

## Criterios de aprobación
Para la aprobación del trabajo, se evaluarán los siguientes aspectos:
1. Planificación del proyecto y cumplimiento de las etapas.
2. Funcionamiento de la aplicación y cumplimiento de los requerimientos.
3. Calidad y estructura de la presentación y documentación.
4. Conocimiento y defensa de la solución implementada.

## Instrucciones para deploy de proyecto base
1. Bajar el archivo de export del proyecto seleccionado
2. Conectarse a gitlab
3. En la barra superior lado derecho presionar el botón /ícono de "NEW" y seleccionar "New projecy/repository". Seleccionar la opción import project. Seleccionar la opción Gitlab Export. En el nombre del proyecto poner el nombre del TP asignado y en "Seleccionar archivo" indicar el archivo bajado.
3. Ejecutar `mvn clean install`.
4. Desplegar la aplicación en el servidor configurado.

