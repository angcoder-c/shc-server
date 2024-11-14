# API Documentation

---


## Activity

### Obtener todas las actividades

`GET /api/activities/`

#### Descripción
Obtiene una lista de todas las actividades registradas.

#### Respuesta
- **200 OK**: Devuelve una lista de objetos `Activity`.

#### Ejemplo de respuesta
```json
[
    {
        "id": 1,
        "name": "Actividad 1",
        "startTime": "08:00:00",
        "endTime": "10:00:00",
        "multiplier": 1.5,
        "scholarshipHoursOffered": 10,
        "coordinator": "Coordinador 1",
        "location": "Edificio A",
        "maxCapacity": 50,
        "department": "Departamento 1",
        "description": "Descripción de la actividad 1",
        "date": "2024-09-01"
    }
]
```

### Obtener una actividad por ID
`GET /api/activities/{id}`

#### Descripción
Obtiene los detalles de una actividad específica mediante su ID.

#### Parámetros de ruta
- **id**: ID de la actividad que se desea obtener.

#### Respuesta
- **200 OK**: Devuelve un objeto `Activity`.
- **404 Not Found**: Si no se encuentra la actividad.

#### Ejemplo de respuesta
```json
{
    "id": 1,
    "name": "Actividad 1",
    "startTime": "08:00:00",
    "endTime": "10:00:00",
    "multiplier": 1.5,
    "scholarshipHoursOffered": 10,
    "coordinator": "Coordinador 1",
    "location": "Edificio A",
    "maxCapacity": 50,
    "department": "Departamento 1",
    "description": "Descripción de la actividad 1",
    "date": "2024-09-01"
}
```

### Crear una nueva actividad

`POST /api/activities/`

#### Descripción

Crea una nueva actividad.

#### Cuerpo de la solicitud
Un objeto `Activity` en formato JSON.

#### Respuesta
- **201 Created**: Devuelve el objeto `Activity` creado.

#### Ejemplo de solicitud
```json
{
    "name": "Actividad 2",
    "startTime": "14:00:00",
    "endTime": "16:00:00",
    "multiplier": 1.2,
    "scholarshipHoursOffered": 8,
    "coordinator": "Coordinador 2",
    "location": "Edificio B",
    "maxCapacity": 30,
    "department": "Departamento 2",
    "description": "Descripción de la actividad 2",
    "date": "2024-09-02"
}
```

#### Ejemplo de respuesta
```json
{
    "id": 2,
    "name": "Actividad 2",
    "startTime": "14:00:00",
    "endTime": "16:00:00",
    "multiplier": 1.2,
    "scholarshipHoursOffered": 8,
    "coordinator": "Coordinador 2",
    "location": "Edificio B",
    "maxCapacity": 30,
    "department": "Departamento 2",
    "description": "Descripción de la actividad 2",
    "date": "2024-09-02"
}
```

### Actualizar una actividad

`PUT /api/activities/{id}`

#### Descripción
Actualiza los detalles de una actividad existente.

#### Parámetros de ruta
- **id**: ID de la actividad que se desea actualizar.

#### Cuerpo de la solicitud
Un objeto `Activity` en formato JSON con los nuevos valores.

#### Respuesta
- **200 OK**: Devuelve el objeto `Activity` actualizado.
- **404 Not Found**: Si no se encuentra la actividad.

#### Ejemplo de solicitud
```json
{
    "name": "Actividad Actualizada",
    "startTime": "15:00:00",
    "endTime": "17:00:00",
    "multiplier": 1.3,
    "scholarshipHoursOffered": 9,
    "coordinator": "Coordinador 3",
    "location": "Edificio C",
    "maxCapacity": 40,
    "department": "Departamento 3",
    "description": "Descripción actualizada",
    "date": "2024-09-03"
}
```

#### Ejemplo de respuesta
```json
{
    "id": 1,
    "name": "Actividad Actualizada",
    "startTime": "15:00:00",
    "endTime": "17:00:00",
    "multiplier": 1.3,
    "scholarshipHoursOffered": 9,
    "coordinator": "Coordinador 3",
    "location": "Edificio C",
    "maxCapacity": 40,
    "department": "Departamento 3",
    "description": "Descripción actualizada",
    "date": "2024-09-03"
}
```

### Eliminar una actividad

`DELETE /api/activities/{id}`

#### Descripción

Elimina una actividad existente mediante su ID.

#### Parámetros de ruta

- **id**: ID de la actividad que se desea eliminar.

#### Respuesta

- **204 No Content**: Si la actividad fue eliminada exitosamente.

