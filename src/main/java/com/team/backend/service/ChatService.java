package com.team.backend.service;

import com.team.backend.model.Match;
import com.team.backend.model.Message;
import com.team.backend.model.User;
import com.team.backend.model.dto.MessageRequestDto;
import com.team.backend.model.dto.MessageResponseDto;
import com.team.backend.model.mapper.MessageMapper;
import com.team.backend.repository.MatchRepository;
import com.team.backend.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatService {

    private final MatchRepository matchRepository;
    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageResponseDto processIncomingMessage(Long matchId, MessageRequestDto requestDto) {
        // TODO: tu czasem sie wywalalo bo
        // Cannot invoke "org.springframework.security.core.Authentication.getName()" because "authentication" is null
        // niekoniecznie musi byc wina tej metody ale przy robieniu frontu jakby byly problemy to moze tu
        User sender = userService.getCurrentUser();

        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match not found: " + matchId));

        Message message = MessageMapper.toEntity(requestDto, sender, match);
        log.debug(message.toString());
        Message saved = messageRepository.save(message);

        return MessageMapper.mapToMessageResponse(saved);
    }

    public List<MessageResponseDto> getChatMessagesForMatch(Long matchId) {
        Match match = matchRepository.findMatchById(matchId).orElseThrow(() -> new RuntimeException("Match not found: " + matchId));

        User currentUser = userService.getCurrentUser();


        if (!match.getFirstUser().getId().equals(currentUser.getId()) &&
                !match.getSecondUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Not authorized to view messages for this match.");
        }

        return messageRepository.findByMatchIdOrderByTimestampDesc(matchId).stream()
                .map(MessageMapper::mapToMessageResponse)
                .toList();
    }
}
