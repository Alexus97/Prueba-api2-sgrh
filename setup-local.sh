#!/bin/bash

# ═══════════════════════════════════════════════════════════════════════════
# SGRSH - Script de Inicialización Local
# Uso: ./setup-local.sh
# ═══════════════════════════════════════════════════════════════════════════

set -e  # Exit on error

# Colores
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔════════════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║         SGRSH - Script de Inicialización Local                    ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════════════════════════╝${NC}"
echo ""

# ═══════════════════════════════════════════════════════════════════════════
# 1. VERIFICAR REQUISITOS
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${YELLOW}[1/5] Verificando requisitos...${NC}"
echo ""

# Verificar Java
if ! command -v java &> /dev/null; then
    echo -e "${RED}✗ Java no está instalado${NC}"
    echo "   Instala Java 21 LTS"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -n 1 | grep -oP '(?<=")\d+(?:\.\d+)*' | head -1)
echo -e "${GREEN}✓ Java $JAVA_VERSION encontrado${NC}"

# Verificar Maven
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}✗ Maven no está instalado${NC}"
    echo "   Instala Maven 3.9+"
    exit 1
fi

MAVEN_VERSION=$(mvn -version 2>&1 | head -n 1 | grep -oP '\d+\.\d+\.\d+')
echo -e "${GREEN}✓ Maven $MAVEN_VERSION encontrado${NC}"

# Verificar MySQL
if ! command -v mysql &> /dev/null; then
    echo -e "${RED}✗ MySQL no está instalado${NC}"
    echo "   Instala MySQL 8.0+"
    exit 1
fi

MYSQL_VERSION=$(mysql --version 2>&1 | grep -oP '\d+\.\d+\.\d+')
echo -e "${GREEN}✓ MySQL $MYSQL_VERSION encontrado${NC}"

echo ""

# ═══════════════════════════════════════════════════════════════════════════
# 2. VERIFICAR CONEXIÓN MYSQL
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${YELLOW}[2/5] Verificando conexión a MySQL...${NC}"

if mysql -u root -proot -e "SELECT 1" &> /dev/null; then
    echo -e "${GREEN}✓ Conectado a MySQL${NC}"
else
    echo -e "${RED}✗ No se puede conectar a MySQL${NC}"
    echo "   Verifica que MySQL está corriendo y credenciales son root/root"
    exit 1
fi

echo ""

# ═══════════════════════════════════════════════════════════════════════════
# 3. CREAR BASE DE DATOS E INICIALIZAR
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${YELLOW}[3/5] Creando base de datos e inicializando datos...${NC}"

# Crear BD
mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS sgrh_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;" 2>/dev/null

if [ -f "database/init.sql" ]; then
    mysql -u root -proot sgrh_db < database/init.sql 2>/dev/null
    echo -e "${GREEN}✓ Base de datos inicializada${NC}"
else
    echo -e "${RED}✗ Archivo database/init.sql no encontrado${NC}"
    exit 1
fi

echo ""

# ═══════════════════════════════════════════════════════════════════════════
# 4. COMPILAR PROYECTO
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${YELLOW}[4/5] Compilando proyecto Maven...${NC}"

if mvn clean install -q -DskipTests; then
    echo -e "${GREEN}✓ Proyecto compilado exitosamente${NC}"
else
    echo -e "${RED}✗ Error al compilar${NC}"
    exit 1
fi

echo ""

# ═══════════════════════════════════════════════════════════════════════════
# 5. VERIFICAR ARCHIVOS YML
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${YELLOW}[5/5] Verificando configuración YAML...${NC}"

if [ -f "src/main/resources/application.yml" ]; then
    echo -e "${GREEN}✓ application.yml${NC}"
else
    echo -e "${RED}✗ application.yml no encontrado${NC}"
fi

if [ -f "src/main/resources/application-local.yml" ]; then
    echo -e "${GREEN}✓ application-local.yml${NC}"
else
    echo -e "${RED}✗ application-local.yml no encontrado${NC}"
fi

echo ""

# ═══════════════════════════════════════════════════════════════════════════
# RESUMEN
# ═══════════════════════════════════════════════════════════════════════════

echo -e "${GREEN}╔════════════════════════════════════════════════════════════════════╗${NC}"
echo -e "${GREEN}║              ✓ CONFIGURACIÓN COMPLETADA                           ║${NC}"
echo -e "${GREEN}╚════════════════════════════════════════════════════════════════════╝${NC}"
echo ""

echo -e "${BLUE}Próximo paso: Ejecutar la aplicación${NC}"
echo ""
echo "  Opción 1 - VS Code (Recomendado):"
echo "    1. Presionar Ctrl + Shift + D"
echo "    2. Seleccionar 'Spring Boot - Local Profile'"
echo "    3. Presionar ▶️"
echo ""
echo "  Opción 2 - Terminal:"
echo "    mvn spring-boot:run -Dspring-boot.run.arguments=\"--spring.profiles.active=local\""
echo ""
echo -e "${BLUE}Acceso a la aplicación:${NC}"
echo "  • API: http://localhost:8080/sgrsh"
echo "  • Swagger: http://localhost:8080/sgrsh/swagger-ui.html"
echo "  • Base de datos: sgrh_db (root/root)"
echo ""

echo -e "${BLUE}Documentación:${NC}"
echo "  • Ver: INICIO_RAPIDO.md"
echo "  • Ver: GUIA_EJECUCION_LOCAL.md"
echo ""
