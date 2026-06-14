package com.dhananjay.taskhub.controller;

import com.dhananjay.taskhub.dto.LoginRequestDTO;
import com.dhananjay.taskhub.dto.RegisterRequestDTO;
import com.dhananjay.taskhub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequestDTO request) {

        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(
        @RequestBody LoginRequestDTO request) {

    return authService.loginUser(request);
    }
}