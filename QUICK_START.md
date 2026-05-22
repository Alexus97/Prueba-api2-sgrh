# 🚀 Quick Start - SGRSH

Comienza a usar SGRSH en minutos.

## ⚡ 5 Minutos - Docker (Recomendado)

```bash
# 1. Clonar o entrar al directorio
cd /home/jonathan/Documentos/sgrh/sgrh

# 2. Levantar servicios
docker-compose up -d

# 3. Acceder a:
# - API: http://localhost:8080/sgrsh
# - Swagger/Docs: http://localhost:8080/sgrsh/swagger-ui.html
# - phpMyAdmin: http://localhost:8081 (root/root)
```

✅ **Listo en 2 minutos**, todo en Docker

---

## 💻 15 Minutos - Desarrollo Local

### Requisitos

- Java 21 JDK
- Maven 3.9+
- MySQL 8.0+

### Pasos

```bash
# 1. Inicializar BD
mysql -u root -p < database/init.sql

# 2. Compilar
mvn clean install

# 3. Ejecutar
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# 4. Abrir navegador
http://localhost:8080/sgrsh/swagger-ui.html
```

✅ **Listo en 15 minutos**

---

## 📝 Primer Endpoint - Cliente

### Crear Cliente

```bash
curl -X POST http://localhost:8080/sgrsh/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "tipoDocumento": "CC",
    "numeroDocumento": "123456789",
    "telefono": "+57 1 2345678",
    "email": "juan@example.com"
  }'
```

**Respuesta (201 Created):**

```json
{
  "idCliente": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan@example.com",
  ...
}
```

---

## 📚 Endpoints Disponibles

### Clientes

- `POST /api/v1/clientes` - Crear
- `GET /api/v1/clientes` - Listar
- `GET /api/v1/clientes/{id}` - Obtener
- `PUT /api/v1/clientes/{id}` - Actualizar
- `DELETE /api/v1/clientes/{id}` - Eliminar

### Reservas

- `POST /api/v1/reservas` - Crear
- `GET /api/v1/reservas` - Listar
- `GET /api/v1/reservas/{id}` - Obtener
- `PUT /api/v1/reservas/{id}` - Actualizar
- `DELETE /api/v1/reservas/{id}` - Eliminar

### Habitaciones

- `POST /api/v1/habitaciones` - Crear
- `GET /api/v1/habitaciones` - Listar
- `GET /api/v1/habitaciones/{id}` - Obtener
- `PUT /api/v1/habitaciones/{id}` - Actualizar
- `DELETE /api/v1/habitaciones/{id}` - Eliminar

### Empleados

- `POST /api/v1/empleados` - Crear
- `GET /api/v1/empleados` - Listar
- `GET /api/v1/empleados/{id}` - Obtener
- `PUT /api/v1/empleados/{id}` - Actualizar
- `DELETE /api/v1/empleados/{id}` - Eliminar

### Facturas

- `POST /api/v1/facturas` - Crear
- `GET /api/v1/facturas` - Listar
- `GET /api/v1/facturas/{id}` - Obtener
- `PUT /api/v1/facturas/{id}` - Actualizar
- `DELETE /api/v1/facturas/{id}` - Eliminar

### Servicios

- `POST /api/v1/servicios` - Crear
- `GET /api/v1/servicios` - Listar
- `GET /api/v1/servicios/{id}` - Obtener
- `PUT /api/v1/servicios/{id}` - Actualizar
- `DELETE /api/v1/servicios/{id}` - Eliminar

**Nota:** Consultar Swagger para más detalles: http://localhost:8080/sgrsh/swagger-ui.html

---

## 🛠️ Comandos Útiles

### Usando Makefile

```bash
make help              # Ver todos los comandos
make dev               # Ejecutar en desarrollo
make docker-up         # Levantar Docker Compose
make docker-down       # Detener Docker Compose
make docker-logs       # Ver logs
make test              # Ejecutar tests
make build             # Compilar
make clean             # Limpiar
```

