# Guía de Pruebas de Endpoints

En esta sección aprenderás cómo interactuar con los diferentes puntos de acceso (endpoints) que hemos creado en nuestra API de Usuarios. Puedes usar herramientas como **Postman**, **Insomnia** o simplemente la terminal con el comando `curl`.

Todos los endpoints tienen como base: `http://localhost:8080/api/usuarios`

---

## 1. Listar todos los Usuarios
**Método:** `GET`  
**Endpoint:** `/api/usuarios`

### Ejemplo (curl):
```bash
curl -X GET http://localhost:8080/api/usuarios
```

---

## 2. Crear un Nuevo Usuario (Caso Exitoso)
**Método:** `POST`  
**Endpoint:** `/api/usuarios`  
**Cuerpo (JSON):**
```json
{
  "nombre": "Juan Pérez",
  "email": "juan.perez@example.com",
  "edad": 25
}
```

### Ejemplo (curl):
```bash
curl -X POST http://localhost:8080/api/usuarios \
     -H "Content-Type: application/json" \
     -d '{"nombre": "Juan Perez", "email": "juan.perez@example.com", "edad": 25}'
```

---

## 3. Probar Lógica de Negocio (Caso Fallido)

### A. Menor de edad (Menos de 18 años)
**Cuerpo (JSON):**
```json
{
  "nombre": "Luisito",
  "email": "luis@example.com",
  "edad": 15
}
```
**Resultado esperado:** Error `400 Bad Request` con el mensaje: *"Lógica de Negocio: El usuario debe ser mayor de 18 años."*

### B. Email Duplicado
Si intentas crear un usuario con un email que ya existe en la base de datos.
**Resultado esperado:** Error `400 Bad Request` con el mensaje: *"Lógica de Negocio: El email ya está en uso."*

---

## 4. Buscar Usuario por ID
**Método:** `GET`
**Endpoint:** `/api/usuarios/{id}`

### Ejemplo (curl):
```bash
curl -X GET http://localhost:8080/api/usuarios/1
```

---

## 5. Buscar por Rango de Edad (Método Personalizado)
**Método:** `GET`  
**Endpoint:** `/api/usuarios/por-edad?min={min}&max={max}`

### Ejemplo (curl):
```bash
curl -X GET "http://localhost:8080/api/usuarios/por-edad?min=20&max=30"
```

---

## 6. Eliminar un Usuario
**Método:** `DELETE`  
**Endpoint:** `/api/usuarios/{id}`

### Ejemplo (curl):
```bash
curl -X DELETE http://localhost:8080/api/usuarios/1
```

---

> [!TIP]
> Si estás usando **Postman**, recuerda configurar el encabezado (Header) `Content-Type` como `application/json` para las peticiones `POST`.
