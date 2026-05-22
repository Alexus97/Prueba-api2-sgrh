# Guía de Contribución - SGRSH

Primero, ¡gracias por tu interés en contribuir a SGRSH!

## Cómo Contribuir

### 1. Reporte de Bugs

Si encuentras un bug, por favor abre un issue con:

- Descripción clara del problema
- Pasos para reproducirlo
- Comportamiento esperado
- Comportamiento actual
- Tu entorno (OS, Java version, etc.)

### 2. Sugerencias de Mejora

Para sugerir mejoras:

- Describe el problema actual
- Propón una solución
- Proporciona ejemplos si es posible

### 3. Pull Requests

1. **Fork** el repositorio
2. **Clone** tu fork: `git clone https://github.com/tu-usuario/sgrsh.git`
3. **Crea una rama**: `git checkout -b feature/descripcion`
4. **Haz tus cambios** siguiendo las guías de código
5. **Commit**: `git commit -m 'Add: descripción clara'`
6. **Push**: `git push origin feature/descripcion`
7. **Abre un Pull Request**

## Guías de Código

### Estándares Java

- **Java 21 LTS**: Usa `var`, Records, Pattern Matching, Text Blocks
- **Convenciones de Nombres**:
  - Clases: PascalCase
  - Métodos/Variables: camelCase
  - Constantes: UPPER_SNAKE_CASE
- **Documentación**: Comenta código complejo
- **Límites de Línea**: Máximo 120 caracteres

### Estructura de Commits

```
Type: Descripción breve (máximo 50 caracteres)

Descripción detallada si es necesaria.
Explica el "qué" y el "por qué", no el "cómo".

Fixes #123
```

**Tipos de commit**:

- `feat`: Nueva funcionalidad
- `fix`: Corrección de bug
- `docs`: Cambios de documentación
- `style`: Cambios de formato (no afectan código)
- `refactor`: Cambios de código sin cambiar funcionalidad
- `test`: Agregar o actualizar tests
- `chore`: Cambios en build o dependencias

### Ejemplo de Pull Request

```markdown
## Descripción

Agregar servicio de envío de notificaciones por email.

## Tipo de cambio

- [x] Nueva funcionalidad
- [ ] Corrección de bug
- [ ] Cambio en documentación

## Cambios principales

- Crear `NotificacionService` para manejar emails
- Agregar controlador `NotificacionController`
- Integrar con SMTP

## Cómo fue probado

- Tests unitarios para `NotificacionService`
- Test de integración con servidor SMTP mock
- Manual testing en ambiente local

## Checklist

- [x] Código sigue guías de estilo
- [x] Documentación fue actualizada
- [x] Tests fueron agregados
- [x] Tests pasan localmente
```

## Configuración del Entorno

### Requisitos

- Java 21 JDK
- Maven 3.9+
- MySQL 8.0+
- Git

### Instalación

```bash
# Clone
git clone https://github.com/sgrh/sgrsh.git
cd sgrsh

# Build
mvn clean install

# Run
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### IDE Setup

**IntelliJ IDEA**:

1. File → Open → Seleccionar proyecto
2. File → Project Structure → Project SDK → Java 21
3. Plugins: Instalar Lombok Plugin
4. Enable annotation processing: Settings → Compiler → Annotation Processors

**VS Code**:

1. Instalar extensión "Extension Pack for Java"
2. Instalar "Lombok Annotations Support for VS Code"
3. Settings → Java: Home → Ruta a Java 21

## Proceso de Revisión

1. **Automatic Checks**: Se ejecutan tests y análisis de código
2. **Manual Review**: Al menos un maintainer revisará tu PR
3. **Changes Requested**: Si hay cambios necesarios, se notificará
4. **Merge**: Una vez aprobado, se mergeará a main

## Preguntas?

- **Documentación**: Lee [ARQUITECTURA.md](ARQUITECTURA.md)
- **Issues**: Busca issues existentes o abre uno nuevo
- **Discusiones**: Usa las discussions para preguntas generales

## Código de Conducta

- Sé respetuoso
- No hagas spam
- Reporta comportamiento inapropiado a los maintainers

¡Gracias por contribuir!
