-- ===================================================================
-- SGRSH - Sistema para la Gestión de Reservas y Servicios Hoteleros
-- Base de Datos MySQL
-- ===================================================================

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS sgrh_db;
USE sgrh_db;

-- ===================================================================
-- TABLA: CLIENTE
-- ===================================================================
CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    numero_documento VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_numero_documento (numero_documento),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: ESTADO
-- ===================================================================
CREATE TABLE IF NOT EXISTS estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(30) NOT NULL,
    descripcion VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_tipo (tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: SUCURSAL_HOTEL
-- ===================================================================
CREATE TABLE IF NOT EXISTS sucursal_hotel (
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(100) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: TIPO_HABITACION
-- ===================================================================
CREATE TABLE IF NOT EXISTS tipo_habitacion (
    id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL UNIQUE,
    precio_base DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: HABITACION
-- ===================================================================
CREATE TABLE IF NOT EXISTS habitacion (
    id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo INT NOT NULL,
    id_sucursal INT NOT NULL,
    numero INT NOT NULL,
    capacidad INT NOT NULL,
    precio_noche DECIMAL(10,2) NOT NULL,
    id_estado_habitacion INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo) REFERENCES tipo_habitacion(id_tipo),
    FOREIGN KEY (id_sucursal) REFERENCES sucursal_hotel(id_sucursal),
    FOREIGN KEY (id_estado_habitacion) REFERENCES estado(id_estado),
    INDEX idx_numero (numero),
    INDEX idx_sucursal (id_sucursal),
    INDEX idx_estado (id_estado_habitacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: RESERVA
-- ===================================================================
CREATE TABLE IF NOT EXISTS reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    id_estado_reserva INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_estado_reserva) REFERENCES estado(id_estado),
    INDEX idx_cliente (id_cliente),
    INDEX idx_estado (id_estado_reserva),
    INDEX idx_fechas (fecha_entrada, fecha_salida)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: RESERVA_HABITACION
-- ===================================================================
CREATE TABLE IF NOT EXISTS reserva_habitacion (
    id_reserva_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_habitacion INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva) ON DELETE CASCADE,
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
    INDEX idx_reserva (id_reserva),
    INDEX idx_habitacion (id_habitacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: EMPLEADO
-- ===================================================================
CREATE TABLE IF NOT EXISTS empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: ROLES
-- ===================================================================
CREATE TABLE IF NOT EXISTS roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nombre (nombre_rol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: ASIGNACION_ROL
-- ===================================================================
CREATE TABLE IF NOT EXISTS asignacion_rol (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado INT NOT NULL,
    id_rol INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado) ON DELETE CASCADE,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    INDEX idx_empleado (id_empleado),
    INDEX idx_rol (id_rol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: CHECK_IN
-- ===================================================================
CREATE TABLE IF NOT EXISTS check_in (
    id_check_in INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_empleado INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    INDEX idx_reserva (id_reserva),
    INDEX idx_empleado (id_empleado),
    INDEX idx_fecha (fecha_hora)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: CHECK_OUT
-- ===================================================================
CREATE TABLE IF NOT EXISTS check_out (
    id_check_out INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_empleado INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    INDEX idx_reserva (id_reserva),
    INDEX idx_empleado (id_empleado),
    INDEX idx_fecha (fecha_hora)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: METODO_PAGO
-- ===================================================================
CREATE TABLE IF NOT EXISTS metodo_pago (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: PAGO
-- ===================================================================
CREATE TABLE IF NOT EXISTS pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_empleado INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    fecha_pago DATE NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_metodo_pago) REFERENCES metodo_pago(id_metodo_pago),
    INDEX idx_reserva (id_reserva),
    INDEX idx_fecha (fecha_pago)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: FACTURA
-- ===================================================================
CREATE TABLE IF NOT EXISTS factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    fecha_emision DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    INDEX idx_reserva (id_reserva),
    INDEX idx_fecha (fecha_emision)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: SERVICIO
-- ===================================================================
CREATE TABLE IF NOT EXISTS servicio (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(100),
    precio DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: PROMOCION
-- ===================================================================
CREATE TABLE IF NOT EXISTS promocion (
    id_promocion INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    descuento DECIMAL(5,2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_fechas (fecha_inicio, fecha_fin)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: SERVICIO_PROMOCION
-- ===================================================================
CREATE TABLE IF NOT EXISTS servicio_promocion (
    id_servicio INT NOT NULL,
    id_promocion INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_servicio, id_promocion),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio) ON DELETE CASCADE,
    FOREIGN KEY (id_promocion) REFERENCES promocion(id_promocion) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: DETALLE_FACTURA
-- ===================================================================
CREATE TABLE IF NOT EXISTS detalle_factura (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT NOT NULL,
    id_servicio INT,
    id_promocion INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_factura) REFERENCES factura(id_factura) ON DELETE CASCADE,
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio),
    FOREIGN KEY (id_promocion) REFERENCES promocion(id_promocion),
    INDEX idx_factura (id_factura)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: CONSUMO_SERVICIO
-- ===================================================================
CREATE TABLE IF NOT EXISTS consumo_servicio (
    id_consumo INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT NOT NULL,
    id_servicio INT NOT NULL,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio),
    INDEX idx_reserva (id_reserva),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: LIMPIEZA
-- ===================================================================
CREATE TABLE IF NOT EXISTS limpieza (
    id_limpieza INT AUTO_INCREMENT PRIMARY KEY,
    id_habitacion INT NOT NULL,
    id_empleado INT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    INDEX idx_habitacion (id_habitacion),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: MANTENIMIENTO
-- ===================================================================
CREATE TABLE IF NOT EXISTS mantenimiento (
    id_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_habitacion INT NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    id_estado_mantenimiento INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
    FOREIGN KEY (id_estado_mantenimiento) REFERENCES estado(id_estado),
    INDEX idx_habitacion (id_habitacion),
    INDEX idx_fecha (fecha)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- TABLA: OPINION_CLIENTE
-- ===================================================================
CREATE TABLE IF NOT EXISTS opinion_cliente (
    id_opinion INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_reserva INT NOT NULL,
    comentario TEXT,
    calificacion INT NOT NULL,
    fecha DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    INDEX idx_cliente (id_cliente),
    INDEX idx_reserva (id_reserva),
    INDEX idx_calificacion (calificacion)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================================
-- DATOS INICIALES
-- ===================================================================

-- Insertar Estados
INSERT INTO estado (tipo, descripcion) VALUES
('RESERVA_ACTIVA', 'Reserva activa'),
('RESERVA_CANCELADA', 'Reserva cancelada'),
('HABITACION_DISPONIBLE', 'Habitación disponible'),
('HABITACION_OCUPADA', 'Habitación ocupada'),
('HABITACION_MANTENIMIENTO', 'En mantenimiento'),
('MANTENIMIENTO_PENDIENTE', 'Pendiente'),
('MANTENIMIENTO_EN_PROCESO', 'En proceso'),
('MANTENIMIENTO_COMPLETADO', 'Completado');

-- Insertar Métodos de Pago
INSERT INTO metodo_pago (descripcion) VALUES
('Efectivo'),
('Tarjeta de Crédito'),
('Tarjeta de Débito'),
('Transferencia Bancaria'),
('Billetera Digital');

-- Insertar Roles
INSERT INTO roles (nombre_rol) VALUES
('ADMIN'),
('GERENTE'),
('RECEPCIONISTA'),
('MUCAMA'),
('CHEF'),
('SEGURIDAD');

-- Insertar Sucursal Hotel
INSERT INTO sucursal_hotel (nombre, direccion, ciudad) VALUES
('Hotel Central', 'Carrera 10 #50-30', 'Bogotá'),
('Hotel Playa', 'Calle 1 #1-1', 'Cartagena'),
('Hotel Montaña', 'Transversal 5 #20-40', 'Medellín');

-- Insertar Tipos de Habitación
INSERT INTO tipo_habitacion (descripcion, precio_base) VALUES
('Estándar', 150000),
('Doble', 200000),
('Suite', 350000),
('Penthouse', 500000);

-- Insertar Habitaciones (10 habitaciones por sucursal)
INSERT INTO habitacion (id_tipo, id_sucursal, numero, capacidad, precio_noche, id_estado_habitacion) VALUES
-- Hotel Central
(1, 1, 101, 1, 150000, 3),
(1, 1, 102, 1, 150000, 3),
(2, 1, 201, 2, 200000, 3),
(2, 1, 202, 2, 200000, 3),
(3, 1, 301, 3, 350000, 3),
(3, 1, 302, 3, 350000, 4),
(4, 1, 401, 4, 500000, 3),
(1, 1, 103, 1, 150000, 3),
(2, 1, 203, 2, 200000, 3),
(3, 1, 303, 3, 350000, 3);

-- Insertar Servicios
INSERT INTO servicio (nombre, descripcion, precio) VALUES
('Desayuno', 'Desayuno buffet', 50000),
('Spa', 'Servicio de spa y relajación', 150000),
('Restaurante', 'Almuerzo en restaurante', 75000),
('Gimnasio', 'Acceso a gimnasio', 30000),
('Transporte', 'Servicio de transporte al aeropuerto', 100000),
('Lavandería', 'Servicio de lavandería', 40000);

-- Insertar Clientes de ejemplo
INSERT INTO cliente (nombre, apellido, tipo_documento, numero_documento, telefono, email) VALUES
('Juan', 'Pérez', 'CC', '12345678', '3001234567', 'juan@example.com'),
('María', 'García', 'CC', '87654321', '3107654321', 'maria@example.com'),
('Carlos', 'López', 'CC', '11111111', '3051111111', 'carlos@example.com');

-- Insertar Empleados de ejemplo
INSERT INTO empleado (nombre, telefono, email) VALUES
('Ana Martínez', '3002222222', 'ana@hotel.com'),
('Roberto Silva', '3103333333', 'roberto@hotel.com'),
('Laura Rodríguez', '3054444444', 'laura@hotel.com');

-- Insertar Asignaciones de Rol
INSERT INTO asignacion_rol (id_empleado, id_rol) VALUES
(1, 2),  -- Ana - Gerente
(2, 3),  -- Roberto - Recepcionista
(3, 4);  -- Laura - Mucama

-- ===================================================================
-- FIN DEL SCRIPT DE INICIALIZACIÓN
-- ===================================================================
