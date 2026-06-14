package com.dhananjay.taskhub.service;

import com.dhananjay.taskhub.dto.LoginRequestDTO;
import com.dhananjay.taskhub.dto.RegisterRequestDTO;
import com.dhananjay.taskhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.dhananjay.taskhub.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dhananjay.taskhub.security.JwtService;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                   PasswordEncoder passwordEncoder,
                   JwtService jwtService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    }
    public String registerUser(RegisterRequestDTO request) {

    if (userRepository.existsByUsername(request.getUsername())) {
        return "Username already exists";
    }

    if (userRepository.existsByEmail(request.getEmail())) {
        return "Email already exists";
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(
      passwordEncoder.encode(request.getPassword())
    );

    userRepository.save(user);

    return "User registered successfully";
    }

    public String loginUser(LoginRequestDTO request) {

    User user = userRepository.findByUsername(
            request.getUsername())
            .orElse(null);

    if (user == null) {
        return "Invalid username or password";
    }

    boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );

    if (!passwordMatches) {
        return "Invalid username or password";
    }

    return jwtService.generateToken(user.getUsername());
    }
}