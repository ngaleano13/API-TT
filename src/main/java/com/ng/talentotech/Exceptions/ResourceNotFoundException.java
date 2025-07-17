package com.ng.talentotech.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String mensaje){
        super(mensaje);
    }
    
}
