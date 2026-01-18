package com.proyecto.horas.excepciones;

/**
 * Clase/Excepción lanzada cuando una usuaria no se encuentra en la base de datos.
 */
public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException(){
        super("El usuario no existe en la base de datos");
    }

    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