- **404 Not Found**: Si no se encuentra la actividad.

### Obtener estudiantes por ID de actividad

`GET /api/activities/{id}/students`

#### Descripción
Obtiene la lista de estudiantes inscritos en una actividad específica.

#### Parámetros de ruta
- **id**: ID de la actividad.

#### Respuesta

- **200 OK**: Devuelve una lista de objetos `Student` inscritos en la actividad.

- **404 Not Found**: Si no se encuentra la actividad.

#### Ejemplo de respuesta
```json
[
    {
        "id": 1,
        "name": "Estudiante 1"
        ...
    },
    {
        "id": 2,
        "name": "Estudiante 2"
        ...
    }
]
```

### Obtener el valor de horas de beca

`GET /api/activities/{id}/scholarshipHourValue`

#### Descripción

Obtiene el valor de horas de beca para una actividad específica, calculado como `multiplier * scholarshipHoursOffered`.

#### Parámetros de ruta

- **id**: ID de la actividad.

#### Respuesta

- **200 OK**: Devuelve un valor `Double` que representa el valor de las horas de beca.

- **404 Not Found**: Si no se encuentra la actividad.

#### Ejemplo de respuesta

```json
15.0
```

### Añadir estudiante a una actividad

`POST /api/activities/{activityId}/addStudent/{studentId}`

#### Descripción

Añade un estudiante a una actividad.

#### Parámetros de ruta

- **activityId**: ID de la actividad.
- **studentId**: ID del estudiante.

#### Respuesta

- **200 OK**: Devuelve el objeto `Activity` actualizado con el estudiante añadido.

- **404 Not Found**: Si no se encuentra la actividad o el estudiante.

### Eliminar estudiante de una actividad

`DELETE /api/activities/{activityId}/removeStudent/{studentId}`

#### Descripción

Elimina un estudiante de una actividad.

#### Parámetros de ruta

- **activityId**: ID de la actividad.
- **studentId**: ID del estudiante.

#### Respuesta

- **200 OK**: Devuelve el objeto `Activity` actualizado con el estudiante eliminado.

- **404 Not Found**: Si no se encuentra la actividad o el estudiante.

### Deshabilitar inscripciones a una actividad

`PUT /api/activities/{id}/disableJoining`

#### Descripción

Deshabilita la opción de inscribirse en una actividad.

#### Parámetros de ruta

- **id**: ID de la actividad.

#### Respuesta

- **200 OK**: Devuelve el objeto `Activity` actualizado.
- **404 Not Found**: Si no se encuentra la actividad.

### Generar código QR de inicio de actividad

**GET** `/api/activities/{id}/startQRCode`

#### Descripción

Genera un código QR que los estudiantes pueden escanear para registrar su asistencia al inicio de una actividad.

#### Parámetros de ruta

- **id**: ID de la actividad.

#### Respuesta

- **200 OK**: Devuelve un archivo de imagen PNG con el código QR.
- **404 Not Found**: Si no se encuentra la actividad.
- **500 Internal Server Error**: Si ocurre un error al generar el código QR.

### Generar código QR de fin de actividad

`GET /api/activities/{id}/endQRCode`

#### Descripción
Genera un código QR que los estudiantes pueden escanear para registrar su salida al finalizar una actividad.

#### Parámetros de ruta

- **id**: ID de la actividad.

#### Respuesta

- **200 OK**:  Devuelve un archivo de imagen PNG con el código QR.

- **404 Not Found**: Si no se encuentra la actividad.

- **500 Internal Server Error**: Si ocurre un error al generar el código QR.

---


## Coordinator

### Obtener todos los coordinadores

`GET /api/coordinators`

#### Descripción

Obtiene una lista de todos los coordinadores registrados.

#### Respuesta

- **200 OK**: Devuelve una lista de objetos `Coordinator`.

#### Ejemplo de respuesta
```json
[
    {
        "id": 1,
        "name": "Coordinador 1",
        "email": "coordinador1@example.com",
        "position": "Jefe de Departamento"
    },
    {
        "id": 2,
        "name": "Coordinador 2",
        "email": "coordinador2@example.com",
        "position": "Coordinador Académico"
    }
]
```

### Obtener un coordinador por ID
`GET /api/coordinators/{id}`

#### Descripción

Obtiene los detalles de un coordinador específico mediante su ID.

#### Parámetros de ruta

- **id**: ID del coordinador que se desea obtener.

#### Respuesta

- **200 OK**: Devuelve un objeto `Coordinator`.
- **404 Not Found**: Si no se encuentra el coordinador.

