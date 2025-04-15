package com.team.backend.service;

import com.team.backend.model.Match;
import com.team.backend.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {
    private MatchRepository matchRepository;


    @Transactional
    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> findByFirstUserId(Long firstUserId) {
        return matchRepository.findByFirstUserId(firstUserId);
    }

    public List<Match> findBySecondUserId(Long secondUserId) {
        return matchRepository.findBySecondUserId(secondUserId);
    }

}
