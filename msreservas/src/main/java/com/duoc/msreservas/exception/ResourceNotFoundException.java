package com.duoc.msreservas.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}
