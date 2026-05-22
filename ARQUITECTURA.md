# SGRSH - Sistema para la Gestión de Reservas y Servicios Hoteleros

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.9%2B-orange.svg)

## Descripción del Proyecto

SGRSH es una plataforma web integral diseñada para la gestión de reservas, habitaciones y servicios hoteleros. La solución proporciona una arquitectura desacoplada, escalable y de alto rendimiento mediante:

- **Backend**: REST API en Spring Boot 4.0 con arquitectura por capas
- **Persistencia**: MySQL con Hibernate/JPA
- **Frontend**: Angular con Tailwind CSS (en desarrollo)
- **Containerización**: Docker para despliegue

## Requisitos Previos

- Java 21 LTS
- Maven 3.9+
- MySQL 8.0+
- Git

## Estructura del Proyecto

```
src/main/java/com/sgrh/sgrh/
├── config/                 # Configuraciones de Spring (Security, OpenAPI, Web)
├── controller/             # REST Controllers (endpoints HTTP)
├── dto/                    # Data Transfer Objects (Records)
├── entity/                 # Entidades JPA
├── exception/              # Excepciones personalizadas y manejo global
├── mapper/                 # Mappers MapStruct (Entity ↔ DTO)
├── repository/             # Spring Data JPA Repositories
├── service/                # Interfaces de servicios
│   └── impl/              # Implementaciones de servicios
└── SgrhApplication.java    # Clase principal de la aplicación

src/main/resources/
├── application.properties           # Configuración principal
├── application-dev.properties      # Perfil de desarrollo
└── application-prod.properties     # Perfil de producción
```

## Arquitectura por Capas

```
HTTP Request
    ↓
[Controller] - Mapeo HTTP, validación
    ↓
[Service] - Lógica de negocio
    ↓
[Repository] - Acceso a datos
    ↓
[Database]
```

### Capas Principales

1. **Controller**: Maneja solicitudes HTTP, mapeo de rutas, validación de entrada
2. **Service**: Contiene lógica de negocio, transacciones, orquestación
3. **Repository**: Acceso a datos mediante Spring Data JPA
4. **Mapper**: Transformación entre Entidades y DTOs usando MapStruct
5. **DTO**: Objetos de transferencia de datos (usados como Records en Java 21)
6. **Entity**: Modelos de base de datos mapeados a JPA

## Entidades Principales

El sistema gestiona 23 entidades:

### Gestión de Clientes y Empleados

- **Cliente**: Información de huéspedes
- **Empleado**: Información de personal hotelero
- **Rol**: Roles de acceso
- **AsignacionRol**: Asignación de roles a empleados

### Gestión de Habitaciones y Reservas

- **SucursalHotel**: Sucursales hoteleras
- **TipoHabitacion**: Categorías de habitaciones
- **Habitacion**: Inventario de habitaciones
- **Reserva**: Reservas de clientes
- **ReservaHabitacion**: Asignación de habitaciones a reservas

### Gestión Operativa

- **CheckIn**: Registro de ingreso
- **CheckOut**: Registro de salida
- **Limpieza**: Limpieza de habitaciones
- **Mantenimiento**: Mantenimiento de habitaciones
- **Estado**: Estados generales del sistema

### Gestión Financiera

- **MetodoPago**: Métodos de pago disponibles
- **Pago**: Pagos realizados
- **Factura**: Facturas emitidas
- **DetalleFactura**: Detalles de facturas

### Gestión de Servicios

- **Servicio**: Servicios hoteleros ofrecidos
- **ConsumoServicio**: Servicios consumidos por huéspedes
- **Promocion**: Promociones activas
- **ServicioPromocion**: Relación entre servicios y promociones

### Opiniones de Clientes

- **OpinionCliente**: Comentarios y calificaciones

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/sgrh/sgrh.git
cd sgrh
```

### 2. Configurar la base de datos

```bash
# Crear la base de datos
mysql -u root -p < database/init.sql
```

### 3. Configurar variables de entorno

Crear archivo `.env` en la raíz del proyecto:

```env
SPRING_PROFILES_ACTIVE=dev
DB_USERNAME=root
DB_PASSWORD=root
JWT_SECRET=tu_secreto_jwt_aqui
```

### 4. Compilar el proyecto

```bash
mvn clean install
```

### 5. Ejecutar la aplicación

```bash
# Modo desarrollo
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# Modo producción
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

