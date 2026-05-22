# 📋 RESUMEN DE CONFIGURACIÓN - YAML + LOCAL (Sin Docker)

## ✨ ¿Qué se ha configurado?

### 1️⃣ Configuración YAML (Nueva)

Convertimos todas las propiedades a formato YAML moderno:

#### `application.yml` 📄

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sgrh_db
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
  jackson:
    time-zone: UTC

server:
  port: 8080
  servlet:
    context-path: /sgrsh
```

#### `application-local.yml` 📄 (Para desarrollo local)

```yaml
spring:
  jpa:
    show-sql: true # Ver queries
  jackson:
    time-zone: UTC

logging:
  level:
    com.sgrh.sgrh: DEBUG # Logs detallados
    org.hibernate: DEBUG
```

### 2️⃣ Sin Docker - Configuración Local

| Componente        | Antes                  | Ahora              |
| ----------------- | ---------------------- | ------------------ |
| **Database**      | Docker MySQL           | MySQL Local (3306) |
| **Aplicación**    | Docker Container       | Local JVM (8080)   |
| **phpMyAdmin**    | Docker Container       | (No necesario)     |
| **Configuración** | application.properties | application.yml    |

---

## 🚀 Cómo Usar

### Opción A: Script Automático (⭐ Recomendado)

```bash
cd /home/jonathan/Documentos/sgrh/sgrh
chmod +x setup-local.sh
./setup-local.sh
```

**Automatiza:**

- ✅ Verificar Java, Maven, MySQL
- ✅ Crear base de datos
- ✅ Importar datos iniciales
- ✅ Compilar proyecto
- ✅ Verificar archivos YAML

### Opción B: Manual

```bash
# 1. Inicializar BD
mysql -u root -p sgrh_db < database/init.sql

# 2. Compilar
mvn clean install

# 3. Ejecutar
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### Opción C: VS Code (Debug)

1. `Ctrl + Shift + D`
2. Seleccionar **"Spring Boot - Local Profile"**
3. Presionar ▶️

---

## 📊 Archivos Creados/Modificados

### Nuevos Archivos

```
✨ src/main/resources/application.yml
   └─ Configuración principal en YAML

✨ src/main/resources/application-local.yml
   └─ Perfil local (MySQL en localhost, logs detallados)

✨ .vscode/launch.json
   └─ Configuración de Debug para VS Code

✨ setup-local.sh
   └─ Script automático de inicialización

✨ INICIO_RAPIDO.md
   └─ Guía de 5 minutos

✨ GUIA_EJECUCION_LOCAL.md
   └─ Guía completa con ejemplos
```

### Archivos Existentes Preservados

```
✓ application.properties (aún disponible)
✓ pom.xml (sin cambios)
✓ database/init.sql (sin cambios)
✓ docker-compose.yml (desactivado opcionalmente)
```

---

## 🎯 Flujo de Ejecución Local

```
1. MySQL corriendo en localhost:3306
           ↓
2. Ejecutar: mvn spring-boot:run --profile=local
           ↓
3. Spring Boot carga application-local.yml
           ↓
4. Conecta a BD sgrh_db
           ↓
5. Aplicación disponible en http://localhost:8080/sgrsh
           ↓
6. Probar endpoints (POST, GET, PUT, DELETE)
           ↓
7. Ver datos en BD (mysql -u root -p)
```

---

## 🔄 Flujo Completo: Controller → DTO → Mapper → Entity → Repository → Service

### Ejemplo: Crear Cliente

```bash
curl -X POST http://localhost:8080/sgrsh/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@example.com",
    ...
  }'
```

### Lo que sucede internamente

```
1. HTTP POST Request
   └─ URL: /api/v1/clientes
   └─ Body: JSON

2. ClienteController.crearCliente()
   └─ Recibe @RequestBody ClienteDTO
   └─ Valida con @Valid

3. Validación (jakarta.validation)
   ├─ @NotBlank nombre
   ├─ @Email email
   └─ etc.

4. ClienteService.crearCliente(dto)
   └─ Lógica de negocio

5. ClienteMapper.toEntity(dto)
   └─ Convierte DTO → Entity

6. ClienteRepository.save(entity)
   └─ INSERT INTO cliente VALUES(...)

7. Hibernate/JPA
   └─ Ejecuta SQL en MySQL

8. ClienteMapper.toDTO(entity)
   └─ Convierte Entity → DTO

9. HTTP Response 201 Created
   └─ Body: JSON ClienteDTO
```

---

## 📝 Archivos YAML Actuales

### application.yml (Producción)

