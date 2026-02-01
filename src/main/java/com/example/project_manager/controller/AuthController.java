package com.example.project_manager.controller;

import com.example.project_manager.dto.LoginRequest;
import com.example.project_manager.dto.RegisterRequest;
import com.example.project_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody RegisterRequest request) {
        return Map.of("token", authService.register(request));
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        return Map.of("token", authService.login(request));
    }
}
