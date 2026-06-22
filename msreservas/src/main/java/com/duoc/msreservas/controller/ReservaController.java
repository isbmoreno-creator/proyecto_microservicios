package com.duoc.msreservas.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import com.duoc.msreservas.dto.ReservaDTO;
import com.duoc.msreservas.dto.ReservaRequestDTO;
import com.duoc.msreservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@Tag(name = "Reservas", description = "API de gestión de reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Operation(summary = "Listar todas las reservas")
    @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de reservas correctamente")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ReservaDTO>>> listarReservas() {
    List<ReservaDTO> reservas = reservaService.obtenerReservas();
    List<EntityModel<ReservaDTO>> listaConLinks = new ArrayList<>();
    for (ReservaDTO reserva : reservas) {
        EntityModel<ReservaDTO> recurso = EntityModel.of(reserva);
        recurso.add(linkTo(methodOn(ReservaController.class)
                .obtenerReservaPorId(reserva.getId())).withSelfRel());
        listaConLinks.add(recurso);
    }

    CollectionModel<EntityModel<ReservaDTO>> coleccion = CollectionModel.of(listaConLinks);

    coleccion.add(linkTo(methodOn(ReservaController.class)
            .listarReservas()).withSelfRel());
        return ResponseEntity.ok(coleccion);
    }

    @Operation(summary = "Obtener reservas por sucursal")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Las reservas han sido encontradas"),
            @ApiResponse(responseCode = "404", description = "No se han encontrado las reservas")})
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorSucursal(
            @PathVariable Integer sucursalId) {

        return ResponseEntity.ok(
                reservaService.obtenerReservasPorSucursal(sucursalId));
    }

    @Operation(summary = "Crear una nueva reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "La reserva ha sido creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")})
    @PostMapping
    public ResponseEntity<EntityModel<ReservaDTO>> guardarReserva(
            @Valid @RequestBody ReservaRequestDTO dto) {

        ReservaDTO reservaCreada = reservaService.guardarReserva(dto);
        EntityModel<ReservaDTO> recurso = EntityModel.of(reservaCreada);

        recurso.add(linkTo(methodOn(ReservaController.class)
                .obtenerReservaPorId(reservaCreada.getId())).withSelfRel());
        recurso.add(linkTo(methodOn(ReservaController.class)
                .listarReservas()).withRel("todas_las_reservas"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recurso);
    }

    @Operation(summary = "Buscar una reserva por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La reserva ha sido encontrada"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la reserva")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReservaDTO>> obtenerReservaPorId(
            @PathVariable Long id) {
        ReservaDTO reserva = reservaService.obtenerReservaPorId(id);
        EntityModel<ReservaDTO> recurso = EntityModel.of(reserva);

        recurso.add(linkTo(methodOn(ReservaController.class)
                .obtenerReservaPorId(id)).withSelfRel());

        recurso.add(linkTo(methodOn(ReservaController.class)
                .listarReservas()).withRel("todas_las_reservas"));
        return ResponseEntity.ok(recurso);
    }

    @Operation(summary = "Actualizar una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Se ha actualizado la reserva"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la reserva"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ReservaDTO>> actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto) {

        ReservaDTO reservaActualizada = reservaService.actualizarReserva(id, dto);

        EntityModel<ReservaDTO> recurso = EntityModel.of(reservaActualizada);

        recurso.add(linkTo(methodOn(ReservaController.class)
                .obtenerReservaPorId(id)).withSelfRel());
        recurso.add(linkTo(methodOn(ReservaController.class)
                .listarReservas()).withRel("todas_las_reservas"));

        return ResponseEntity.ok(recurso);
    }

    @Operation(summary = "Eliminar una reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Se ha eliminado la reserva"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la reserva")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(
            @PathVariable Long id) {

        reservaService.eliminarReserva(id);

        return ResponseEntity.noContent().build();
    }
}
