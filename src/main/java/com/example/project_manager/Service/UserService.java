package com.example.project_manager.Service;

import com.example.project_manager.Repo.UserRepository;
import com.example.project_manager.dto.LoginRequest;
import com.example.project_manager.dto.RegisterRequest;
import com.example.project_manager.model.UsersEntry;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        UsersEntry user = new UsersEntry();
        user.setFirstName(request.getUsername());
        user.setEmail(request.getUsername());
        user.setPassword(request.getPassword()); // plain for now

        userRepository.save(user);
    }

    public void login(LoginRequest request) {

        UsersEntry user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new SecurityException("Invalid password");
        }
    }
}
