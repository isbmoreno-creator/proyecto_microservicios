CREATE TABLE pagos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    monto DOUBLE,
    metodo_pago VARCHAR(100),
    pagado BOOLEAN,
    fecha_pago DATE,
    numero_cuotas INT,
    reserva_id BIGINT
);

INSERT INTO pagos (monto, metodo_pago, pagado, fecha_pago, numero_cuotas, reserva_id)
VALUES
    (150000, 'Tarjeta', true, '2026-05-10', 3, 1),
    (200000, 'Transferencia', true, '2026-05-11', 1, 2),
    (300000, 'Efectivo', false, '2026-05-12', 2, 3);