#### Ejemplo de respuesta

```json
{
    "id": 1,
    "name": "Coordinador 1",
    "email": "coordinador1@example.com",
    "position": "Jefe de Departamento"
}
```

### Crear un nuevo coordinador

`POST /api/coordinators`

#### Descripción

Crea un nuevo coordinador.

#### Cuerpo de la solicitud

Un objeto `Coordinator` en formato JSON.

#### Respuesta

- **201 Created**: Devuelve el objeto `Coordinator` creado.

#### Ejemplo de solicitud

```json
{
    "name": "Coordinador 3",
    "email": "coordinador3@example.com",
    "password": "password123",
    "position": "Coordinador de Proyectos"
}
```

#### Ejemplo de respuesta

```json
{
    "id": 3,
    "name": "Coordinador 3",
    "email": "coordinador3@example.com",
    "position": "Coordinador de Proyectos"
}
```

### Actualizar un coordinador

`PUT /api/coordinators/{id}`

#### Descripción

Actualiza los detalles de un coordinador existente.

#### Parámetros de ruta

- **id**: ID del coordinador que se desea actualizar.

#### Cuerpo de la solicitud

Un objeto `Coordinator` en formato JSON con los nuevos valores.

#### Respuesta

- **200 OK**: Devuelve el objeto `Coordinator` actualizado.
- **404 Not Found**: Si no se encuentra el coordinador.

#### Ejemplo de solicitud

```json
{
    "name": "Coordinador Actualizado",
    "email": "coordinador1@example.com",
    "password": "newpassword123",
    "position": "Director de Departamento"
}
```

#### Ejemplo de respuesta

```json
{
    "id": 1,
    "name": "Coordinador Actualizado",
    "email": "coordinador1@example.com",
    "position": "Director de Departamento"
}
```

### Eliminar un coordinador

`DELETE /api/coordinators/{id}`

#### Descripción

Elimina un coordinador existente mediante su ID.

#### Parámetros de ruta

- **id**: ID del coordinador que se desea eliminar.

#### Respuesta

- **204 No Content**: Si el coordinador fue eliminado exitosamente.

- **404 Not Found**: Si no se encuentra el coordinador.

---

## Student

### Obtener todos los estudiantes

`GET /api/students/`

#### Descripción

Obtiene una lista de todos los estudiantes registrados.

#### Respuesta

- **200 OK**: Devuelve una lista de objetos `Student`.

#### Ejemplo de respuesta

```json
[
    {
        "id": 1,
        "name": "Estudiante 1",
        "email": "estudiante1@example.com",
        "major": "Ingeniería Informática",
        "year": 3,
        "scholarshipHours": 50,
        "completedScholarshipHours": 20,
        "score": 5
    },
    {
        "id": 2,
        "name": "Estudiante 2",
        "email": "estudiante2@example.com",
        "major": "Ingeniería Electrónica",
        "year": 2,
        "scholarshipHours": 40,
        "completedScholarshipHours": 15,
        "score": 4.5
    }
]
```

### Obtener un estudiante por ID

`GET /api/students/{id}`

#### Descripción

Obtiene los detalles de un estudiante específico mediante su ID.

#### Parámetros de ruta

- **id**: ID del estudiante que se desea obtener.

#### Respuesta

- **200 OK**: Devuelve un objeto `Student`.

- **404 Not Found**: Si no se encuentra el estudiante.

#### Ejemplo de respuesta

```json
{
    "id": 1,
    "name": "Estudiante 1",
    "email": "estudiante1@example.com",
    "major": "Ingeniería Informática",
    "year": 3,
    "scholarshipHours": 50,
    "completedScholarshipHours": 20,
    "score": 5
}
```

### Crear un nuevo estudiante

`POST /api/students`

#### Descripción

Crea un nuevo estudiante.

#### Cuerpo de la solicitud

Un objeto `Student` en formato JSON.

#### Respuesta

- **201 Created**: Devuelve el objeto `Student` creado.

#### Ejemplo de solicitud

```json
{
    "name": "Estudiante 3",
    "email": "estudiante3@example.com",
    "password": "password123",
    "major": "Ingeniería Mecánica",
    "year": 1,
    "scholarshipHours": 30,
    "completedScholarshipHours": 10,
    "score": 5
}
```

#### Ejemplo de respuesta

```json
{
    "id": 3,
    "name": "Estudiante 3",
    "email": "estudiante3@example.com",
    "major": "Ingeniería Mecánica",
    "year": 1,
    "scholarshipHours": 30,
    "completedScholarshipHours": 10,
    "score": 5
}
```

