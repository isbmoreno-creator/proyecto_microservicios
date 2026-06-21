CREATE TABLE estado_reserva (

     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     nombre VARCHAR(100),
     descripcion VARCHAR(200),
     activo BOOLEAN,
     prioridad INT,
     fecha_estado DATE
);
CREATE TABLE reserva (

      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      fecha_reserva DATE,
      fecha_entrega DATE,
      total DOUBLE,
      activa BOOLEAN,
      cantidad_dias INT,
      sucursal_id INT,
      estado_reserva_id BIGINT,

      CONSTRAINT fk_estado_reserva
      FOREIGN KEY (estado_reserva_id)
      REFERENCES estado_reserva(id)

);
INSERT INTO estado_reserva
(nombre, descripcion, activo, prioridad, fecha_estado)
VALUES
    ('Pendiente', 'Reserva pendiente', true, 1, CURDATE()),
    ('Confirmada', 'Reserva confirmada', true, 2, CURDATE()),
    ('Finalizada', 'Reserva finalizada', false, 3, CURDATE());

INSERT INTO reserva
(fecha_reserva, fecha_entrega, total, activa, cantidad_dias, sucursal_id, estado_reserva_id)
VALUES
    (CURDATE(), CURDATE(), 150000, true, 3, 1, 1),
    (CURDATE(), CURDATE(), 300000, true, 5, 2, 2);
