package com.java.eco_reportes_city.service;

import com.java.eco_reportes_city.entity.User;
import com.java.eco_reportes_city.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registrar(User user) {
        if (userRepository.existsByCorreo(user.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }
        if (user.getRol() == null) {
            user.setRol("CIUDADANO");
        }
        return userRepository.save(user);
    }
}