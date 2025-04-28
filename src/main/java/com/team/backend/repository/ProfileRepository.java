package com.team.backend.repository;

import com.team.backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findProfileByUserUsername(String username);

    @Query("SELECT p.profilePictureUrl FROM Profile p WHERE p.user.username = :username")
    Optional<String> findProfilePictureUrlByUserUsername(@Param("username") String username);
}