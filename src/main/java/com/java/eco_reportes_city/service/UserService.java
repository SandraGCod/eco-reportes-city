package com.java.eco_reportes_city.service;

import com.java.eco_reportes_city.entity.User;
import com.java.eco_reportes_city.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registrar(User user) {
        if (userRepository.existsByCorreo(user.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }
        user.setRol("CIUDADANO"); // el rol nunca lo decide el cliente
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}