```yaml
spring.datasource.url: jdbc:mysql://localhost:3306/sgrh_db
spring.datasource.username: root
spring.datasource.password: root
spring.jpa.hibernate.ddl-auto: update
spring.jpa.show-sql: false
logging.level.com.sgrh.sgrh: DEBUG
```

### application-local.yml (Desarrollo)

```yaml
spring.jpa.show-sql: true
logging.level.com.sgrh.sgrh: DEBUG
logging.level.org.hibernate: DEBUG
logging.file.name: logs/sgrsh-local.log
```

---

## ✅ Checklist de Verificación

- [ ] MySQL corriendo (`mysql -u root -p -e "SELECT 1"`)
- [ ] BD creada (`mysql -u root -p -e "SHOW DATABASES"`)
- [ ] Tablas inicializadas (`mysql -u root -p sgrh_db -e "SHOW TABLES"`)
- [ ] Java 21 instalado (`java -version`)
- [ ] Maven 3.9+ instalado (`mvn -version`)
- [ ] Proyecto compilado (`mvn clean install`)
- [ ] Aplicación inicia (`mvn spring-boot:run ...`)
- [ ] Swagger accesible (http://localhost:8080/sgrsh/swagger-ui.html)
- [ ] POST /clientes funciona
- [ ] GET /clientes retorna datos

---

## 🛠️ Comandos Útiles

### Desarrollo

```bash
# Ejecutar con perfil local
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"

# Ejecutar con debug
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local" -DskipTests

# Compilar sin ejecutar
mvn clean compile

# Ver propiedades actuales
mvn help:describe -Dcmd=spring-boot:run
```

### Base de Datos

```bash
# Conectar a MySQL
mysql -u root -p sgrh_db

# Ver tablas
SHOW TABLES;

# Ver datos de cliente
SELECT * FROM cliente;

# Ver estructura de tabla
DESCRIBE cliente;

# Resetear BD
mysql -u root -p -e "DROP DATABASE sgrh_db; CREATE DATABASE sgrh_db;"
mysql -u root -p sgrh_db < database/init.sql
```

### Tests

```bash
# Ejecutar tests unitarios
mvn test

# Ejecutar con cobertura
mvn clean test jacoco:report

# Tests de integración
mvn failsafe:integration-test
```

---

## 📚 Documentación Relacionada

| Documento                   | Propósito                    |
| --------------------------- | ---------------------------- |
| **INICIO_RAPIDO.md**        | ⚡ 5 minutos de inicio       |
| **GUIA_EJECUCION_LOCAL.md** | 📖 Guía completa sin Docker  |
| **ARQUITECTURA.md**         | 🏗️ Diseño del sistema        |
| **RESUMEN_PROYECTO.md**     | 📊 Estadísticas del proyecto |

---

## 🚀 Próximas Acciones

```
✅ 1. Ejecutar setup-local.sh
✅ 2. Iniciar aplicación
✅ 3. Probar endpoints en Swagger
✅ 4. Ver datos en BD
✅ 5. Revisar logs
✅ 6. Implementar más servicios
✅ 7. Agregar autenticación JWT
✅ 8. Escribir tests
```

---

## 💡 Ventajas de esta Configuración

| Aspecto               | Ventaja                                  |
| --------------------- | ---------------------------------------- |
| **YAML**              | Más legible que Properties               |
| **Perfil Local**      | Configuración optimizada para desarrollo |
| **Sin Docker**        | Menos dependencias, más rápido           |
| **MySQL Local**       | Control total de la BD                   |
| **Debug en VSCode**   | Depuración visual intuitiva              |
| **Script Automático** | Setup en 1 comando                       |

---

## ❓ Preguntas Frecuentes

### ¿Dónde está el Docker ahora?

- `docker-compose.yml` sigue en el proyecto pero no se utiliza en el flujo local
- Para usar Docker, ejecutar: `docker-compose up -d`

### ¿Puedo cambiar el puerto?

- Sí, en `application-local.yml`:

```yaml
server:
  port: 8081 # Cambiar a otro puerto
```

### ¿Y la base de datos en producción?

- Usar `application-prod.yml` para producción
- Cambiar credenciales según ambiente

### ¿Cómo veo los logs?

- En terminal: verás los logs en tiempo real
- En archivo: `logs/sgrsh-local.log`

---

## 🎉 ¡Listo!

Tu proyecto SGRSH está configurado para ejecutarse localmente sin Docker:

```
✨ Java 21 LTS
✨ Spring Boot 4.0
✨ MySQL 8.0 Local
✨ YAML Configuration
✨ Debug en VSCode
✨ Script Automático
✨ Documentación Completa
```

**Comienza con:** `./setup-local.sh` o `INICIO_RAPIDO.md`
