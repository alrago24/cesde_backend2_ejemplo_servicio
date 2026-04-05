# Validaciones en ProveedorService

Este documento explica las validaciones implementadas en la clase `ProveedorService`, que forma parte de la capa de servicio en la arquitectura del proyecto Spring Boot.

---

## Lógica de Validación Aplicada

En el método `guardarProveedor(Proveedor proveedor)` se implementa una validación específica para el campo `telefono`:

- **Verificación de nulidad:**
  Se comprueba si el campo `telefono` es `null`.

- **Validación de formato:**
  Se utiliza una expresión regular para asegurar que el teléfono contenga exactamente 10 dígitos numéricos.

  - **Patrón utilizado:**
    ```java
    telefono.matches("\\d{10}")
    ```

- **Manejo de errores:**
  Si la validación falla, se lanza una excepción `RuntimeException` con el mensaje:

  > "El teléfono debe tener exactamente 10 dígitos numéricos."

---

## Qué Garantiza Esta Validación

- **Integridad de datos:**
  Asegura que el campo `telefono` no sea nulo antes de guardar un proveedor en la base de datos.

- **Formato consistente:**
  Garantiza que el teléfono tenga exactamente 10 caracteres, todos ellos dígitos, lo que facilita el procesamiento y evita errores en operaciones futuras.

---

## Observaciones Importantes

- Esta validación se ejecuta en la **capa de servicio**, representando una regla de negocio específica para proveedores.

- La validación complementa las restricciones de JPA definidas en la entidad `Proveedor`, proporcionando una **doble capa de protección** (base de datos y lógica de negocio).

---

## Tecnologías Utilizadas en la Validación

- **Java:** Lenguaje base para la implementación de la lógica.
- **Spring Boot:** Framework que facilita la integración con la capa de repositorio.
- **Expresiones regulares:** Para validar el formato del teléfono de manera eficiente.

# Guía de Pruebas de Endpoints

En esta sección aprenderás cómo interactuar con los diferentes puntos de acceso (endpoints) que hemos creado en nuestra API de proveedores. Puedes usar herramientas como **Postman**, **Insomnia** o simplemente la terminal con el comando `curl`.

Todos los endpoints tienen como base: `http://localhost:8080/api/proveedores`

---

## 1. Listar todos los Proveedores

### Ejemplo Postman:
**Método:** `GET`
**Endpoint:** `/api/proveedores`

- Status code: `200`
- Respuesta de Postman:
```json
[
    {
        "id": 1,
        "nombre": "Google",
        "contacto": "Juan Google",
        "telefono": "3106576449"
    },
    {
        "id": 2,
        "nombre": "Google",
        "contacto": "Daniel Navegador",
        "telefono": "3100015555"
    }
]
```
---

## 2. Crear un Nuevo Proveedor (Caso Exitoso)
**Método:** `POST`
**Endpoint:** `/api/proveedores`
**Cuerpo (JSON):**
```json
{
  "nombre": "Firefox",
  "contacto": "Carlos Firefox",
  "telefono": "3102125551"
}
```
- Status code: `200`
- Respuesta de Postman:
```json
{
    "id": 3,
    "nombre": "Firefox",
    "contacto": "Carlos Firefox",
    "telefono": "3102125551"
}
```

## 3. Probar Lógica de Negocio (Caso Fallido)

### A. Numero de telefono incorrecto (Menos de 10 digitos)
**Cuerpo (JSON):**
```json
{
  "nombre": "Opera",
  "contacto": "Felipe Opera",
  "telefono": "31058664"
}
```
**Resultado esperado:** Error `400 Bad Request` con el mensaje: *"Lógica de Negocio: El teléfono debe tener exactamente 10 dígitos numéricos.."*
- Status code: `400 Bad Request`
- Respuesta de Postman: Lógica de Negocio: El teléfono debe tener exactamente 10 dígitos numéricos.

---

## 4. Buscar Proveedor por ID
**Método:** `GET`
**Endpoint:** `/api/proveedores/{id}`
**Endpoint:** `/api/proveedores/2`

- Status code: `200`
- Respuesta de Postman:
```json
{
    "id": 2,
    "nombre": "Google",
    "contacto": "Daniel Navegador",
    "telefono": "3100015555"
}
```

---

## 5. Buscar por Nombre (Método Personalizado)
**Método:** `GET`
**Endpoint:** `http://localhost:8080/api/proveedores/por-nombre?nombre=Firefox`
- Status code: `200`
- Respuesta de Postman:
```json
[
    {
        "id": 3,
        "nombre": "Firefox",
        "contacto": "Carlos Firefox",
        "telefono": "3102125551"
    }
]
```
---