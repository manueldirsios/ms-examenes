package com.gd.examen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gd.examen.request.LoginRequest;
import com.gd.examen.response.AuthResponse;
import com.gd.examen.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        // Validar usuario (ejemplo simple)
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}