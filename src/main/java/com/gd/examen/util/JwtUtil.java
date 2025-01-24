package com.gd.examen.util;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gd.examen.response.EntFailResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletResponse;

public class JwtUtil {
private JwtUtil() {}
    private static final String SECRET_KEY = "Xra/pB7kzgfgXd7GpWQhXtKu799MfHqG9k4eczWbIZc=";
	private static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


	public static  String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
        		.claims(claims)
        		.subject(username)
        		.issuedAt(new Date())
        		.expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getKey(SECRET_KEY))
                .compact();
        
    }
    

    public static Claims  validateToken(String token,HttpServletResponse response) throws IOException {
    	EntFailResponse failResponse=new EntFailResponse();

    	try {
             return Jwts.parser()
            		.verifyWith(getKey(SECRET_KEY))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            
            failResponse.setCodigo("401");
            failResponse.setDetalles(Arrays.asList("Token expirado: " + e.getMessage()));
            response.getWriter().write(ow.writeValueAsString(failResponse));
            throw e;
        } catch (MalformedJwtException e) {
            failResponse.setDetalles(Arrays.asList("Token mal formado: " + e.getMessage()));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            failResponse.setCodigo("401");
            failResponse.setMensaje("Favor de validar su informacion");
            response.getWriter().write(ow.writeValueAsString(failResponse));
            throw e;
        } catch (SignatureException e) {
            failResponse.setDetalles(Arrays.asList("Firma inválida:  " + e.getMessage()));
            failResponse.setCodigo("401");
            failResponse.setMensaje("Favor de validar su informacion");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write(ow.writeValueAsString(failResponse));
            throw e;
        }
	

    }
    
    public static SecretKey getKey(String base64Secret) {

        byte[] decodedKey = Base64.getDecoder().decode(base64Secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

}