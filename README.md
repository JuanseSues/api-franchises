# Franchise Management System

Este es un sistema de gestión de franquicias desarrollado con Spring Boot WebFlux y MongoDB, implementando programación reactiva.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.1.5
- Spring WebFlux
- MongoDB Reactive
- Docker
- Maven

## Requisitos Previos

- Java 17 o superior
- Docker y Docker Compose
- Maven (opcional, incluido en el wrapper)

## Estructura del Proyecto

```
franchise-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/franchise/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── FranchiseApplication.java
│   │   └── resources/
│   │       └── application.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Configuración y Ejecución

Repositorio >GitHub

https://github.com/JuanseSues/api-franchises

### Usando Docker (Recomendado)

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/JuanseSues/api-franchises
   cd franchise-management
   ```

2. Construir y ejecutar con Docker Compose:
   ```bash
   docker-compose up --build
   ```

La aplicación estará disponible en `http://localhost:8080/api`

### Ejecución Local

1. Asegúrate de tener MongoDB ejecutándose localmente en el puerto 27017

2. Ejecutar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

### Ejecución en la Nube con MongoDB Atlas

Para ejecutar esta aplicación en la nube utilizando MongoDB Atlas, sigue estos pasos:

1. **Crear una Cuenta en MongoDB Atlas**:
   - Regístrate en [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) y crea un clúster.

2. **Configurar la Red**:
   - Permite el acceso desde cualquier IP (0.0.0.0/0) para desarrollo.
   - En la página del clúster, haz clic en "Network Access" en el menú de la izquierda.
   - Haz clic en "Add IP Address".
   - Selecciona "Allow Access from Anywhere" (0.0.0.0/0) y haz clic en "Confirm".

3. **Crear un Usuario de Base de Datos**:
   - Crea un usuario de base de datos con un nombre de usuario y contraseña.
   - En la página del clúster, haz clic en "Database Access" en el menú de la izquierda.
   - Haz clic en "Add New Database User".
   - Crea un usuario con un nombre de usuario y contraseña. Asegúrate de guardar esta información.

4. **Obtener la URI de Conexión**:
   - En la página del clúster, haz clic en "Connect" y selecciona "Connect your application".
   - Copia la URI de conexión proporcionada. 
     ```
     mongodb+srv://<username>:<password>@cluster0.mongodb.net/<dbname>?retryWrites=true&w=majority
     ```


## API Endpoints

### Franquicias

- `POST /api/franchises` - Crear nueva franquicia
- `GET /api/franchises` - Listar todas las franquicias
- `GET /api/franchises/{id}` - Obtener franquicia por ID
- `PUT /api/franchises/{id}/name` - Actualizar nombre de franquicia

### Sucursales

- `POST /api/branches/franchise/{franchiseId}` - Agregar sucursal a franquicia
- `GET /api/branches/{id}` - Obtener sucursal por ID
- `GET /api/branches/franchise/{franchiseId}` - Listar sucursales por franquicia
- `PUT /api/branches/{id}/name` - Actualizar nombre de sucursal

### Productos

- `POST /api/products/branch/{branchId}` - Agregar producto a sucursal
- `DELETE /api/products/branch/{branchId}/{productId}` - Eliminar producto de sucursal
- `PATCH /api/products/{productId}/stock` - Actualizar stock de producto
- `PUT /api/products/{productId}/name` - Actualizar nombre de producto
- `GET /api/products/highest-stock/franchise/{franchiseId}` - Obtener productos con mayor stock por sucursal

## Ejemplos de Uso

### Crear una Franquicia

```bash
curl -X POST http://localhost:8080/api/franchises \
  -H "Content-Type: application/json" \
  -d '{"name": "Mi Franquicia"}'
```

### Agregar una Sucursal

```bash
curl -X POST http://localhost:8080/api/branches/franchise/{franchiseId} \
  -H "Content-Type: application/json" \
  -d '{"name": "Sucursal Centro"}'
```

### Agregar un Producto

```bash
curl -X POST http://localhost:8080/api/products/branch/{branchId} \
  -H "Content-Type: application/json" \
  -d '{"name": "Producto 1", "stock": 100}'
```

## Características Implementadas

- Programación reactiva con WebFlux
- Manejo de excepciones global
- Validación de datos
- Documentación completa
- Containerización con Docker
- Persistencia con MongoDB