package com.team.backend.service;

import com.team.backend.model.Match;
import com.team.backend.model.Message;
import com.team.backend.model.dto.MessageResponseDto;
import com.team.backend.model.mapper.MessageMapper;
import com.team.backend.repository.MatchRepository;
import com.team.backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MessageRepository messageRepository;

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

    public Match findById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public List<MessageResponseDto> findMessagesByMatchId(Long matchId) {
        return messageRepository.findByMatchId(matchId).stream()
                .map(MessageMapper::mapToMessageResponse)
                .collect(Collectors.toList());
    }
}
