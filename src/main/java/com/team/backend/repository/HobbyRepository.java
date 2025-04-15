package com.team.backend.repository;

import com.team.backend.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByHobby_name(String hobby_name);

    Optional<Hobby> findById(Long id);
}
