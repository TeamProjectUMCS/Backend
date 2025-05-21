package com.team.backend.repository;

import com.team.backend.model.Hobby;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Hobby> findByHobbyName(String hobbyName);

    List<Hobby> findByHobbyNameContainingIgnoreCase(String hobbyName, Limit limit);
}
