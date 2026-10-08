package com.java.eco_reportes_city.service;

import com.java.eco_reportes_city.dto.AuthResponse;
import com.java.eco_reportes_city.dto.LoginRequest;
import com.java.eco_reportes_city.dto.UserResponse;
import com.java.eco_reportes_city.entity.User;
import com.java.eco_reportes_city.repository.UserRepository;
import com.java.eco_reportes_city.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByCorreo(req.correo())
                .filter(u -> passwordEncoder.matches(req.password(), u.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Correo o contraseña incorrectos"));

        UserResponse usuario = new UserResponse(user.getId(), user.getNombre(), user.getCorreo(), user.getRol());
        return new AuthResponse(jwtService.generarToken(user), usuario);
    }
}