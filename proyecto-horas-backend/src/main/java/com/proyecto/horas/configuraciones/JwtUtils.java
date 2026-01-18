package com.proyecto.horas.configuraciones;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Clase de utilidad para la generación, validación y extracción de información de tokens JWT.
 * <p>
 * Proporciona métodos para obtener la usuaria, fecha de expiración, crear nuevos tokens
 * y validarlos.
 * </p>
 */
@Component
public class JwtUtils {
    /**
     * Clave secreta utilizada para firmar los tokens JWT.
     */
    private String SECRET_KEY = "secret";

    /**
     * Extrae el nombre de usuario contenido en el token.
     *
     * @param token token JWT
     * @return nombre de usuaria
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Obtiene la fecha de expiración del token.
     *
     * @param token token JWT
     * @return fecha de expiración
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un dato específico del token usando una función resolutora.
     *
     * @param token          token JWT
     * @param claimsResolver función que extrae un valor de los claims (campos del token)
     * @param <T>            tipo del valor retornado
     * @return valor extraído
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene todos los claims del token.
     *
     * @param token token JWT
     * @return claims del token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Verifica si el token ha expirado.
     *
     * @param token token JWT
     * @return true si está expirado, false si aún es válido
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un nuevo token JWT para una usuaria.
     *
     * @param userDetails datos de la usuaria
     * @return token generado
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crea un token JWT con claims personalizados.
     *
     * @param claims  mapa de claims
     * @param subject usuaria propietaria del token
     * @return token JWT firmado
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Valida que el token pertenezca a la usuaria y no esté expirado.
     *
     * @param token       token JWT
     * @param userDetails datos dela usuaria
     * @return true si es válido, false en caso contrario
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
