# 🚀 Guía de Ejecución Local (Sin Docker)

## 📋 Requisitos

- **Java 21 JDK** instalado
- **Maven 3.9+** instalado
- **MySQL 8.0+** corriendo localmente
- **Git** (opcional)

---

## ⚡ Paso 1: Configurar Base de Datos

### 1.1 Iniciar MySQL

```bash
# En macOS (con Homebrew)
brew services start mysql

# En Linux
sudo systemctl start mysql

# En Windows
net start MySQL80
```

### 1.2 Crear Base de Datos e Inicializar

```bash
# Conectar a MySQL
mysql -u root -p

# Dentro de MySQL:
CREATE DATABASE sgrh_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sgrh_db;

# Salir
EXIT;

# Ejecutar script de inicialización
mysql -u root -p sgrh_db < database/init.sql
```

**Verificar:**
```bash
mysql -u root -p
USE sgrh_db;
SHOW TABLES;
SELECT COUNT(*) FROM cliente;
EXIT;
```

---

## ⚡ Paso 2: Compilar el Proyecto

```bash
cd /home/jonathan/Documentos/sgrh/sgrh

# Compilar con Maven
mvn clean install

# O solo compilar
mvn clean compile
```

**Resultado esperado:**
```
[INFO] BUILD SUCCESS
```

---

## ⚡ Paso 3: Ejecutar la Aplicación

### Opción A: Desarrollo Local (Recomendado)

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

**Salida esperada:**
```
2024-05-22 14:30:45 [main] INFO  o.s.b.w.embedded.tomcat.TomcatWebServer - 
Tomcat started on port(s): 8080 (http) with context path '/sgrsh'
2024-05-22 14:30:45 [main] INFO  com.sgrh.sgrh.SgrhApplication - 
Started SgrhApplication in 3.450 seconds
```

### Opción B: Modo Producción

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

### Opción C: Usando Maven wrapper (sin instalar Maven)

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

---

## ✅ Paso 4: Verificar Aplicación

### 4.1 Health Check

```bash
curl http://localhost:8080/sgrsh/actuator/health
```

**Respuesta esperada:**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "MySQL",
        "hello": 1
      }
    }
  }
}
```

### 4.2 Swagger/OpenAPI

Abrir en navegador:
```
http://localhost:8080/sgrsh/swagger-ui.html
```

---

## 🧪 Paso 5: Probar el Flujo Completo

### 5.1 Crear Cliente (POST)

```bash
curl -X POST http://localhost:8080/sgrsh/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "tipoDocumento": "CC",
    "numeroDocumento": "1234567890",
    "telefono": "+57 1 2345678",
    "email": "juan.perez@example.com"
  }'
```

**Respuesta esperada (201 Created):**
```json
{
  "idCliente": 4,
  "nombre": "Juan",
  "apellido": "Pérez",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "telefono": "+57 1 2345678",
  "email": "juan.perez@example.com"
}
```

### 5.2 Obtener Clientes (GET)

```bash
curl http://localhost:8080/sgrsh/api/v1/clientes
```

**Respuesta esperada (200 OK):**
```json
[
  {
    "idCliente": 1,
    "nombre": "Juan",
    "apellido": "Testdata",
    ...
  },
  ...
]
```

### 5.3 Obtener Cliente por ID (GET /{id})

```bash
curl http://localhost:8080/sgrsh/api/v1/clientes/1
```

**Respuesta esperada (200 OK):**
```json
{
  "idCliente": 1,
  "nombre": "Juan",
  ...
}
```

### 5.4 Actualizar Cliente (PUT)

```bash
curl -X PUT http://localhost:8080/sgrsh/api/v1/clientes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Updated",
    "apellido": "Pérez",
    "tipoDocumento": "CC",
    "numeroDocumento": "1234567890",
    "telefono": "+57 1 2345678",
    "email": "juan.updated@example.com"
  }'
```

**Respuesta esperada (200 OK):**
```json
{
  "idCliente": 1,
  "nombre": "Juan Updated",
  ...
}
```

### 5.5 Eliminar Cliente (DELETE)

```bash
curl -X DELETE http://localhost:8080/sgrsh/api/v1/clientes/4
```

**Respuesta esperada (204 No Content):**
(Sin body, solo status 204)

---

## 🔄 Flujo Completo: Controller → DTO → Mapper → Entity → Repository → Service

### Diagrama de Flujo

```
HTTP Request (POST /api/v1/clientes)
        ↓
  ClienteController (recibe JSON)
        ↓
  @Valid ClienteDTO (validación automática)
        ↓
  ClienteService (lógica de negocio)
        ↓
  ClienteMapper (convierte DTO → Entity)
        ↓
  ClienteRepository (persiste en BD)
        ↓
  Entity → Row en tabla cliente
        ↓
  Response: ClienteDTO (200/201)
