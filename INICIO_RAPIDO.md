# ⚡ INICIO RÁPIDO - Sin Docker (5 minutos)

## 🎯 Objetivo
Probar el flujo completo: **Controller → DTO → Mapper → Entity → Repository → Service**

---

## 📋 Checklist Previo (1 minuto)

- [ ] MySQL 8.0+ corriendo
- [ ] Base de datos `sgrh_db` creada
- [ ] Java 21 instalado (`java -version`)
- [ ] Maven 3.9+ instalado (`mvn -version`)

---

## ✅ Paso 1: Preparar Base de Datos (2 minutos)

### 1.1 Conectar a MySQL
```bash
mysql -u root -p
```

### 1.2 Crear base de datos
```sql
CREATE DATABASE sgrh_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sgrh_db;
EXIT;
```

### 1.3 Importar datos iniciales
```bash
mysql -u root -p sgrh_db < database/init.sql
```

### 1.4 Verificar (opcional)
```bash
mysql -u root -p sgrh_db -e "SHOW TABLES; SELECT COUNT(*) FROM cliente;"
```

---

## 🚀 Paso 2: Ejecutar Aplicación (2 minutos)

### En VS Code (Recomendado)
1. Presionar `Ctrl + Shift + D` (Debug)
2. Seleccionar: **"Spring Boot - Local Profile"**
3. Presionar ▶️ (Play)

### O en Terminal
```bash
cd /home/jonathan/Documentos/sgrh/sgrh
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### Esperado
```
2024-05-22 14:30:45 [main] INFO  com.sgrh.sgrh.SgrhApplication - 
Started SgrhApplication in 3.450 seconds (JVM running for 3.725s)
```

---

## 🧪 Paso 3: Probar Endpoints (1 minuto)

### Opción A: Usar Swagger (interfaz gráfica)
```
http://localhost:8080/sgrsh/swagger-ui.html
```

### Opción B: Usar Terminal (curl)

#### 🔵 Crear Cliente (POST)
```bash
curl -X POST http://localhost:8080/sgrsh/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Test",
    "apellido": "User",
    "tipoDocumento": "CC",
    "numeroDocumento": "9999999999",
    "telefono": "+57 1 1111111",
    "email": "test@example.com"
  }'
```

#### 🟢 Obtener Clientes (GET)
```bash
curl http://localhost:8080/sgrsh/api/v1/clientes
```

#### 🟡 Obtener Cliente por ID (GET /{id})
```bash
curl http://localhost:8080/sgrsh/api/v1/clientes/1
```

#### 🟠 Actualizar Cliente (PUT)
```bash
curl -X PUT http://localhost:8080/sgrsh/api/v1/clientes/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Updated","apellido":"User","tipoDocumento":"CC","numeroDocumento":"9999999999","telefono":"+57 1 1111111","email":"test@example.com"}'
```

#### 🔴 Eliminar Cliente (DELETE)
```bash
curl -X DELETE http://localhost:8080/sgrsh/api/v1/clientes/4
```

---

## 📊 Verificar Configuración YAML

### application.yml (Principal)
```bash
cat src/main/resources/application.yml
```

### application-local.yml (Desarrollo Local)
```bash
cat src/main/resources/application-local.yml
```

---

## 🔄 Flujo Observado en Logs

Cuando ejecutes una solicitud POST, verás en los logs:

```
[ClienteController] recibiendo POST /clientes
[ClienteController] validando DTO
[ClienteService] creando cliente
[ClienteMapper] convirtiendo DTO → Entity
[ClienteRepository] guardando en BD
[Hibernate] INSERT INTO cliente...
[ClienteMapper] convirtiendo Entity → DTO
[ClienteController] retornando respuesta 201
```

---

## 🛑 Si Algo Falla

### "Can't connect to MySQL"
```bash
# Reiniciar MySQL
sudo systemctl restart mysql
# O verificar credenciales en application-local.yml
```

### "Port 8080 occupied"
```bash
# Cambiar puerto en application-local.yml
# server.port: 8081
```

### "Build error"
```bash
# Limpiar y recompilar
mvn clean install -DskipTests
```

---

## 📚 Archivos Clave

| Archivo | Propósito |
|---------|----------|
| `application.yml` | Configuración principal (YAML) |
| `application-local.yml` | Configuración local (YAML) |
| `src/main/java/.../controller/ClienteController.java` | Endpoints REST |
| `src/main/java/.../dto/ClienteDTO.java` | Records con validaciones |
| `src/main/java/.../mapper/ClienteMapper.java` | Mapeo Entity ↔ DTO |
| `src/main/java/.../entity/Cliente.java` | Entidad JPA |
| `src/main/java/.../repository/ClienteRepository.java` | Acceso a datos |
| `src/main/java/.../service/ClienteService.java` | Lógica de negocio |
| `database/init.sql` | Script de inicialización |

---

## ✨ Próximos Pasos

1. ✅ Explorar otros controladores (Reserva, Habitacion, etc.)
2. ✅ Ver cómo los datos se guardan en BD
3. ✅ Revisar mapeos en MapStruct
4. ✅ Entender validaciones de DTOs
5. ✅ Implementar más servicios

---

## 📞 Soporte

- **Guía Completa**: [GUIA_EJECUCION_LOCAL.md](GUIA_EJECUCION_LOCAL.md)
- **Arquitectura**: [ARQUITECTURA.md](ARQUITECTURA.md)
- **Swagger Live**: http://localhost:8080/sgrsh/swagger-ui.html

---

**¡Listo! Tu aplicación SGRSH está ejecutándose localmente sin Docker** 🎉

```
✅ MySQL             → Corriendo
✅ Aplicación        → Corriendo
✅ API REST          → Disponible
✅ Swagger UI        → Disponible
✅ Flujo Completo    → Funcional
```
