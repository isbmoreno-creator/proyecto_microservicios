# Proyecto Microservicios

Proyecto de arriendo de vehiculos desarrollado con Spring Boot y Maven en microservicios independientes.

## Servicios actuales

| Servicio | Puerto | Responsabilidad |
| --- | --- | --- |
| msclientes | 8081 | Clientes y direcciones |
| msvehiculos | 8082 | Vehiculos y categorias |
| msreservas | 8083 | Reservas y estados de reserva |
| mspagos | 8084 | Pagos asociados a reservas |
| ms-sucursales | 8085 | Sucursales y regiones |
| ms-empleados | 8086 | Empleados |
| ms-reportes | 8087 | Reportes consolidados |

## Ejecucion en IntelliJ IDEA

1. Abrir la carpeta raiz `proyecto_microservicios` en IntelliJ IDEA.
2. Importar cada carpeta de microservicio como proyecto Maven si IntelliJ no lo detecta automaticamente.
3. Crear las bases de datos MySQL usadas por cada servicio.
4. Ejecutar cada clase `*Application` desde IntelliJ o usar el wrapper Maven:

```powershell
.\mvnw.cmd spring-boot:run
```

## Verificacion

Cada microservicio se puede compilar y probar con:

```powershell
.\mvnw.cmd test
```

## Evaluacion Parcial 3

Pendientes principales del checklist:

- Agregar Swagger/OpenAPI a todos los servicios.
- Agregar HATEOAS en endpoints principales.
- Incorporar API Gateway para exponer los servicios desde un unico punto.
- Migrar configuracion de `application.properties` a `application.yml`.
- Aumentar pruebas unitarias de capa Service con JUnit y Mockito.
- Completar o agregar microservicios hasta llegar al minimo requerido por la pauta.
