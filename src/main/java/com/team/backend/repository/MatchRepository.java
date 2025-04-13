package com.team.backend.repository;

import com.team.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match save(Match match);

    List<Match> findByFirstUserId(Long firstUserId);

    List<Match> findBySecondUserId(Long secondUserId);
}
