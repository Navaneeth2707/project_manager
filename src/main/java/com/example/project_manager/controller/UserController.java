package com.example.project_manager.controller;

import com.example.project_manager.model.UsersEntry;
import com.example.project_manager.repo.UserRepository;
import com.example.project_manager.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public Map<String, String> getProfile(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);

        UsersEntry user = userRepository.findByEmail(email).orElseThrow();

        return Map.of(
                "name", user.getName(),
                "email", user.getEmail()
        );
    }
}
