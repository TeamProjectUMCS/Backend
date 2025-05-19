package com.team.backend.repository;

import com.team.backend.model.Match;
import com.team.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Match save(Match match);

    List<Match> findByFirstUserId(Long firstUserId);

    List<Match> findBySecondUserId(Long secondUserId);

    @Query("SELECT DISTINCT m FROM Match m WHERE m.firstUser = :user OR m.secondUser = :user")
    List<Match> findAllMatchesForUser(@Param("user") User user);

    Optional<Match> findMatchById(Long matchId);
}