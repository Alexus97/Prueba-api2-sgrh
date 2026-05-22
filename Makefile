.PHONY: help install build test run dev prod clean docker-up docker-down logs

# Variables
MAVEN := mvn
DOCKER_COMPOSE := docker-compose
PROJECT_NAME := SGRSH

help:
	@echo "╔════════════════════════════════════════════════════════════════════╗"
	@echo "║           SGRSH - Sistema de Gestión Hotelera                     ║"
	@echo "║                    Comandos Disponibles                           ║"
	@echo "╚════════════════════════════════════════════════════════════════════╝"
	@echo ""
	@echo "📦 BUILD & INSTALL"
	@echo "  make install          - Instalar dependencias"
	@echo "  make build            - Compilar proyecto"
	@echo "  make clean            - Limpiar archivos compilados"
	@echo ""
	@echo "🧪 TESTING"
	@echo "  make test             - Ejecutar tests"
	@echo "  make test-coverage    - Tests con cobertura"
	@echo ""
	@echo "🚀 EJECUCIÓN"
	@echo "  make dev              - Ejecutar en modo desarrollo"
	@echo "  make prod             - Ejecutar en modo producción"
	@echo "  make run              - Ejecutar (uso general)"
	@echo ""
	@echo "🐳 DOCKER"
	@echo "  make docker-build     - Construir imagen Docker"
	@echo "  make docker-up        - Levanter containers (docker-compose)"
	@echo "  make docker-down      - Detener containers"
	@echo "  make docker-logs      - Ver logs de containers"
	@echo ""
	@echo "📚 DOCUMENTACIÓN"
	@echo "  make docs             - Abrir documentación"
	@echo "  make swagger          - Abrir Swagger UI"
	@echo ""
	@echo "🗄️  BASE DE DATOS"
	@echo "  make db-init          - Inicializar base de datos"
	@echo "  make db-reset         - Resetear base de datos"
	@echo ""

# ═══════════════════════════════════════════════════════════════════════════
# BUILD & INSTALL
# ═══════════════════════════════════════════════════════════════════════════

install:
	@echo "📦 Instalando dependencias..."
	$(MAVEN) dependency:go-offline

build:
	@echo "🔨 Compilando proyecto..."
	$(MAVEN) clean install

clean:
	@echo "🧹 Limpiando archivos compilados..."
	$(MAVEN) clean
	rm -rf target/

# ═══════════════════════════════════════════════════════════════════════════
# TESTING
# ═══════════════════════════════════════════════════════════════════════════

test:
	@echo "🧪 Ejecutando tests..."
	$(MAVEN) test

test-coverage:
	@echo "📊 Ejecutando tests con cobertura..."
	$(MAVEN) test jacoco:report

# ═══════════════════════════════════════════════════════════════════════════
# EJECUCIÓN
# ═══════════════════════════════════════════════════════════════════════════

dev:
	@echo "🚀 Iniciando modo DESARROLLO..."
	@echo "📍 URL: http://localhost:8080/sgrsh"
	@echo "📚 Swagger: http://localhost:8080/sgrsh/swagger-ui.html"
	$(MAVEN) spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

prod:
	@echo "🚀 Iniciando modo PRODUCCIÓN..."
	$(MAVEN) spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

run: dev

# ═══════════════════════════════════════════════════════════════════════════
# DOCKER
# ═══════════════════════════════════════════════════════════════════════════

docker-build:
	@echo "🐳 Construyendo imagen Docker..."
	docker build -t sgrsh:latest .

docker-up:
	@echo "⬆️  Levantando containers..."
	$(DOCKER_COMPOSE) up -d
	@echo ""
	@echo "✅ Containers levantados"
	@echo "📍 API: http://localhost:8080/sgrsh"
	@echo "📚 Swagger: http://localhost:8080/sgrsh/swagger-ui.html"
	@echo "🗄️  phpMyAdmin: http://localhost:8081"
	@echo "   Usuario: root"
	@echo "   Contraseña: root"

docker-down:
	@echo "⬇️  Deteniendo containers..."
	$(DOCKER_COMPOSE) down

docker-logs:
	@echo "📋 Mostrando logs..."
	$(DOCKER_COMPOSE) logs -f app

docker-restart: docker-down docker-up

# ═══════════════════════════════════════════════════════════════════════════
# DOCUMENTACIÓN
# ═══════════════════════════════════════════════════════════════════════════

docs:
	@echo "📖 Abriendo documentación..."
	@open ARQUITECTURA.md || xdg-open ARQUITECTURA.md

swagger:
	@echo "📚 Abriendo Swagger UI..."
	@sleep 2
	@open http://localhost:8080/sgrsh/swagger-ui.html || xdg-open http://localhost:8080/sgrsh/swagger-ui.html

# ═══════════════════════════════════════════════════════════════════════════
# BASE DE DATOS
# ═══════════════════════════════════════════════════════════════════════════

db-init:
	@echo "🗄️  Inicializando base de datos..."
	mysql -u root -p < database/init.sql
	@echo "✅ Base de datos inicializada"

db-reset:
	@echo "⚠️  Reseteando base de datos..."
	mysql -u root -p -e "DROP DATABASE IF EXISTS sgrh_db;"
	$(MAKE) db-init

# ═══════════════════════════════════════════════════════════════════════════
# UTILIDADES
# ═══════════════════════════════════════════════════════════════════════════

version:
	@echo "$(PROJECT_NAME) - Sistema de Gestión Hotelera"
	@$(MAVEN) -v
	@java -version

format:
	@echo "📝 Formateando código..."
	$(MAVEN) spotless:apply

check:
	@echo "✅ Verificando proyecto..."
	$(MAVEN) verify

.DEFAULT_GOAL := help
