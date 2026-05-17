package com.duoc.mspagos.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}
