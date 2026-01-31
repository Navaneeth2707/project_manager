package com.example.project_manager.Repo;

import com.example.project_manager.model.UsersEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersEntry, Integer> {

    Optional<UsersEntry> findByEmail(String email);
}