### Actualizar un estudiante

`PUT /api/students/{id}`

#### Descripción

Actualiza los detalles de un estudiante existente.

#### Parámetros de ruta

- **id**: ID del estudiante que se desea actualizar.

#### Cuerpo de la solicitud

Un objeto `Student` en formato JSON con los nuevos valores.

#### Respuesta

- **200 OK**: Devuelve el objeto `Student` actualizado.

- **404 Not Found**: Si no se encuentra el estudiante.

#### Ejemplo de solicitud

```json
{
    "name": "Estudiante Actualizado",
    "email": "estudiante1@example.com",
    "password": "newpassword123",
    "major": "Ingeniería Informática",
    "year": 3,
    "scholarshipHours": 50,
    "completedScholarshipHours": 25,
    "score": 5
}
```

#### Ejemplo de respuesta
```json
{
    "id": 1,
    "name": "Estudiante Actualizado",
    "email": "estudiante1@example.com",
    "major": "Ingeniería Informática",
    "year": 3,
    "scholarshipHours": 50,
    "completedScholarshipHours": 25,
    "score": 5
}
```

### Eliminar un estudiante

`DELETE /api/students/{id}`

#### Descripción

Elimina un estudiante existente mediante su ID.

#### Parámetros de ruta

- **id**: ID del estudiante que se desea eliminar.

#### Respuesta

- **204 No Content**: Si el estudiante fue eliminado exitosamente.

- **404 Not Found**: Si no se encuentra el estudiante.

### Unirse a una actividad

`POST /api/students/{studentId}/join/{activityId}`

#### Descripción

Permite que un estudiante se una a una actividad específica.

#### Parámetros de ruta

- **studentId**: ID del estudiante.
- **activityId**: ID de la actividad a la que se desea unir.

#### Respuesta
- **200 OK**: Devuelve el objeto `Student` actualizado.
- **404 Not Found**: Si no se encuentra el estudiante o la actividad.

#### Ejemplo de respuesta

```json
{
    "id": 1,
    "name": "Estudiante 1",
    "email": "estudiante1@example.com",
    "major": "Ingeniería Informática",
    "year": 3,
    "scholarshipHours": 50,
    "completedScholarshipHours": 20,
    "score": 5,
    "activity": {
        "id": 5,
        "name": "Actividad 1"
    }
}
```

### Obtener horas completadas de beca

`GET /api/students/{id}/completed-hours`

#### Descripción

Obtiene el número total de horas de beca completadas por un estudiante específico.

#### Parámetros de ruta

- **id**: ID del estudiante.

#### Respuesta

- **200 OK**: Devuelve un entero representando las horas completadas.

- **404 Not Found**: Si no se encuentra el estudiante.

#### Ejemplo de respuesta

```json
20
```

### Actualizar el registro de un estudiante

`PUT /api/students/{id}/updateRecord`

#### Descripción

Actualiza el registro de un estudiante con nueva información.

#### Parámetros de ruta

- **id**: ID del estudiante que se desea actualizar.

#### Cuerpo de la solicitud

Un objeto `Student` en formato JSON con los nuevos valores.

#### Respuesta
- **200 OK**: Devuelve el objeto `Student` actualizado.
- **404 Not Found**: Si no se encuentra el estudiante.

#### Ejemplo de solicitud
```json
{
    ...
    "score": 4.1,
    ...
}
```

#### Ejemplo de respuesta
```json
{
    "id": 1,
    "name": "Estudiante 1",
    "email": "estudiante1@example.com",
    "major": "Ingeniería Informática",
    "year": 3,
    "scholarshipHours": 50,
    "completedScholarshipHours": 25,
    "score": 4.1
}
```

# Autenticación

## Registro de un nuevo coordinador

- `POST /api/auth/register/coordinator/`

```json
{
  "name": "CoordinadorX",
  "email": "xc@example.com",
  "password": "securepassword",
  "position": "Cordinator"
}
```

## Login de coordinador

- `POST /api/auth/login/coordinator/`

```json
{
  "email": "xc@example.com",
  "password": "securepassword"
}
```


## Registro de un nuevo estudiante

- `POST /api/auth/register/student/`

```json
{
  "name": "EstudianteX",
  "email": "x@example.com",
  "password": "securepassword",
  "major": "Computer Science",
  "year": 3
}
```

### Login de estudiante

- `POST /api/auth/login/student/`

```json
{
  "email": "x@example.com",
  "password": "securepassword"
}
```

