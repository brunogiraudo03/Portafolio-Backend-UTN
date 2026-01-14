DROP TABLE IF EXISTS ejercicio_plan;
DROP TABLE IF EXISTS abonado;
DROP TABLE IF EXISTS sucursal;
CREATE TABLE sucursal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);
CREATE TABLE abonado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(30),
    precio DECIMAL(10, 2) NOT NULL,
    sucursal_id INT NOT NULL,
    CONSTRAINT fk_abonado_sucursal FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);
CREATE TABLE ejercicio_plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_maquina VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    series INT NOT NULL,
    repeticiones INT NOT NULL,
    abonado_id INT NOT NULL,
    CONSTRAINT fk_ejercicio_abonado FOREIGN KEY (abonado_id) REFERENCES abonado(id)
);