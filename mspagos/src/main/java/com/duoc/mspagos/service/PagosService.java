package com.duoc.mspagos.service;

import com.duoc.mspagos.client.ReservaClient;
import com.duoc.mspagos.dto.ReservaDTO;
import com.duoc.mspagos.exception.ResourceNotFoundException;
import com.duoc.mspagos.dto.PagosDTO;
import com.duoc.mspagos.dto.PagosRequestDTO;
import com.duoc.mspagos.mapper.PagosMapper;
import com.duoc.mspagos.model.Pagos;
import com.duoc.mspagos.repository.PagosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagosService {

    private final PagosRepository pagosRepository;
    private final PagosMapper pagosMapper;
    private final ReservaClient reservaClient;

    public List<PagosDTO> obtenerTodos() {

        log.info("Obteniendo pagos");

        return pagosRepository.findAll()
                .stream()
                .map(pagosMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagosDTO obtenerPorId(Long id) {

        log.info("Buscando pago por id");

        Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado"));

        return pagosMapper.toDTO(pagos);
    }

    public PagosDTO guardarPago(PagosRequestDTO dto) {

        try {
            log.info("Guardando pago");

            ReservaDTO reserva = reservaClient.getReservaById(dto.getReservaId());

            Pagos pagos = pagosMapper.toEntity(dto);

            Pagos guardado = pagosRepository.save(pagos);

            return pagosMapper.toDTO(guardado);

        } catch (Exception e) {
            log.error("Error al guardar pago", e);
            throw e;
        }
    }
    public PagosDTO actualizarPago(Long id, PagosRequestDTO dto) {

        log.info("Actualizando pago");

        Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado"));

        pagos.setMonto(dto.getMonto());
        pagos.setMetodoPago(dto.getMetodoPago());
        pagos.setFechaPago(dto.getFechaPago());

        Pagos actualizado = pagosRepository.save(pagos);

        return pagosMapper.toDTO(actualizado);
    }

    public void eliminarPago(Long id) {

        log.info("Eliminando pago");

        Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pago no encontrado"));

        pagosRepository.delete(pagos);
    }
}
