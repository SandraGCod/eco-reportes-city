package com.java.eco_reportes_city.controller;

import com.java.eco_reportes_city.dto.RegistroRequest;
import com.java.eco_reportes_city.dto.UserResponse;
import com.java.eco_reportes_city.entity.User;
import com.java.eco_reportes_city.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registro")
    public ResponseEntity<UserResponse> registrar(@Valid @RequestBody RegistroRequest req) {
        User nuevo = userService.registrar(User.builder()
                .nombre(req.nombre())
                .correo(req.correo())
                .password(req.password())
                .build());

        UserResponse respuesta = new UserResponse(nuevo.getId(), nuevo.getNombre(), nuevo.getCorreo(), nuevo.getRol());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}