```

### Ejemplo de Código (Qué sucede internamente)

```java
// 1. REQUEST ENTRA AL CONTROLLER
@PostMapping
public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
    // 2. SERVICE RECIBE EL DTO
    ClienteDTO resultado = clienteService.crearCliente(clienteDTO);
    // 3. RETORNA RESPONSE
    return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
}

// En ClienteServiceImpl:
@Override
public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
    // 4. MAPPER CONVIERTE DTO → ENTITY
    Cliente cliente = clienteMapper.toEntity(clienteDTO);
    
    // 5. REPOSITORY GUARDA EN BD
    Cliente guardado = clienteRepository.save(cliente);
    
    // 6. MAPPER CONVIERTE ENTITY → DTO
    return clienteMapper.toDTO(guardado);
}
```

---

## 📊 Probar Otros Controladores

### Reservas

```bash
# Crear reserva
curl -X POST http://localhost:8080/sgrsh/api/v1/reservas \
  -H "Content-Type: application/json" \
  -d '{
    "idCliente": 1,
    "fechaReserva": "2024-05-22",
    "fechaEntrada": "2024-05-25",
    "fechaSalida": "2024-05-27",
    "idEstadoReserva": 1
  }'

# Obtener reservas
curl http://localhost:8080/sgrsh/api/v1/reservas
```

### Habitaciones

```bash
# Listar habitaciones
curl http://localhost:8080/sgrsh/api/v1/habitaciones

# Obtener habitación
curl http://localhost:8080/sgrsh/api/v1/habitaciones/1
```

### Empleados

```bash
# Crear empleado
curl -X POST http://localhost:8080/sgrsh/api/v1/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos López",
    "telefono": "+57 1 1234567",
    "email": "carlos@hotel.com"
  }'
```

### Facturas

```bash
# Listar facturas
curl http://localhost:8080/sgrsh/api/v1/facturas
```

### Servicios

```bash
# Crear servicio
curl -X POST http://localhost:8080/sgrsh/api/v1/servicios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Servicio de Catering",
    "descripcion": "Servicio de comidas en habitación",
    "precio": 85000
  }'
```

---

## 🐛 Solución de Problemas

### Error: "Can't connect to MySQL server"

```bash
# Verificar que MySQL está corriendo
mysql -u root -p -e "SELECT VERSION();"

# Si no conecta, iniciar MySQL
sudo systemctl restart mysql  # Linux
brew services restart mysql   # macOS
```

### Error: "Database sgrh_db not found"

```bash
# Verificar que la BD existe
mysql -u root -p -e "SHOW DATABASES;"

# Si no existe, crear y inicializar
mysql -u root -p < database/init.sql
```

### Error: "Port 8080 already in use"

```bash
# Ver qué usa el puerto
lsof -i :8080

# O cambiar puerto en application-local.yml
# server.port: 8081
```

### Error: "Java 21 not found"

```bash
# Verificar Java
java -version

# Establecer JAVA_HOME
export JAVA_HOME=/path/to/jdk-21
mvn -version
```

---

## 📝 Verificación en Base de Datos

```bash
# Conectar a MySQL
mysql -u root -p sgrh_db

# Ver datos
SELECT * FROM cliente;
SELECT * FROM reserva;
SELECT * FROM habitacion;

# Verificar tablas
SHOW TABLES;

# Ver estructura de tabla
DESCRIBE cliente;
```

---

## 🎯 Comandos Útiles

```bash
# Compilar sin ejecutar
mvn clean compile

# Ejecutar tests
mvn test

# Ver logs más detallados
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local" -X

# Empaquetar como JAR
mvn clean package

# Limpiar
mvn clean

# Ejecutar con mayor memoria
export MAVEN_OPTS="-Xmx1024m -Xms512m"
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

---

## ✅ Checklist de Verificación

- [ ] MySQL corriendo y accesible
- [ ] Base de datos `sgrh_db` creada
- [ ] Tablas inicializadas con datos de ejemplo
- [ ] Proyecto compilado sin errores
- [ ] Aplicación inicia correctamente
- [ ] Health check responde (status: UP)
- [ ] Swagger accesible en /swagger-ui.html
- [ ] POST /clientes crea cliente correctamente
- [ ] GET /clientes retorna lista
- [ ] GET /clientes/{id} retorna cliente individual
- [ ] PUT /clientes/{id} actualiza cliente
- [ ] DELETE /clientes/{id} elimina cliente

---

## 🚀 Próximos Pasos

1. Explorar otros endpoints en Swagger
2. Revisar logs para ver el flujo
3. Modificar application-local.yml según necesidades
4. Crear más datos de prueba
5. Implementar más servicios
6. Agregar autenticación JWT

---

## 📚 Documentación Relacionada

- [ARQUITECTURA.md](ARQUITECTURA.md)
- [QUICK_START.md](QUICK_START.md)
- [application.yml](src/main/resources/application.yml)
- [application-local.yml](src/main/resources/application-local.yml)

---

**¡Ahora puedes probar la aplicación localmente sin Docker!** 🎉
