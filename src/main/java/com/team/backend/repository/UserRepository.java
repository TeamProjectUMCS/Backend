package com.team.backend.repository;

import com.team.backend.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(@NonNull String login);
    void deleteByLogin(@NonNull String login);

    Optional<User> findByUsername(@NonNull String username);
}