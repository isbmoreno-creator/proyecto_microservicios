CREATE TABLE IF NOT EXISTS region (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    codigo VARCHAR(20),
    descripcion VARCHAR(255),
    numero_region INT,
    zona_geografica VARCHAR(100),
    activo BOOLEAN,
    fecha_creacion DATE
);

CREATE TABLE IF NOT EXISTS sucursal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    numero VARCHAR(50),
    capacidad INT,
    costo_arriendo DOUBLE,
    operativa BOOLEAN,
    fecha_apertura DATE,
    region_id INT,
    CONSTRAINT fk_sucursal_region
        FOREIGN KEY (region_id)
        REFERENCES region(id)
);

INSERT INTO region
(nombre, codigo, descripcion, numero_region, zona_geografica, activo, fecha_creacion)
VALUES
    ('Valparaiso', 'V', 'Region de Valparaiso', 5, 'Centro', true, '2020-01-01'),
    ('Metropolitana', 'RM', 'Region Metropolitana', 13, 'Centro', true, '2020-01-01'),
    ('Biobio', 'VIII', 'Region del Biobio', 8, 'Sur', true, '2020-01-01');

INSERT INTO sucursal
(nombre, descripcion, numero, capacidad, costo_arriendo, operativa, fecha_apertura, region_id)
VALUES
    ('Sucursal Vina del Mar', 'Sucursal principal de Vina', '001', 50, 100000, true, '2021-03-15', 1),
    ('Sucursal Santiago Centro', 'Sucursal en el centro de Santiago', '002', 80, 150000, true, '2021-06-01', 2),
    ('Sucursal Concepcion', 'Sucursal en Concepcion', '003', 40, 80000, false, '2022-01-10', 3);
