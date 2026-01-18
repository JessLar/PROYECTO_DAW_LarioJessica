package com.proyecto.horas.configuraciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que trabaja como una excepción para saber si las usuarias estan autorizadas.
 * <p>
 * Esta clase es la encargada de comprobar la autenticación cuando la usuaria
 * no ha iniciado sesión o introduce credenciales inválidas.
 * Devuelve una respuesta JSON con un código HTTP 401 Unhauthorized (acceso no autorizado).
 * </p>
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Método que se invoca cuando una usuaria intenta acceder a un recurso protegido sin estar autenticada.
     *
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @param authException excepción que describe el motivo del fallo de autenticación
     * @throws IOException si ocurre un error de lectura/escritura
     * @throws ServletException si ocurre un error en el procesamiento del servlet
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\"error\": \"Usuario no autorizado\"}");
        writer.flush();
        writer.close();
    }
}
