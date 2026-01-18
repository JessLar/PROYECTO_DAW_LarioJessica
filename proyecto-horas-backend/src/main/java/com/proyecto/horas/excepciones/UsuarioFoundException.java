package com.proyecto.horas.excepciones;

/**
 * Clase/Excepción lanzada cuando se intenta crear una usuaria que ya existe.
 */
public class UsuarioFoundException extends Exception{

    public UsuarioFoundException(){
        super("El usuario ya existe en la base de datos.");
    }

    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
    
}
