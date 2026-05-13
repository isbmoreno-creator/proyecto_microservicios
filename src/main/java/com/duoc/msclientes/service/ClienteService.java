package com.duoc.msclientes.service;
import com.duoc.msclientes.model.Cliente;
import com.duoc.msclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }
    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
