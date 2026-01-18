package com.proyecto.horas.modelo;

/**
 * Respuesta que devuelve el token JWT tras un login correcto.
 */
public class JwtResponse {
    private String token;

    /**
     * Constructor vacío necesario para deserialización.
     */
    public JwtResponse() {
    }
    /**
     * Constructor que crea una respuesta con el token generado.
     *
     * @param token
     */
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
