/*
   CREACIÓN DE BASE DE DATOS Y USUARIA
 */

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS gestionhoras;
USE gestionhoras;

-- Crear usuario con contraseña
CREATE USER IF NOT EXISTS 'gestion'@'localhost' IDENTIFIED BY '1234';

-- Asignar permisos completos sobre la base de datos
GRANT ALL PRIVILEGES ON gestionhoras.* TO 'gestion'@'localhost';

-- Aplicar cambios de permisos
FLUSH PRIVILEGES;


/*
   TABLAS Y RELACIONES
*/

-- Tabla: usuarios
DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
  id BIGINT NOT NULL AUTO_INCREMENT,
  activado BIT(1) NOT NULL,
  apellido VARCHAR(255),
  email VARCHAR(255),
  nombre VARCHAR(255),
  password VARCHAR(255),
  imagen_perfil VARCHAR(255),
  username VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla: roles
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  rol_id BIGINT NOT NULL,
  nombre VARCHAR(255),
  PRIMARY KEY (rol_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla: usuario_rol
DROP TABLE IF EXISTS usuario_rol;
CREATE TABLE usuario_rol (
  usuario_rol_id BIGINT NOT NULL AUTO_INCREMENT,
  rol_rol_id BIGINT,
  usuario_id BIGINT,
  PRIMARY KEY (usuario_rol_id),
  FOREIGN KEY (rol_rol_id) REFERENCES roles(rol_id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla: proyectos
DROP TABLE IF EXISTS proyectos;
CREATE TABLE proyectos (
  id_proyecto VARCHAR(255) NOT NULL,
  descripcion_proyecto VARCHAR(255),
  nombre_proyecto VARCHAR(255),
  PRIMARY KEY (id_proyecto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla: usuarios_proyectos
DROP TABLE IF EXISTS usuarios_proyectos;
CREATE TABLE usuarios_proyectos (
  id BIGINT NOT NULL AUTO_INCREMENT,
  proyecto_id_proyecto VARCHAR(255),
  usuario_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (proyecto_id_proyecto) REFERENCES proyectos(id_proyecto),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabla: horas_trabajadas
DROP TABLE IF EXISTS horas_trabajadas;
CREATE TABLE horas_trabajadas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  fecha_inicio DATE,
  numero_horas DOUBLE NOT NULL,
  tipo_horas ENUM('EXTRAS','FESTIVAS','ORDINARIAS'),
  total_horas DOUBLE NOT NULL,
  proyecto_id VARCHAR(255),
  usuario_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (proyecto_id) REFERENCES proyectos(id_proyecto),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
