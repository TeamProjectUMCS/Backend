package com.team.backend.repository;

import com.team.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    User save(User user);
    
    void deleteById(Long id);
}
