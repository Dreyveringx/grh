Modelo Conceptual del Sistema de Gestión de Recursos Humanos

1. Introducción

El presente documento describe el modelo conceptual del sistema de gestión de recursos humanos multiempresa, detallando su organización, operación y la relación entre sus componentes. También se incluye la parte lógica del sistema para establecer su estructura funcional y su integración con la base de datos.

2. Definición de Conceptos Principales

2.1 Entidades Principales

Empresa: Representa a una organización dentro del sistema. Puede gestionar sus propios procesos y usuarios.

Usuario: Representa a cualquier persona que interactúa con el sistema. Se clasifica en:

Administrador: Gestiona la empresa, usuarios y procesos.

Empleado: Usuario registrado con acceso restringido a su información.

Candidato: Persona que postula a una vacante.

Proceso de Selección: Flujo mediante el cual un candidato es evaluado y contratado.

Roles y Permisos: Definen los niveles de acceso de cada usuario.

Reportes y Análisis: Datos procesados sobre la gestión del personal.

Autenticación y Seguridad: Mecanismos para controlar el acceso al sistema.

3. Relaciones entre los Conceptos

Una empresa tiene múltiples usuarios.

Un usuario puede ser administrador, empleado o candidato.

Un candidato pasa por un proceso de selección.

Un administrador define roles y permisos.

Los datos se almacenan en la base de datos y pueden ser consultados en reportes.

El sistema implementa seguridad y autenticación.

4. Modelo Lógico

4.1 Procesos Clave

Registro y autenticación de usuarios

Gestión de empresas y administración de usuarios

Procesos de selección y evaluación de candidatos

Asignación de roles y permisos

Generación de reportes y análisis de datos

Seguridad y protección de la información

4.2 Estructura de Datos

Usuarios (ID, nombre, correo, rol, empresa, estado)

Empresas (ID, nombre, dirección, administradorID)

Candidatos (ID, nombre, experiencia, estado, empresaID)

Procesos de Selección (ID, candidatoID, etapa, resultado)

Roles y Permisos (ID, nombre, permisos)