## Uso de la API

### Base URL

```
http://localhost:8080/sgrsh/api/v1
```

### Documentación de API (Swagger)

```
http://localhost:8080/sgrsh/swagger-ui.html
```

### Ejemplos de Endpoints

#### Clientes

```bash
# Crear cliente
POST /api/v1/clientes
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "tipoDocumento": "CC",
  "numeroDocumento": "123456789",
  "telefono": "+57 1 2345678",
  "email": "juan@example.com"
}

# Obtener todos los clientes
GET /api/v1/clientes

# Obtener cliente por ID
GET /api/v1/clientes/{id}

# Actualizar cliente
PUT /api/v1/clientes/{id}

# Eliminar cliente
DELETE /api/v1/clientes/{id}
```

#### Reservas

```bash
# Crear reserva
POST /api/v1/reservas

# Obtener reservas por cliente
GET /api/v1/reservas/cliente/{idCliente}

# Obtener reservas por estado
GET /api/v1/reservas/estado/{idEstado}

# Obtener reservas por fechas
GET /api/v1/reservas/fechas?fechaEntrada=2024-01-01&fechaSalida=2024-01-10
```

#### Habitaciones

```bash
# Obtener habitaciones disponibles
GET /api/v1/habitaciones/estado/{idEstado}

# Obtener habitaciones por sucursal
GET /api/v1/habitaciones/sucursal/{idSucursal}
```

## Tecnologías Utilizadas

### Backend

- **Java 21 LTS**: Lenguaje de programación
- **Spring Boot 4.0**: Framework web
- **Spring Security 6**: Autenticación y autorización
- **Spring Data JPA**: Persistencia de datos
- **Hibernate**: ORM
- **Maven**: Gestor de dependencias

### Herramientas

- **MapStruct 1.6.2**: Mapeo de entidades
- **Lombok**: Reducción de boilerplate
- **JWT**: Autenticación con tokens
- **SpringDoc OpenAPI**: Documentación de API
- **MySQL Connector**: Driver MySQL

## Buenas Prácticas Implementadas

### Java 21 LTS

- ✅ Uso de `var` para variables locales
- ✅ Records para DTOs
- ✅ Pattern Matching (switch/instanceof)
- ✅ Text Blocks para SQL y JSON

### Spring Boot 4.0

- ✅ Inyección de dependencias por constructor
- ✅ RestClient en lugar de RestTemplate
- ✅ Spring Security 6 Lambda DSL
- ✅ Validación con `jakarta.validation`

### Arquitectura

- ✅ Separación de capas clara
- ✅ No retorna entidades directamente
- ✅ Mappers para transformación DTO-Entity
- ✅ Servicios como interfaz entre controller y repository
- ✅ Manejo global de excepciones
- ✅ Configuración centralizada

### Seguridad

- ✅ Validación de entrada con `@Valid`
- ✅ Autenticación y autorización
- ✅ CORS configurado
- ✅ Protección CSRF
- ✅ Manejo seguro de contraseñas

## Testing

```bash
# Ejecutar tests
mvn test

# Ejecutar tests con cobertura
mvn test jacoco:report
```

## Docker

### Construir imagen

```bash
docker build -t sgrsh:latest .
```

### Ejecutar contenedor

```bash
docker-compose up -d
```

## Contribución

Para contribuir al proyecto:

1. Fork el repositorio
2. Crear rama de feature (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir Pull Request

## Licencia

Este proyecto está bajo la licencia Apache 2.0. Ver [LICENSE](LICENSE) para detalles.

## Soporte

Para soporte, reportar bugs o sugerencias, por favor abrir un issue en el repositorio.

## Roadmap

- [ ] Implementar autenticación JWT
- [ ] Agregar más servicios (CheckIn, CheckOut, Facturación)
- [ ] Implementar reportes
- [ ] Agregar notificaciones por email
- [ ] Crear frontend Angular
- [ ] Implementar despliegue en Docker Swarm
- [ ] Agregar métricas de monitoreo
- [ ] Implementar cache distribuido

## Autores

- **Equipo de Desarrollo SGRSH**

## Agradecimientos

- Spring Boot Team
- Community de Java
- Todos los contribuyentes