### Usando Maven

```bash
mvn clean install              # Compilar e instalar
mvn spring-boot:run            # Ejecutar
mvn test                        # Tests
mvn clean verify               # Verificar
```

### Base de Datos

```bash
# Inicializar
mysql -u root -p < database/init.sql

# Conectar a MySQL
mysql -u root -p sgrh_db

# Ver tablas
SHOW TABLES;

# Ver datos
SELECT * FROM cliente;
```

---

## 🐛 Solución de Problemas

### Puerto 8080 ocupado

```bash
# Encontrar qué usa el puerto
lsof -i :8080

# O cambiar el puerto en application.properties
server.port=8081
```

### BD no conecta

```bash
# Verificar que MySQL esté corriendo
sudo service mysql status

# O iniciar MySQL
sudo service mysql start

# Verificar credenciales en application.properties
# spring.datasource.username=root
# spring.datasource.password=root
```

### Maven no encuentra Java 21

```bash
# Verificar Java
java -version

# Establecer JAVA_HOME
export JAVA_HOME=/path/to/jdk-21

# O en Maven
mvn -version
```

---

## 📖 Documentación

- **Completa**: [ARQUITECTURA.md](ARQUITECTURA.md)
- **Proyecto**: [RESUMEN_PROYECTO.md](RESUMEN_PROYECTO.md)
- **Implementación**: [IMPLEMENTACION_COMPLETADA.md](IMPLEMENTACION_COMPLETADA.md)
- **Contribución**: [CONTRIBUTING.md](CONTRIBUTING.md)
- **API Docs**: Swagger en http://localhost:8080/sgrsh/swagger-ui.html

---

## 🎯 Ejemplo Completo: Crear Reserva

```bash
# 1. Crear cliente (si no existe)
CLIENTE_ID=1

# 2. Crear reserva
curl -X POST http://localhost:8080/sgrsh/api/v1/reservas \
  -H "Content-Type: application/json" \
  -d '{
    "idCliente": 1,
    "fechaReserva": "2024-01-15",
    "fechaEntrada": "2024-01-20",
    "fechaSalida": "2024-01-22",
    "idEstadoReserva": 1
  }'

# 3. Obtener reserva creada
curl http://localhost:8080/sgrsh/api/v1/reservas/1

# 4. Obtener todas las reservas
curl http://localhost:8080/sgrsh/api/v1/reservas

# 5. Actualizar reserva
curl -X PUT http://localhost:8080/sgrsh/api/v1/reservas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "idCliente": 1,
    "fechaReserva": "2024-01-15",
    "fechaEntrada": "2024-01-20",
    "fechaSalida": "2024-01-23",
    "idEstadoReserva": 1
  }'

# 6. Eliminar reserva
curl -X DELETE http://localhost:8080/sgrsh/api/v1/reservas/1
```

---

## 🔐 Seguridad

El proyecto está configurado con:

- ✅ Validación automática de entrada
- ✅ Protección CSRF
- ✅ CORS habilitado
- ✅ Spring Security integrado

---

## 📊 Stack

```
Java 21 LTS
  ↓
Spring Boot 4.0
  ├─ Spring Web
  ├─ Spring Security
  ├─ Spring Data JPA
  └─ Hibernate ORM
  ↓
MySQL 8.0+
```

---

## ✨ Características

- ✅ 23 Entidades
- ✅ 6+ Servicios
- ✅ 6+ Controllers REST
- ✅ Validación automática
- ✅ Documentación Swagger
- ✅ Docker ready
- ✅ Error handling global
- ✅ Transacciones ACID

---

## 🚀 Próximo Paso

Leer [ARQUITECTURA.md](ARQUITECTURA.md) para comprender mejor la estructura del proyecto.

---

**¡Bienvenido a SGRSH!** 🎉
