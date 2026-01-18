package com.proyecto.horas.modelo;

/**
 * Objeto que recibe las credenciales de login.
 */
public class JwtRequest {

    private String username;
    private String password;

    /**
     * Constructor vacío necesario para deserialización.
     */
    public JwtRequest(){
    }

    /**
     * Constructor que crea una petición de login con usuaria y contraseña.
     *
     * @param username
     * @param password
     */
    public JwtRequest(String username, String password){
        this.username=username;
        this.password=password;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
