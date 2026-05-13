package com.duoc.msclientes.controller;


import com.duoc.msclientes.model.Direccion;
import com.duoc.msclientes.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public List<Direccion> listarDirecciones(){
        return direccionService.obtenerDirecciones();
    }
    @PostMapping
    public Direccion guardarDireccion(@RequestBody Direccion direccion){
        return direccionService.guardarDireccion(direccion);
    }
}
